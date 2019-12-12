#!/usr/bin/env python
import sys

aux = None
#Primo próximo do numero de shingles unicos para K = 3 para as primeiras 2008 linhas do arquivo
primoProximo = 15373 

def h(x):
	return (x * 12 + 65) % primoProximo

for word in sys.stdin:
	if( aux != word ):
		print("%d" % ( h(hash(word)) ))
		aux = word
