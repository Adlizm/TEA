#!/usr/bin/env python
import sys
import random

seed = 807
b = 20
r = 5
br = b*r
primo = 15373

def minHashing(values):
	signatures = []
	random.seed(seed)
	for _ in range(0,br):
		weight = random.randint(1,br)
		basie = random.randint(1,br)
		litter = primo + 1
		for value in values:
			x = (weight*value + basie) % primo
			if(x < litter):
				litter = x
		signatures.append(litter)
	return signatures


for line in sys.stdin:
	docId, values = line.split('\t',1)
	values = values.split(" ")
	newValues = []
	for value in values:
		if(value.isdigit()):
			newValues.append(int(value))

	sigs = minHashing(newValues)
	stringSigs = ""
	for sig in sigs:
		stringSigs += str(sig) + " "
	print( "%s\t%s" % (docId,stringSigs) )
    
