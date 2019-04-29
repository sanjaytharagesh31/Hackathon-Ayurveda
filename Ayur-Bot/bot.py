import logging                      #imports logging module
import json

import ayur                         #this section imports the other files required for the various features of this bot.
import config

from telegram.ext import BaseFilter                                  
from telegram.ext import CommandHandler, Filters, MessageHandler, Updater
from telegram.error import TelegramError

"""
NOTE:
For a live demo
Talk to @exalt_bot on Telegram
AKA Exaltor

"""

class integery(BaseFilter):
    def filter(self, message):
        string = message.text
        try:
            float(string)
            return True
        except ValueError:
            return False

filter_integery = integery()

class stringy(BaseFilter):
    def filter(self, message):
        if isinstance(message.text, str):
            return True
        else:
            return False

filter_stringy = stringy()

#This is the main module which oversees the bot.
#API related functions are called seperately. *Only* functions directly affecting the bot are stored here.
#Written for SIH Hackathon, by Siddharth Sham.

#Lists used in the bot
first = {'/Length', '/Weight', '/Time'}
length = {"yavodara","angula","bitahasti","aratni","hasta","nrpahasta","rajahasta","vyama"}
weight = {"karsa","tola","masa","ratti","gunga","palam","prasrti","kudava","manika","prastha","adhaka","drona","surpa","droni","vahi","khari","tula","bhara"}
time = {"kastha","ghati","kala","muhurta","ahoratra","paksa","masa","rtu","ayana","samvatsara","yuga"}
modern_length = {"/inch","/centimetre"}
modern_weight = {"/gram","/kilogram"}
modern_time = {"/seconds","/minutes","/hours","/days","/months","/years"}

commands = ["/inch","/centimetre","/gram","/kilogram","/seconds","/minutes","/hours","/days","/months","/years"]

#Extraneous function used to create keyboards from the lists
def build_keyboard(items):
    keyboard = [[item] for item in items]
    reply_markup = {"keyboard":keyboard, "one_time_keyboard": True}
    return json.dumps(reply_markup)

#updater commands
updater = Updater(token = config.TOKEN)
dispatcher = updater.dispatcher

logging.basicConfig(format = '%(asctime)s - %(name)s - %(levelname)s - %(message)s', level=logging.INFO)

#Start of function definitions
def start(bot, update):
    keyboard = build_keyboard(first)
    user = update.message.from_user
    bot.send_message(chat_id=update.message.chat_id,
                    text="Welcome to the Ayurvedic unit converter, {0}! Select the type of unit you wish to convert: ".format(user.first_name),
                    reply_markup=keyboard)

def lengthy(bot, update):
    keyboard = build_keyboard(length)
    global converted
    converted = " metres"
    bot.send_message(chat_id=update.message.chat_id,
                text="Choose the unit you wish to convert from: ",
                reply_markup=keyboard)

def weighty(bot, update):
    global converted 
    converted= " grams"
    keyboard = build_keyboard(weight)
    bot.send_message(chat_id=update.message.chat_id,
                text="Choose the unit you wish to convert from: ",
                reply_markup=keyboard)

def timey(bot, update):
    global converted
    converted = " seconds"
    keyboard = build_keyboard(time)
    bot.send_message(chat_id=update.message.chat_id,
                text="Choose the unit you wish to convert from: ",
                reply_markup=keyboard)            

def initial(bot, update):
    global unit
    unit = update.message.text
    bot.send_message(chat_id=update.message.chat_id, text="Enter the Ayurvedic value: ")

def calc(bot, update):
    input = float(update.message.text)
    global done
    done = True
    bot.send_message(chat_id = update.message.chat_id, 
                     text = "{0} {1}s equals: {2}".format(input, unit, ayur.convert(unit, input)+converted))
    start(bot, update)  

#Start of handlers
start_handler = CommandHandler('start', start)
dispatcher.add_handler(start_handler)

length_handler = CommandHandler('Length', lengthy)
dispatcher.add_handler(length_handler)

weight_handler = CommandHandler('Weight', weighty)
dispatcher.add_handler(weight_handler)

time_handler = CommandHandler('Time', timey)
dispatcher.add_handler(time_handler)

convert_handler = MessageHandler(filter_stringy & ~filter_integery, initial)
dispatcher.add_handler(convert_handler)

calc_handler = MessageHandler(filter_integery, calc)
dispatcher.add_handler(calc_handler)

updater.start_polling()                                         #starts long polling
