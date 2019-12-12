#!/usr/bin/env python
import sys

r = 5
b = 20
nSigs = r*b

for line in sys.stdin:
    docId, stringSigs = line.split("\t",1)
    sigs = stringSigs.split(" ")
    
    index = 0
    indexBanda = 1
    while(index + r < nSigs):
        banda = sigs[index:index + r]
        strBanda = ""
        for s in banda:
            strBanda += s
        value = str(hash(strBanda) % nSigs)
        key = str(indexBanda) + value
        print('%s\t%s %s' % (key, docId, stringSigs) )
        index += r
        indexBanda += 1
