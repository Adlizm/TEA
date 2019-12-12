import sys
import re

# Tarefa 2
arquivo = open("karate/karate.gml","r")

M = 34
source = None
target = None
patternSource = "source [0-9]+"
patternTarget = "target [0-9]+"

matrixM = []
for i in range(0,M):
    matrixM.append([])
    for _ in range(0,M):
        matrixM[i].append(0)


for line in arquivo.readlines():
    listSource = re.findall(patternSource,line)
    if(len(listSource) !=  0):
        source = int( listSource[0].split(" ")[1] )
    
    listTarget = re.findall(patternTarget,line)
    if(len(listTarget) !=  0):
        target = int( listTarget[0].split(" ")[1] )

        matrixM[source - 1][target - 1] = 1

arquivo.close()

'''
# Arquivo para visualização com graphviz dot
arqOut = open("karate/matrix.txt","w")
for i in range(0,M):
    for j in range(0,M):
        if(matrixM[i][j] == 1):
            arqOut.write("%d -> %d\n" % (i,j))

arqOut.close()
'''

for i in range(0,M):
    ones = 0
    for j in range(0,M):
        ones += matrixM[i][j]
    if ones != 0:
        for j in range(0,M):
            matrixM[i][j] = matrixM[i][j]/ones

# Tarefa 3
b = 0.8
pagerank = []

for _ in range(0,M):
    pagerank.append(1/M)

def interation(matrix,b,pagerank):
    dPagerank = 0
    for i in range(0,M):
        newpagerank = 0
        for j in range(0,M):
            newpagerank = newpagerank + matrix[i][j] * pagerank[j]
        newpagerank = newpagerank*b + (1-b)*(1/M)
        
        dPagerank += abs(newpagerank - pagerank[i])
        pagerank[i] = newpagerank
    return dPagerank

# Tarefa 4
dPagerank = interation(matrixM,b,pagerank)
while(dPagerank > 0.001):
    dPagerank = interation(matrixM,b,pagerank)

for i in range(0,M):
    pagerank[i] = (i,pagerank[i])

def sortFuncComp(value):
    return value[1]
pagerank.sort(key = sortFuncComp, reverse = True)

'''
arqOut = open("karate/pagerank.txt","w")
for p in pagerank:
    arqOut.write("%d %.10lf\n" % (p[0],p[1]))
arqOut.close()
'''