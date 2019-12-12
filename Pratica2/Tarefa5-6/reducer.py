#!/usr/bin/env python
import sys

r = 5
b = 20
nSigs = r * b

simRate = 0.8

prevKey = None
bucket = []

def removeSimsAndPrint(bucket):
    aux = 1
    for sigA in bucket:
        valuesA = sigA.split(" ")
        for sigB in bucket[aux:]:
            valuesB = sigB.split(" ")
            equals = 0
            for i in range(1,nSigs + 1):
                if(valuesB[i] == valuesA[i]):
                    equals = equals + 1
            if(equals / nSigs > simRate):
                print("%s\t%s" % (valuesA[0], valuesB[0]) )
        aux = aux + 1

for line in sys.stdin:
    key , stringSig = line.split('\t',1)

    if(prevKey == None):
        prevKey = key
        bucket.append(stringSig)
    elif(key == prevKey):
        bucket.append(stringSig)
    else:
        removeSimsAndPrint(bucket)
        bucket = []
        bucket.append(stringSig)
        prevKey = key
