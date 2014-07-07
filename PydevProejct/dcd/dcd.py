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

    v_newAlphas=mat(zeros((numInstance,1)))# initial vector
    
    newOmegaVec=cal_ini_OmegaVec(vlabelMAT,featureMAT,v_newAlphas,numInstance,dimFeature)
    print 'inititial omega is'
    print newOmegaVec
    maxIter=100
    iter=0
    while (iter < maxIter):
        iter+=1
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
                newOmegaVec=calNewOmega(oldOmegaVec,s_newAlpha,s_oldAlpha,s_label,v_xi)# ｗ's norm is changing
            else:print 'PG==0!'# convergence
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
    v=dcdSun(dataArr,labelArr,3)
    print v[v>0]
    
    
#    >>> alphas[alphas>0]
#matrix([[  6.55910741e-02,   1.38777878e-17,   1.81515309e-01,
#           7.94270188e-02,   1.11230857e-01,   1.61462339e-01,
#           5.38402059e-02]])
