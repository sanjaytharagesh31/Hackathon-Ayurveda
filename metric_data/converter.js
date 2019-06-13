//data = import final_data.json file

function core_converter(system, author, metric, from, to, value) {
    var result = 0;
    result = (value) * (data[system][author][metric][from]/data[system][author][metric][to]);
    return result;
}
