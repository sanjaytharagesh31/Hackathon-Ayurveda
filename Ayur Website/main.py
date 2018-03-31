from flask import Flask
from flask_compress import Compress
from flask import render_template

app = Flask(__name__)
Compress(app)

@app.route('/',methods=['POST','GET'])
def master():
    return render_template('index.html')

@app.route('/siddha',methods=['POST','GET'])
def siddha():
    return render_template('siddha.html')

@app.route('/unani',methods=['POST','GET'])
def unani():
    return render_template('unani.html')

@app.route('/videos',methods=['POST','GET'])
def videos():
    return render_template('video.html')

if __name__ == '__main__':
    app.run(debug=True)
