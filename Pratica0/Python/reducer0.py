#!/usr/bin/env python
import sys

text = ""
userAux = ""
first = True

for line in sys.stdin:
	user, movie = line.split("\t",1)
	movie = movie.split("\n")[0]
	if(first):
		userAux = user
		text = movie
		first = False
	elif(user == userAux):
		text = text + " " + movie
	else:
		print(userAux + "\t" + text)
		userAux = user
		text = movie

if(not first):
	print("%s\t%s" % (user,text))
