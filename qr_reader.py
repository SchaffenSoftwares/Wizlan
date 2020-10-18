from pyzbar.pyzbar import decode
from flask import Flask,request as rq,jsonify as js
from PIL import Image as img

a=Flask(__name__)

@a.route("/",methods=["POST"])
def home():
     if rq.method=="POST":
        qr=" "
          for i in decode(img.open(rq.files["qr"].stream)):  # or rq.files["qr"]
             qr=i.data.decode("utf-8")
          return js(qr=qr)

if __name__=="__main__":
    a.run()