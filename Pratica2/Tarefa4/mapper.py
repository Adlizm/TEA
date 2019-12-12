#!/usr/bin/env python
import sys
import random

#Funcoes e constantes utilizadas nas Tarefas 2 e 3
K = 3
primoProximo = 15373

def h(x):
	return (x * 12 + 65) % primoProximo

docId = 1

for line in sys.stdin:
	values = ""
	index = 0
	length = len(line)
	while( index + K < length): 
		value = h( hash(line[index: index + K]) )
		values += str(value) + " "
		index += 1
	print("%d\t%s" % (docId,values) )
	docId += 1