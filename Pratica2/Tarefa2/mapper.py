#!/usr/bin/env python
import sys

k = int(sys.argv[1])

for line in sys.stdin:
	atual = 0
	length = len(line)
	while( atual + k < length): 
		print(line[atual:atual + k])
		atual = atual + 1
