#!/usr/bin/env python
import sys
import re
import numpy as np

# Tarefa 2
M = 34
# Cria array M x M com zeros
matrixM = np.zeros((M,M))

# Padrões no arquivo
source = None
target = None
patternSource = "source [0-9]+"
patternTarget = "target [0-9]+"

arquivo = open("Pratica4/karate.gml","r")

for line in arquivo.readlines():
    listSource = re.findall(patternSource,line)
    if(len(listSource) !=  0):
        source = int( listSource[0].split(" ")[1] )
    
    listTarget = re.findall(patternTarget,line)
    if(len(listTarget) !=  0):
        target = int( listTarget[0].split(" ")[1] )

        matrixM[source - 1][target - 1] = 1

arquivo.close()


# Transformando Matrix 0,1 na matrix de probabilidades
for i in range(0,M):
    # Soma os valores da coluna i
    s = np.sum( [matrixM[j][i] for j in range(0,M)] )
    if(s != 0):
        for j in range(0,M):
            # Divide todos os valores da coluna i pela soma
            matrixM[j][i] = matrixM[j][i] / s         

'''
# Escrever matrix no arquivo
arquivo = open("Pratica4/matrix.txt","w")
for row in matrixM:
    arquivo.write(str(list(row)))
    arquivo.write("\n")
arquivo.close()
'''

# Transformando array em Matriz
matrixM = np.matrix(matrixM)      

# Tarefa 3
b = 0.8
pagerank = np.matrix(np.ones((M,1)))
pagerank = pagerank / M

def interation():
    global matrixM
    global pagerank
    
    newpagerank = (matrixM @ pagerank) * b + (1 - b)*(1 / M)
    dPagerank = np.abs(np.sum(newpagerank) - np.sum(pagerank))
    pagerank = newpagerank
    return dPagerank

# Tarefa 4
dPagerank = interation()
while(dPagerank > 0.001):
    dPagerank = interation()

'''
# Escrever pagerank no arquivo
arquivo = open("Pratica4/pagerank.txt","w")
arquivo.write(str(pagerank))
arquivo.close()
'''