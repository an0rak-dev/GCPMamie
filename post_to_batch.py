#!/usr/bin/python
#coding: utf-8 

from random import randint

streets = [
    'Privet Drive',
    'Impasse du Tisseur',
    'Wisteria Walk',
    'Square Grimmaud',
    'Charing Cross Road',
    'Magnolia Crescent',
    'Allee des Embrumes',
    'Chemin de Traverse'
]

cities = [
    'Pre-au-Lard',
    "Godric's Hollow",
    'Little Whinging',
    'Little Hangleton',
    'Tinworth',
    'Budly Babberton',
    'Lille'
]

deliveryMethods = [
    'Post Mail',
    'Express Mail',
    'AirMail',
    'Owl',
    'Pickup Point'
]


def generateCommandNumber():
    return '#' + str(randint(1, 900000))


def generateAddress():
    number = randint(1, 54)
    result = str(number)
    streetIdx = randint(0, len(streets)) - 1
    result += ' ' + streets[streetIdx]
    result += ' ' + str(randint(1000, 90000))
    citiesIdx = randint(0, len(cities)) - 1
    result += ' ' + cities[citiesIdx]
    return result

import time
from google.cloud import pubsub_v1
project_id='talk-gcp-mamie'
topic_id='fulfilment'
publisher = pubsub_v1.PublisherClient()
topicPath = publisher.topic_path(project_id, topic_id)

def callback(future):
    if future.exception(timeout=30):
        print('Exception while pushing to fulfilment topic')
    else:
        print(future.result())

def post():
    commandNumber = generateCommandNumber()
    addr = generateAddress()
    methodIdx = randint(0, len(deliveryMethods)) - 1
    method = deliveryMethods[methodIdx]
    fullMsg = commandNumber + '|' + addr + '|' + method
    data = fullMsg.encode('utf-8')
    result = publisher.publish(topicPath, data=data)
    result.add_done_callback(callback)

if __name__ == '__main__':
    while True:
        time.sleep(4)
        post()
