import pyqrcode
import os
from flask import Flask,render_template as rt

a=Flask(__name__)

@a.route("/")
def ma():
   return rt("qrgen.html")

@a.route("/<st>")
def gh(st):
   qr = pyqrcode.create(st)
   qr.svg(os.getcwd()+"\static\myqr2.svg", scale = 8)
   qr.png(os.getcwd()+'\static\myqr2.png', scale = 6)
   if os.path.exists(os.getcwd()+"\static\myqr2.png"):
       return "Success"
   else:
       return "Failure"


if __name__=="__main__":
    a.run()