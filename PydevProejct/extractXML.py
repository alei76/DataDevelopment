#-*- coding: UTF-8 -*- 
#@author 孙旭东

import xml.etree.ElementTree as ET
tree = ET.parse('C:/Users/admin/git/newGitRepo/Tables_project/Tables_Project/src/com/entity/credit/sql4column.xml')
root = tree.getroot()
print root