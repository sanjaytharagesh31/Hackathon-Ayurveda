const express = require('express')
const {WebhookClient} = require('dialogflow-fulfillment')
const fs = require('fs')
const app = express()

const PORT = 8056
const SESSION_LIMIT = 100       //Maximum number of sessions to store at same time
const default_unit_field = {    //JSON holds the values for default unit selections.
    "weight": {
        "from_unit": "gram",
        "to_unit": "kilogram"
    },
    "time": {
        "from_unit": "second",
        "to_unit": "minute"
    },
    "length": {
        "from_unit": "centimetre",
        "to_unit": "metre"
    },
    "volume": {
        "from_unit": "millilitre",
        "to_unit": "litre"
    }
};
const data_field = JSON.parse(fs.readFileSync('data_field.json')) //Read JSON data file

let session_map = new Map()     //Map datastructure to store sessions

app.get('/', (req, res) => res.send('AYUSH ChatBot fulfillment process running'))

app.post('/dialogflow', express.json(), (req, res) => {
    const agent = new WebhookClient({
        request: req,
        response: res
    })

    let session_token = agent.session.split('/')[4] // get the session token
    console.log(session_token + ' requesting...')

    if(session_map.has(session_token)) { //session already present
        console.log(session_token + ' session token already present')
    }
    else { //session not available
        let df = {
            'system': 'ayurveda',
            'metric': 'weight',
            'author': 'api',
            'pre_from_unit': default_unit_field['weight']['from_unit'],
            'pre_to_unit': default_unit_field['weight']['to_unit'],
            'pre_value': 1
        }
        if (session_map.size === SESSION_LIMIT) {
            console.log('Session limit reached. Deleting ' + string(session_map.keys().next().value))
            session_map.delete(session_map.keys().next().value)
        }
        console.log(session_token + " session token not found, inserting token...")
        session_map.set(session_token, df)
        console.log(session_map)
    }

    //ShowGlobalParameters Intent. Displays the current selected parameters and previous converted units.
    function showGlobalParameters() {
        let df = session_map.get(session_token)
        agent.add(`The selected global parameters are (${df['system']}, ${df['author']}, ${df['metric']}). We're good to go captain 😎. You previously converted ${df['pre_from_unit']} to ${df['pre_to_unit']}.`);
    }

    //SwapUnits Intent. swaps the FROM and TO units. Also Calculates the new answer. 
    function swapUnits() {
        let df = session_map.get(session_token)
        let pfu = df['pre_from_unit']
        let ptu = df['pre_to_unit']

        //swap  the values
        df['pre_from_unit'] = ptu
        df['pre_to_unit'] = pfu
        session_map.set(session_token, df)

        //Calculate the new value after swapping the units.
        let unitfrom_value = data_field[df['system']][df['author']][df['metric']][df['pre_from_unit']];
        let unitto_value = data_field[df['system']][df['author']][df['metric']][df['pre_to_unit']];
        let answer = (df['pre_value']) * (unitfrom_value / unitto_value);

        agent.add(`${df['pre_value']} ${df['pre_from_unit']} is ${answer} ${df['pre_to_unit']}.`);
    }

    //GlobalParameters Intent. Sets the global parameters for conversions.
    function globalParameters() {
        global_system = agent.parameters.global_system
        global_author = agent.parameters.global_author
        global_metric = agent.parameters.global_metric

        let df = session_map.get(session_token)

        //Set the global systems based on user request  
        if (global_system != '')
            df['system'] = global_system;
        if (global_author != '')
            df['author'] = global_author;
        if (global_metric != '')
            df['metric'] = global_metric;

        let s = df['system']
        let a = df['author']
        let m = df['metric']
        let pfu = df['pre_from_unit']
        let ptu = df['pre_to_unit']

        //Store a default response
        let res = `The parameters (${s}, ${a}, ${m}) are set. Let's do some conversions 😎`;

        if (s === 'unani') { //only one author in unani (API)
            a = 'api'; //change author to API
            df['author'] = a
            if (m != 'weight' && m != 'volume') {
                res = `Unfortunately Unani system doesn't support ${m}. It only supports weight and volume measures. Let me change the metric, to weight, for you.`;
                m = 'weight';
                df['metric'] = m;
            }
        }

        if (s === 'siddha') { //only one author (API)
            a = 'api'; //change author to API
            df['author'] = a
            res = `The parameters (${s}, ${a}, ${m}) are set. Let's do some conversions 😎`;
        }

        if (s === 'ayurveda') {
            if (a === 'charaka' || a === 'sharangadhara' || a === 'rrs' || a === 'vaidyaka paribhasha pradeepa') { //authors defined only weight metric
                if (m != 'weight') {
                    m = 'weight';
                    df['metric'] = m;
                    res = `${a} has defined only weight metric. I've changed the metric to weight`;
                }
            } else if (a === 'sushruta') { //author has defined only weight and time metrics
                if (m != 'weight' && m != 'time') {
                    m = 'weight';
                    df['metric'] = m;
                    res = `${a} has defined only weight and time metrics. Metric changed to weight.`;
                }
            } else if (a == 'api') { //API has defined onlt weight, time and length
                if (m != 'weight' && m != 'time' && m != 'length') {
                    m = 'weight';
                    df['metric'] = m;
                    res = `${a} has defined only weight, time and length metrics. Metric changed to weight`;
                }
            }
        }

        //set previous/default units for the selected metrics
        pfu = default_unit_field[m]['from_unit'];
        ptu = default_unit_field[m]['to_unit'];
        df['pre_from_unit'] = pfu
        df['pre_to_unit'] = ptu

        session_map.set(session_token, df)

        agent.add(res);
    }

    //Converter Intent. The core conversion of units is executed here. 
    function converter() {
        unitfrom = agent.parameters.unitfrom
        unitto = agent.parameters.unitto
        value = agent.parameters.value

        let df = session_map.get(session_token)

        //Set the FROM unit if user has requested.
        if (unitfrom != '')
            df['pre_from_unit'] = unitfrom;

        //Set the TO unit if user has requested.
        if (unitto != '')
            df['pre_to_unit'] = unitto;

        //Set the value to convert, if user has requested else set to default value ie 1.
        if (value != '')
            df['pre_value'] = Math.abs(value);

        session_map.set(session_token, df);
        let s = df['system']
        let a = df['author']
        let m = df['metric']
        let pfu = df['pre_from_unit']
        let ptu = df['pre_to_unit']
        let pv = df['pre_value']

        //Get the propotion of FROM and To unit from data_field.
        let unitfrom_value = data_field[s][a][m][pfu];
        let unitto_value = data_field[s][a][m][ptu];

        //FROM unit - not found under system->author->metric.
        if (typeof unitfrom_value === 'undefined')
            agent.add(`Oops1! I couldn't find ${pfu} in ${s}, ${a}, ${m} 🙁. Can you try checking the website (https://siddharthsham.github.io/ayush) for reference`);

        //TO unit - not found under system->author->metric.
        else if (typeof unitto_value === 'undefined')
            agent.add(`Oops2! I couldn't find ${ptu} in ${s}, ${a}, ${m} 🙁. Can you try checking the website (https://siddharthsham.github.io/ayush) for reference`);
        
        //Author has not defined FROM unit.
        else if (unitfrom_value === 0)
            agent.add(`I believe ${a} has not defined ${pfu} unit in his metrics 😕. Refer the website (https://siddharthsham.github.io/ayush) for referecnce`);

        //Author has not defined TO unit.
        else if (unitto_value === 0)
            agent.add(`${a} has not defined ${ptu} unit. Can you try converting to a different unit? or refer the website (https://siddharthsham.github.io/ayush) for referecnce`);

        //Calculate the result, if all entered inputs are valid.
        else {
            let answer = (pv) * (unitfrom_value / unitto_value);
            agent.add(`${pv} ${pfu} is ${answer} ${ptu}.`);
        }
    }

    let intentMap = new Map()
    intentMap.set('Converter', converter)
    intentMap.set('GlobalParameters', globalParameters)
    intentMap.set('ShowGlobalParameters', showGlobalParameters)
    intentMap.set('SwapUnits', swapUnits)
    agent.handleRequest(intentMap)
})

app.listen(PORT, () => console.log(`AYUSH BOT listening at http://localhost:${PORT}`))



