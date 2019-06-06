#!/usr/bin/python3

import socket
import subprocess
import time
from google.cloud import pubsub_v1

project_id='talk-gcp-mamie'
subscription_id='nextOrder'

def callback(msg):
    data = msg.data
    msg.ack()
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(('127.0.0.1', 10022))
    s.send(data)
    s.close()


def main():
    subprocess.Popen(['batch'], shell=True, stdin=None, stdout=None, stderr=None)
    subscriber = pubsub_v1.SubscriberClient()
    subscriptionPath = subscriber.subscription_path(project_id, subscription_id)
    
    subscriber.subscribe(subscriptionPath, callback=callback)
    while True:
        time.sleep(60)

if __name__ == '__main__':
    main()
