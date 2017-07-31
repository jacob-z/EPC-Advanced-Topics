#!/usr/bin/env python

import socket

host = '127.0.0.1'
port = 80

client = socket.socket()
client.connect((host, port))
client.sendall("Thanks for accepting my connection!")
data = client.recv(1024)
client.close()
print 'Received:', repr(data)