from flask import Flask
from flask import render_template
from flask import request
from flask import flash

app = Flask(__name__)

@app.route('/',methods=['POST','GET'])
def master():
    return render_template('index.html')

if __name__ == '__main__':
    app.run(debug=True)
