#-*- coding: UTF-8 -*- 
'''
Created on 2014��4��22��

@author: xudong sun
'''


if __name__ == "__main__":
    print "Hello PyDev!"
    
import re   
pchinese=re.compile('`*`') #�ж��Ƿ�Ϊ���ĵ�������ʽ
fin=open("D:/pythonWorkspace/resources/mysqlStmt") #��Ҫ��ȡ���ļ�
fw=open("outdata.txt","w")#��Ҫд����ļ�
fin.seek(0)# go to the beginning of the file
#######################################################################
columnList=[]
columnType=[]
typelist=['int' , 'decimal' ,'varchar']#��Ҫ�Ӷ��ţ����������
typedic={}
typedic['int']='Integer'
typedic['decimal']='Double'
typedic['varchar']='String'
typelist
#########################################################################


for line in fin.readlines():   #ѭ����ȡҪ��ȡ�ļ���ÿһ��
    index1=line.find('`');
    index1
    if index1 !=-1:
        index2=str(line[index1+1:]).find('`')
        index2
        if index2 != -1:
            sub=line[index1+1:index1+index2+1]
            print(sub)
            print(line[index1+index2+1:])
            columnList.append(sub)
            for x in typelist:
                print('x is'+x)
                typelist
                subsub=line[index1+index2+1:]
                if line[index1+index2+1:].find(x):
                    sub
                    print('x is'+x)
                    columnType.append(typedic[x]+' '+sub)
                    break
fin.close()
fw.close()#�򿪵��ļ��ǵùر�Ŷ!
str1='class '+columnList[0]+'{';
str2=reduce(lambda x,y:x+';'+'\n'+' private '+y,columnType,str1)
str2=str2+';}//beanOver'