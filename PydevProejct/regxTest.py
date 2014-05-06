#-*- coding: UTF-8 -*- 
'''
Created on 2014��5��5��

@author: admin
'''

import re



p = re.compile('ab*')
#p=re.compile('[a-z]+')
print p
m=p.match("geabaglke")
s=p.search("geabaglke")
f=p.finditer("tempo")
if m:
    print m.group()
    print m.span()
print s.group()
print s.span()


######################################################################
p = re.compile('\d+')
a=p.findall('12 drummers drumming, 11 pipers piping, 10 lords a-leaping')
if a:
    print a
    
################################regx iterator

iterator = p.finditer('12 drummers drumming, 11 ... 10 ...')

if iterator:  
    for match in iterator:
        print match.span()
        print match.group()

