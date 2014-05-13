#-*- coding: UTF-8 -*- 
'''
Created on 2014��4��22��

@author: xudong sun
'''

import re   
from string import rstrip
def reDemo():
##########################################################################################################
    p = re.compile('(ab)*')# group的概念
    print '''using group'''
    print p.match('ababababab').span() 
    p = re.compile('(a(b)c)d')
    m = p.match('abcd')
    print m.group(0)
    print m.group(1) 
    print m.group(2)
    
def req1():# status ok
#需求：提取下列doc里的1.0，1002和中文,用正则表达式的group来匹配每个列
#Date（日期）    Module 1.0（主页面）    Function（功能）     Time（次数）
#2014-04-17            1.0     1001 （应用按钮）√     10
#2014-04-17     1.0    1002（试手气按钮）√     4
#2014-04-17     1.0    1003 （兑换按钮）√     4
#2014-04-17     1.0    1004（我的账户按钮）√     4
#2014-04-17     1.0    1005 （关闭引导页）    22
#2014-04-17     1.0    1006 （点击提示消息进入主页）√    232
#2014-04-17     1.0    1007 （正常方式进入主页面）√    323
############################################################################################################
    req='''sum(case function when <function> then use_count else 0 end) AS <name1>_total_click,
    sum(case function when <function> then user_num else 0 end) AS <name2>_total_usr_num,
    sum(case function when <function> then use_count else 0 end)/sum(case function when 1001 then user_num else 0 end)as <name3>_ratio'''
    #############################################################################
    pchinese=re.compile('[\u4e00-\u9fa5]+') #�ж��Ƿ�Ϊ���ĵ�������ʽ
    fin=open("E:/tmp.txt") #��Ҫ��ȡ���ļ�
    fw=open("E:/outdata.txt","w")#��Ҫд����ļ�
    #fin.seek(0)# go to the beginning of the file
    #######################################################################
    columnList_function=[]#1.0
    columnList_module=[]#1001
    columnList_anno=[]#试手气
    #########################################################################
    
    print "begin reading"+fin.name
    
    tt="CREATE temporary TABLE `credit_transaction` (`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',"
    cc=" int(10) unsigned NOT NULL,\r";
    hh='insert tbname(';
    for line in fin.readlines():   #one line of the file is read into the var "line"
        #print line
        #s=re.search('.*d\.d\s+?(?P<myname>.+?).*',line);##找到这个pattern
        #s=re.search('.*([0-9]+\.[0-9]).+([0-9]+)[^d].*([\u4e00-\u9fa5]+).*',line);##找到这个pattern 
        s=re.search(r'.*([0-9]+\.[0-9])\s+([0-9]+)[^d].*',line);##成功，该串之所以能成功是因为 用\s代替了.*,当你知道是空格时，一定要用空格！
        #s1=re.search('.*[0-9]*\.[0-9].*[\u4e00-\u9fa5]+',line);##找到这个pattern 
        #其中[\u4e00-\u9fa5]+表示中文，[0-9]*\.[0-9]表示1.1这样的版本号，".*"表示任意数
        #s=re.match('.*',line)
        
        if s:
            print line
            #print s.group(0)
            print s.groups();
            #from Canvas import Group
            hh=hh+s.group(2)+'_total_click,'+s.group(2)+'_total_usr_num,'+s.group(2)+'_ratio,'
            s1='sum(case function when '+s.group(2)+" then use_count else 0 end) AS "+s.group(2)+"_total_click,\r"
            s2='sum(case function when '+s.group(2)+' then user_num else 0 end)as '+s.group(2)+'_total_usr_num,\r'
            s3='(sum(case function when '+s.group(2)+' then use_count else 0 end)/sum(case function when '+s.group(2)+' then user_num else 0 end) )as '+s.group(2)+'_ratio,\r'
            tt=tt+s.group(2)+'_total_click '+cc+s.group(2)+'_total_usr_num '+cc+s.group(2)+'_ratio '+cc;
            print s1
            print s2
            print s3
            fw.write(s1)
            fw.write(s2)
            fw.write(s3)         
    fin.close()
    fw.close()#�򿪵��ļ��ǵùر�Ŷ!
    print hh 
    #print tt
    if __name__ == "__main__":
        print "Hello PyDev!"
        
        
