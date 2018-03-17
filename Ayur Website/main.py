from flask import Flask
from flask_compress import Compress
from flask import render_template

app = Flask(__name__)
Compress(app)

@app.route('/',methods=['POST','GET'])
def master():
    return render_template('index.html')

if __name__ == '__main__':
    app.run(debug=True)
