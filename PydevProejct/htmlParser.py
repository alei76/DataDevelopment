#-*- coding: UTF-8 -*- 
from HTMLParser import HTMLParser
 
class MyHTMLParser(HTMLParser):#HTMLParser
    def __init__(self):
        HTMLParser.__init__(self)#inherit  
        self.links = []
 
    def handle_starttag(self, tag, attrs): #over ride this funtion to handle the start tag
        #print "Encountered the beginning of a %s tag" % tag
        if tag == "span":
            if len(attrs) == 0: pass  #if there is no attribute, pass
            else:
                for (variable, value)  in attrs: # <key,val> de pair
                    if variable == "href":
                        self.links.append(value)# append the value to the list
##############################################################################    
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