def req2():
#需求：提取下列doc里的sql语句
#public int getScheme1PointCount() {
#        return scheme1PointCount;
#    }
#
#    
#    @AnnotationGenSQL4Bean(sql1="select count(b.point) from  lottery_data as a inner join member_point_data as b on a.trade_id=b.trade_id where a.difftype=1",et=EnumBeanFieldType.INT, TbColumnName = "Time")
#    public void setScheme1PointCount(int scheme1PointCount) {
#        this.scheme1PointCount = scheme1PointCount;
#    }
    #############################################################################
    fin=open("C:/Users/admin/git/newGitRepo/Tables_project/Tables_Project/src/com/entity/credit/CreditNewUserStatBean.java") #��Ҫ��ȡ���ļ� 
    print "begin reading"+fin.name
    for line in fin.readlines():   #one line of the file is read into the var "line"
        #print line
        #s=re.search('.*d\.d\s+?(?P<myname>.+?).*',line);##找到这个pattern
        s=re.search('.*sql1=(?P<myname>).*',line);##找到这个pattern 
        s1=re.search('.*[0-9]*\.[0-9].*[\u4e00-\u9fa5]+',line);##找到这个pattern 
        #其中[\u4e00-\u9fa5]+表示中文，[0-9]*\.[0-9]表示1.1这样的版本号，".*"表示任意数
        #s=re.match('.*',line)
        if s:
            #print s.group("myname")
            print s.group()             
    fin.close()
    #fw.close()#�򿪵��ļ��ǵùر�Ŷ!
    #####################################################
def req3(): #需求:将含有@sql的地方用concate拼接起来#将select * from (@sql1)as a inner join (@sql2)as b on a.qid=b.qid按照concate拼接起来
    #############################################################################
    line="select * from (@sql0)as z left join (@sql1)as a on z.qid=a.qid left join (@sql2)as b on a.qid=b.qid left join (@sql3)as c on b.qid=c.qid"
    #s=re.search('.*(@.*).*',line);##找到这个pattern
    #s=re.search('(',line)
    s=line.find('(')
    e=line.find(')')
    print s,e
    l1=line.replace('(', ''' (", ''')
    l2= l1.replace(')', ''' ,") ''')
    print line
    print l2
    f1='''concate("''';
    f2='''")'''; 
    print f1+l2+f2
    #s1=re.search('.*[0-9]*\.[0-9].*[\u4e00-\u9fa5]+',line);##找到这个pattern 
    
    #if s:
        #print s.group("myname")
        #print s.group()
                
def req4():
#需求：提取下列xml里的 procedure语句
    #############################################################################
    fin=open("C:/Users/admin/git/newGitRepo/Tables_project/Tables_Project/src/com/entity/credit/sql4column.xml") #��Ҫ��ȡ���ļ� 
    print "begin reading"+fin.name
    s='';
    for line in fin.readlines():   #one line of the file is read into the var "line"
        s=s+line
    fin.close()
    
    iter=re.finditer(r'<procedure.?>(.*?)</procedure>',s,re.DOTALL);##找到这个pattern
    if iter:
        print "found"
        
        for r in iter:
            print r.span()
            print r.group()
    else:
        print s
        print "not found"
    #fw.close()#�򿪵��ļ��ǵùر�Ŷ!        
if __name__ == "__main__":
        req4()
        
        
        
def req5():
    fin=open("C:\Users\admin\git\newGitRepo\PydevProejct\req2") #��Ҫ��ȡ���ļ� 
    print "begin reading"+fin.name
    s='';
    for line in fin.readlines():   #one line of the file is read into the var "line"
        s=s+line
    fin.close()
    
    iter=re.finditer(r'''<procedure.?>(.*?)</procedure>''',s,re.DOTALL);##找到这个pattern
    if iter:
        print "found"
        
        for r in iter:
            print r.span()
            print r.group()
    else:
        print s
        print "not found"
    #fw.close()#�򿪵��ļ��ǵùر�Ŷ!        
if __name__ == "__main__":
        req1()