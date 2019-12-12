#!/usr/bin/env python
import sys

text = ""
user = ""
first = True

for line in sys.stdin:
	words = line.split("\t")
	if(first):
		user = words[0]
		text = words[1]
		first = False
	else if(user == words[0]):
		text += " " + words[1]
	else:
		write("%s\t%s" % (user,text))
		user = words[0]
		text = words[1]

if(not first):
	write("%s\t%s" % (user,text))