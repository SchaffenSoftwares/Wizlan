from flask import Flask, render_template,request as rq,jsonify as js
from pyzbar.pyzbar import decode
from PIL import Image as img

a=Flask(__name__)

@a.route("/",methods=["POST"])
def index():
    if rq.method=="POST":
        barcodeData=" "
        barcodeType=" "
        for i in decode(img.open(rq.files["barcode"].stream)):
            barcodeData=i.data.decode("utf-8")
            barcodeType = i.type
        return js(barcodeData=barcodeData,barcodeType= barcodeType)

if __name__=="__main__":
a.run()

