import logging                      #imports logging module

import ayur
import chatter                      #this section imports the other files required for the various features of this bot.
import config
                                  
from telegram.ext import CommandHandler, Filters, MessageHandler, Updater
#This line imports the telegram wrapper, and makes the functions usable. Three cheers for wrappers and APIs!
from telegram.error import TelegramError
#This line is added for handling errors



#This is the main module which oversees the bot.
#API related functions are called seperately. *Only* functions directly affecting the bot are stored here.
#Created by Siddharth Sham for the tKnow Chatbots event. Rewritten for SIH Hackathon, again by Siddharth Sham.



updater = Updater(token = config.TOKEN)
dispatcher = updater.dispatcher

logging.basicConfig(format = '%(asctime)s - %(name)s - %(levelname)s - %(message)s', level=logging.INFO)

#Start of function definitions

def checker(bot, update):
    text = update.message.text              #gets the user input

    try:               
        bot.send_text(chat_id = update.message.chat_id, text = ayur.convert(text))
    except TelegramError:
        bot.send_message(chat_id = update.message.chat_id, text = "Whoops, I made a mistake. Can you try again later?")


def start(bot, update):                                 #The user is greeted by this message. A quick start guide, in essence.
    user = update.message.from_user            
    bot.send_message(chat_id = update.message.chat_id, text="Hi! What can I do for you, %s?"%user.first_name)

#End of function definitions


#Start of handlers

query_handler = MessageHandler(Filters.text, checker)          #handles textual queries
dispatcher.add_handler(query_handler)                          #Passes control to the main control block

start_handler = CommandHandler('start', start)
dispatcher.add_handler(start_handler)

#End of handlers


updater.start_polling()                                         #starts long polling
