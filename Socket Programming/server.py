#!/usr/bin/env python

import socket, thread

host = '127.0.0.1'
port = 80

server = socket.socket()
server.bind((host, port))
server.listen(5)

while True:
	client, addr = server.accept()
	print 'Connected to', addr

	print client

	# Echo everything sent to the port
	data = client.recv(1024)
	if not data: break
	client.sendall(data)

	client.close()