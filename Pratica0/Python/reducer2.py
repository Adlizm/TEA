#!/usr/bin/env python
import sys

aux = None
cont = 0

for line in sys.stdin:
	if(aux == None):
		line = aux
		cont += 1
	else if(line == aux):
		cont += 1
	else:
		write(aux + " " + cont)
		cont = 1
		aux = line

if(aux != None):
	write(aux + " " + cont)