print('sunxd hello world')

from pyquery import PyQuery as pyq
from lxml import etree   

import urllib
d = pyq("<html></html>")
d = pyq(etree.fromstring("<html></html>"))
d = pyq(url='http://google.com/')
# d = pq(url='http://google.com/', opener=lambda url, **kw: urllib.urlopen(url).read())
#d = pyq(filename=path_to_html_file)

#doc=pyq(url=r'http://youa.baidu.com/')

#cts=doc('.cat-items .item')
#for i in cts:
#    print '====',pyq(i).find('h3').text(),'===='
#    for j in pyq(i).find('dd a'):
#        print pyq(j).text()
#    print '\n'
    
    
