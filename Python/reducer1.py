#!/usr/bin/env python
import sys

aux = None
cont = 0

for line in sys.stdin:
	if(aux == None):
		aux = line
		cont += 1
	elif(line == aux):
		cont += 1
	else:
		print(aux + " " + str(cont))
		cont = 1
		aux = line

if(aux != None):
	print(aux + " " + str(cont))