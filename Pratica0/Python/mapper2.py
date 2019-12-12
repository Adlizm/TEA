#!/usr/bin/env python
import sys

for line in sys.stdin:
	user, movies = line.split("\t",1)
	movies = movies.split(" ")

	movies.sort()

	for i in range(0,len(movies) - 1):
		for j in range(i+1,len(movies)):
			write(movies[i] + " " + movies[j])