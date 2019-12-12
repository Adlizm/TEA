#!/usr/bin/env python
import sys

aux = None

for word in sys.stdin:
	if( aux != word ):
		print("%s" % (word) )
		aux = word