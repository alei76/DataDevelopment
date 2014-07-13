#-*- coding: UTF-8 -*- 
'''
Created on 2014年7月7日

@author: sunxd
'''
from numpy import *
from time import sleep



def loadDataSet(fileName):
    dataMat = []; labelMat = []
    fr = open(fileName)
    for line in fr.readlines():
        lineArr = line.strip().split('\t')
        dataMat.append([float(lineArr[0]), float(lineArr[1])])
        labelMat.append(float(lineArr[2]))
    return dataMat,labelMat


def dcdSun(dataMatIn, classLabels,U):
    featureMAT = mat(dataMatIn)
    vlabelMAT= mat(classLabels).transpose()
    #get the number of instances and number of features
    numInstance,dimFeature = shape(featureMAT)
    v_newAlphas=mat(ones((numInstance,1)))# initial vector
    newOmegaVec=cal_ini_OmegaVec(vlabelMAT,featureMAT,v_newAlphas,numInstance,dimFeature)
    print 'inititial omega is'
    print newOmegaVec
    maxOutterIter=100
    maxStat=40;
    outterIter=0
    stat=0;
    #while ((iter < maxOutterIter)&(stat<maxStat)):
    while (iter < maxOutterIter):
        outterIter+=1
        for i in range(numInstance):
            s_newAlpha=v_newAlphas[i]
            v_xi=featureMAT[i,:] # get the ith row,which is the training vector
            s_label=vlabelMAT[i] 
            G=calGradient(newOmegaVec,s_label,v_xi)
            oldOmegaVec=newOmegaVec.copy()
            PG=calPG(s_newAlpha, G, U);#scalar
            if PG!=0:
                s_oldAlpha=s_newAlpha.copy();
                s_newAlpha=calNewAlpha(s_oldAlpha,G,v_xi,U)#
                v_newAlphas[i]=s_newAlpha# memorize it
                print s_newAlpha
                newOmegaVec=calNewOmega(oldOmegaVec,s_newAlpha,s_oldAlpha,s_label,v_xi)# ｗ's norm is changing
                stat=0
            else:
                print 'PG==0!'# convergence
                stat+=1;
        #if iter%100==0:print v_newAlphas
    return v_newAlphas
            
def cal_ini_OmegaVec(vlabel,featureMat,v_alphaIni,numInstance,numFeature):
    w=mat(zeros((1,numFeature)))# Row vector
    for i in range(numInstance):
        s=float(vlabel[i])
        s*=v_alphaIni[i]
        #print 's is'
        #print s
        vec=s*mat(featureMat[i,:])
        
        w=w+vec
        
    return w;
        
    
def calNewAlpha(s_alpha,sGradient,vec_xi,U):
    ll=max(s_alpha-sGradient/(vec_xi*vec_xi.T),0)
    rr=min(ll,U)
    return rr    
def calNewOmega(vec_wOld,scalar_newAlpha_i,scalar_oldAlpha_i,scalar_label,vector_xi):#s denote scaler,v denote vector 
    w=vec_wOld+(scalar_newAlpha_i-scalar_oldAlpha_i)*scalar_label*vector_xi;
    return w
def calGradient(vector_w,s_label,vector_xi):
    #print 'vector_w'
    #print vector_w
    #print 'vector_xi'
    #print vector_xi
    temp=vector_w*vector_xi.T
    #print 'in calGradient'
    #print temp
    G=s_label*temp-1
    return G
def calPG(s_alpha,G,U):
    if s_alpha==0:
        PG=min(G,0)
    elif s_alpha==U:
        PG=max(G,0)
    elif s_alpha<U and s_alpha>0:
        PG=G;
    return PG;
            
if __name__ == "__main__":
    dataArr,labelArr=loadDataSet('testSet.txt')
    v=dcdSun(dataArr,labelArr,0.3)
    print 'finally'
    print v
    print 'support vector'
    print v[v>0]
    
    
#    >>> alphas[alphas>0]
#matrix([[  6.55910741e-02,   1.38777878e-17,   1.81515309e-01,
#           7.94270188e-02,   1.11230857e-01,   1.61462339e-01,
#           5.38402059e-02]])



def selectJrand(i,m):
    j=i #we want to select any J not equal to i
    while (j==i):
        j = int(random.uniform(0,m))
    return j

def clipAlpha(aj,H,L):
    if aj > H: 
        aj = H
    if L > aj:
        aj = L
    return aj


def smoSimple(dataMatIn, classLabels, C, toler, maxIter):
    dataMatrix = mat(dataMatIn); labelMat = mat(classLabels).transpose()
    b = 0; m,n = shape(dataMatrix)
    alphas = mat(zeros((m,1)))
    iter = 0
    while (iter < maxIter):
        alphaPairsChanged = 0
        for i in range(m):
            fXi = float(multiply(alphas,labelMat).T*(dataMatrix*dataMatrix[i,:].T)) + b
            Ei = fXi - float(labelMat[i])#if checks if an example violates KKT conditions
            if ((labelMat[i]*Ei < -toler) and (alphas[i] < C)) or ((labelMat[i]*Ei > toler) and (alphas[i] > 0)):
                j = selectJrand(i,m)
                fXj = float(multiply(alphas,labelMat).T*(dataMatrix*dataMatrix[j,:].T)) + b
                Ej = fXj - float(labelMat[j])
                alphaIold = alphas[i].copy(); alphaJold = alphas[j].copy();
                if (labelMat[i] != labelMat[j]):
                    L = max(0, alphas[j] - alphas[i])
                    H = min(C, C + alphas[j] - alphas[i])
                else:
                    L = max(0, alphas[j] + alphas[i] - C)
                    H = min(C, alphas[j] + alphas[i])
                if L==H: print "L==H"; continue
                eta = 2.0 * dataMatrix[i,:]*dataMatrix[j,:].T - dataMatrix[i,:]*dataMatrix[i,:].T - dataMatrix[j,:]*dataMatrix[j,:].T
                if eta >= 0: print "eta>=0"; continue
                alphas[j] -= labelMat[j]*(Ei - Ej)/eta
                alphas[j] = clipAlpha(alphas[j],H,L)
                if (abs(alphas[j] - alphaJold) < 0.00001): print "j not moving enough"; continue
                alphas[i] += labelMat[j]*labelMat[i]*(alphaJold - alphas[j])#update i by the same amount as j
                                                                        #the update is in the oppostie direction
                b1 = b - Ei- labelMat[i]*(alphas[i]-alphaIold)*dataMatrix[i,:]*dataMatrix[i,:].T - labelMat[j]*(alphas[j]-alphaJold)*dataMatrix[i,:]*dataMatrix[j,:].T
                b2 = b - Ej- labelMat[i]*(alphas[i]-alphaIold)*dataMatrix[i,:]*dataMatrix[j,:].T - labelMat[j]*(alphas[j]-alphaJold)*dataMatrix[j,:]*dataMatrix[j,:].T
                if (0 < alphas[i]) and (C > alphas[i]): b = b1
                elif (0 < alphas[j]) and (C > alphas[j]): b = b2
                else: b = (b1 + b2)/2.0
                alphaPairsChanged += 1
                print "iter: %d i:%d, pairs changed %d" % (iter,i,alphaPairsChanged)
        if (alphaPairsChanged == 0): iter += 1
        else: iter = 0
        print "iteration number: %d" % iter
    return b,alphas

