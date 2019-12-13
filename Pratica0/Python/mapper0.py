#!/usr/bin/env python
import sys

for line in sys.stdin:
	words = line.split(",")
	user = words[0]
	movie = words[1]
	try:
		grade = float(words[2])
	except:
		continue

	if(grade >= 4):
		print("%s\t%s" % (user,movie))