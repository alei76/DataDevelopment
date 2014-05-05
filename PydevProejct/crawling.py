#-*- coding: UTF-8 -*- 
from HTMLParser import HTMLParser
import urllib2
import re
class MyHTMLParser(HTMLParser):#HTMLParser
    def __init__(self):
        HTMLParser.__init__(self)#inherit  
        self.links = []
 
    def handle_starttag(self, tag, attrs): #over ride this funtion to handle the start tag
        #print "Encountered the beginning of a %s tag" % tag
        if tag == "a":
            if len(attrs) == 0: pass  #if there is no attribute, pass
            else:
                for (variable, value)  in attrs: # <key,val> de pair
                    if variable == "href":
                        self.links.append(value)# append the value to the list
 
if __name__ == "__main__":
    html_code = """
    <a href="www.google.com"> google.com</a>
    <A Href="www.pythonclub.org"> PythonClub </a>
    <A HREF = "www.sina.com.cn"> Sina </a>
    """
    hp = MyHTMLParser()
    hp.feed(html_code)
    
    hp.close()
    print(hp.links)
    
    def main():
        userMainUrl = "http://www.songtaste.com/user/351979/";
        req = urllib2.Request(userMainUrl);
        resp = urllib2.urlopen(req);
        respHtml = resp.read();
        print "respHtml=",respHtml; # you should see the ouput html
        #<h1 class="h1user">crifan</h1>
        foundH1user = re.search('<h1\s+?class="h1user">(?P<h1user>.+?)</h1>', respHtml);
        print "foundH1user=",foundH1user;
        if(foundH1user):
            h1user = foundH1user.group("h1user");
            print "h1user=",h1user;
     
    ###############################################################################
if __name__=="__main__":
    main();