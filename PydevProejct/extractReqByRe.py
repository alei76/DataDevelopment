#-*- coding: UTF-8 -*- 
import urllib2
import re
#class extractReqByRegx:
def main():
    userMainUrl = "file:///E:/jobsunxudong/credit/credit/span.html";
    print userMainUrl
    req = urllib2.Request(userMainUrl);
    resp = urllib2.urlopen(req);
    respHtml = resp.read();
    print "respHtml=",respHtml; # you should see the ouput html
    #<h1 class="h1user">crifan</h1>
    #foundH1 = re.search('<h1\s+?class="h1user">(?P<h1user>.+?)</h1>', respHtml);
    #\s matches any white space character
    #. matches any character except the newline character
    foundSpan = re.search('<span\s+?style=".*">(?P<myname>.+?)</span>',respHtml)
    #(?....)是python的正则表达式的拓展
    # (?P<name>...) defines a named group, and (?P=name) is a backreference to a named group
    
    print "foundSpan=",foundSpan;
    if(foundSpan):
        h1user = foundSpan.group("myname");  # 提取当时的
        #group() returns the substring that was matched by the RE. start() and end() return the starting and ending index of the match
        print "h1user=",h1user;
    ###############################################################################
if __name__=="__main__":
    main()
 