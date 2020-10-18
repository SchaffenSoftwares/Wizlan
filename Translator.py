# Importing essential libraries
from flask import Flask, render_template, request
from googletrans import Translator

translator=Translator()

app = Flask(__name__)

@app.route('/predict')
def predict():
    message = request.args.get('message')
    lang=request.args.get('languages')
    lang=lang.lower()
    if(lang=="kannada"):
        dest_code='kn'
    if(lang=="japanese"):
        dest_code='ja'
    if(lang=="hindi"):
        dest_code='hi'
    if(lang=="telugu"):
        dest_code='te'   
    if(lang=="tamil"):
        dest_code='ta'
    if (lang == "afrikaans"):
        dest_code = 'af'
    if (lang == "albanian"):
        dest_code = 'sq'
    if (lang == "arabic"):
        dest_code = 'ar'
    if (lang == "amharic"):
        dest_code = 'am'
    if (lang == "azerbaijani"):
        dest_code = 'az'
    if (lang == "bengali"):
        dest_code = 'bn'
    if (lang == "bulgarian"):
        dest_code = 'bg'
    if (lang == "catalan"):
        dest_code = 'ca'
    if (lang == "chichewa"):
        dest_code = 'ny'
    if (lang == "chinese(simplified)"):
        dest_code = 'zh-ch'
    if (lang == "chinese(traditional)"):
        dest_code = 'zh-tw'
    if (lang == "corsican"):
        dest_code = 'co'
    if (lang == "croatian"):
        dest_code = 'hr'
    if (lang == "czech"):
        dest_code = 'cs'
    if (lang == "danish"):
        dest_code = 'ta'
    if (lang == "dutch"):
        dest_code = 'nl'
    if (lang == "esperanto"):
        dest_code = 'eo'
    if (lang == "finnish"):
        dest_code = 'fi'
    if (lang == "french"):
        dest_code = 'fr'
    if (lang == "galician"):
        dest_code = 'gl'
    if (lang == "georgian"):
        dest_code = 'ka'
    if (lang == "german"):
        dest_code = 'de'
    if (lang == "greek"):
        dest_code = 'el'
    if (lang == "gujrati"):
        dest_code = 'gu'
    if (lang == "hausa"):
        dest_code = 'ha'
    if (lang == "hawaiin"):
        dest_code = 'haw'
    if (lang == "hebrew"):
        dest_code = 'iw'
    if (lang == "hmong"):
        dest_code = 'hmn'
    if (lang == "indonesian"):
        dest_code = 'id'
    if (lang == "italian"):
        dest_code = 'it'
    if (lang == "kannada"):
        dest_code = 'kn'
    if (lang == "kazakh"):
        dest_code = 'kk'
    if (lang == "lao"):
        dest_code = 'lo'
    if (lang == "latin"):
        dest_code = 'la'
    if (lang == "lithuanian"):
        dest_code = 'lt'
    if (lang == "luxembourgish"):
        dest_code = 'lb'
    if (lang == "malagasy"):
        dest_code = 'mg'
    if (lang == "malayalam"):
        dest_code = 'ml'
    if (lang == "marathi"):
        dest_code = 'mr'
    if (lang == "mongolian"):
        dest_code = 'mn'
    if (lang == "myanmar"):
        dest_code = 'my'
    if (lang == "nepali"):
        dest_code = 'ne'
    if (lang == "norwegian"):
        dest_code = 'no'
    if (lang == "odia"):
        dest_code = 'or'
    if (lang == "persian"):
        dest_code = 'fa'
    if (lang == "polish"):
        dest_code = 'pl'
    if (lang == "portuguese"):
        dest_code = 'pt'
    if (lang == "punjabi"):
        dest_code = 'pa'
    if (lang == "romanian"):
        dest_code = 'ro'
    if (lang == "russian"):
        dest_code = 'ru'
    if (lang == "serbian"):
        dest_code = 'sr'
    if (lang == "sindhi"):
        dest_code = 'sd'
    if (lang == "slovak"):
        dest_code = 'sk'
    if (lang == "somali"):
        dest_code = 'so'
    if (lang == "spanish"):
        dest_code = 'es'
    if (lang == "swahili"):
        dest_code = 'sw'
    if (lang == "swedish"):
        dest_code = 'sv'
    if (lang == "thai"):
        dest_code = 'th'
    if (lang == "turkish"):
        dest_code = 'tr'
    if (lang == "urdu"):
        dest_code = 'ur'
    if (lang == "uzbek"):
        dest_code = 'uz'
    if (lang == "vietnamese"):
        dest_code = 'vi'
    if (lang == "yiddish"):
        dest_code = 'yi'
    if (lang == "zulu"):
        dest_code = 'zu'

    text_to_translate = translator.translate(message, src= 'en', dest= dest_code)
    text = text_to_translate.text  
    return text



if __name__ == '__main__':
    app.run(debug=True)