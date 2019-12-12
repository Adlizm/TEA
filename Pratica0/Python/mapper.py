#!/usr/bin/env python
import sys

for line in sys.stdin:
	words = line.split(" ")
	user = words[0]
	movie = words[1]
	grade = float(words[2])
	if(grade >= 4):
		write( "%s\t%s" % (user,movie))

