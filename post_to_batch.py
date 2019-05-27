#!/usr/bin/python3


from random import randint
import socket

TCP_IP='127.0.0.1'
TCP_PORT=10022

streets = [
    'Privet Drive',
    'Impasse du Tisseur',
    'Wisteria Walk',
    'Square Grimmaud',
    'Charing Cross Road',
    'Magnolia Crescent',
    'Allée des Embrumes',
    'Chemin de Traverse'
]

cities = [
    'Pré-au-Lard',
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
    streetIdx = randint(0, len(streets)) -1
    result += ' ' + streets[streetIdx]
    result += ' ' + str(randint(1000, 90000))
    citiesIdx = randint(0, len(cities)) -1
    result += ' ' + cities[citiesIdx]
    return result


def post():
    commandNumber = generateCommandNumber()
    addr = generateAddress()
    methodIdx = randint(0, len(deliveryMethods)) - 1
    method = deliveryMethods[methodIdx]
    fullMsg = commandNumber + '|' + addr + '|' + method
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((TCP_IP, TCP_PORT))
    s.send(fullMsg.encode('utf-8'))
    s.recv(500)
    s.close()


if __name__ == '__main__':
    while True:
        post()
