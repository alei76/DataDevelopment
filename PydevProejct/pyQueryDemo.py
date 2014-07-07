#-*- coding: UTF-8 -*- 
print('sunxd hello world')

from pyquery import PyQuery as pyq
from lxml import etree   
import re
import urllib
d = pyq("<html></html>")
d = pyq(etree.fromstring("<html></html>"))
d = pyq(url='http://google.com/')
#print d.text()
p = re.compile('ab*')


print 'pyq.filter method'
d =pyq('<p class="hello">Hi</p><p>Bye</p>')

print d('p').filter(lambda i, this: pyq(this).text() == 'Hi')

# d = pq(url='http://google.com/', opener=lambda url, **kw: urllib.urlopen(url).read())
#d = pyq(filename=path_to_html_file)

#doc=pyq(url=r'http://youa.baidu.com/')

#cts=doc('.cat-items .item')
#for i in cts:
#    print '====',pyq(i).find('h3').text(),'===='
#    for j in pyq(i).find('dd a'):
#        print pyq(j).text()
#    print '\n'
p=pyq("<head><title>hello</title></head>")
print p('head').html()#返回<title>hello</title>
print p('head').text()#返回hello

d=pyq('<div><p>test 1</p><p>test 2</p></div>')
d('p')#返回[<p>,<p>]
print d('p')#返回<p>test 1</p><p>test 2</p>
print d('p').html()#返回test 1
print d('p').eq(1).html() #返回test 2, eq用来标识第几个标签

d=pyq("<div><p id='1'>test 1</p><p class='2'>test 2</p></div>")
print d('p').filter('#1') #返回[<p#1>]
#print d('p').filter('.2') #返回[<p.2>]


print '查找嵌套的元素'
d=pyq("<div><p id='1'>test 1</p><p class='2'>test 2</p></div>")
print d('div').find('p')#返回[<p#1>, <p.2>]
print d('div').find('p').eq(0)#返回[<p#1>]
print '根据类名获取元素'
d=pyq("<div><p id='1'>test 1</p><p class='2'>test 2</p></div>")
print d('#1').html()#返回test 1
#print d('.2').html()#返回test 2

print 'child'
d=pyq("<span><p id='1'>hello</p><p id='2'>world</p></span>")
print d.children()#返回[<p#1>, <p#2>]
print d.children('#2')#返回[<p#2>]
print 'parent'
d=pyq("<span><p id='1'>hello</p><p id='2'>world</p></span>")
print d('p').parents()#返回[<span>]
print d('#1').parents('span')#返回[<span>]
print d('#1').parents('p')#返回[]
