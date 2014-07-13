#-*- coding: UTF-8 -*- 
'''
Created on 2014年7月7日

@author: sunxd
'''
import numpy;
from numpy import *;
##################
import matplotlib as mpl
import pylab as p
#import matplotlib.axes3d as p3
from mpl_toolkits.mplot3d import axes3d;#没有错误,只是pydev没有解析好
import mpl_toolkits.mplot3d.axes3d as p3;#没有错误,只是pydev没有解析好

class simpleBayesian:
    def mgenDataset(self):
        x=2*(numpy.random.random()-0.5)
        temp=simpleBayesian();   
        t=temp.mline(x)+numpy.random.normal(0,0.1,1);
        return x,t;
    def mline(self,x):
        a=-0.3
        b=0.5
        f=a*x+b;
        return f
    
    def mlikeli(self,w0,w1,x,t,beta):
    #y=w0+w1*x is the prediction,t is the target,  
        f=exp(beta*numpy.power(t-(w0+w1*x),2))
        return f
    
    def mprior(self,alpha,w0,w1):
        f=exp(-alpha*(numpy.power(w0,2)+numpy.power(w1,2)))
        return f
    
    def mexec(self):
        print 'simple beyesian demo'
        alpha_0=0.5 #precision of the prior distribution of w0,w1,will be updated
        w0_array=numpy.arange(-1.0,1.0,0.02)
        w1_array = numpy.arange(-1.0, 1.0, 0.02)
        w0_mesh, w1_mesh = numpy.meshgrid(w0_array, w1_array)
        gen=simpleBayesian();
        prior_mesh=gen.mprior(alpha_0, w0_mesh, w1_mesh)
        beta_0=0.3 #precision(inverse of the likelihood distribution),not updated
        for i in range(10):#take sequencial input data (x,t) to the learning engine
            #self.visualize3D(w0_mesh, w1_mesh, prior_mesh)
            self.mheat(w0_mesh, w1_mesh, prior_mesh)
            print 'sequencial input'
            x,t=gen.mgenDataset()#generate one pair of data(x,y)
            print([x,t])
            likeli_mesh=gen.mlikeli(w0_mesh,w1_mesh,x,t,beta_0)
            #self.visualize3D(w0_mesh, w1_mesh, likeli_mesh)
            self.mheat(w0_mesh, w1_mesh, likeli_mesh)
            posterior_mesh=numpy.multiply(prior_mesh,likeli_mesh)
            prior_mesh=posterior_mesh
            print(prior_mesh)
    def mheat(self,x,y,z):
       
        p.figure()
        p.hexbin(x, y, z)      
    def visualize3D(self,x,y,z):
        import matplotlib as mpl
        import pylab as p
        #import matplotlib.axes3d as p3
        from mpl_toolkits.mplot3d import axes3d;#没有错误,只是pydev没有解析好
        import mpl_toolkits.mplot3d.axes3d as p3;#没有错误,只是pydev没有解析好
        fig=p.figure()
        ax = p3.Axes3D(fig)
        ax.plot_wireframe(x,y,z)
        ax.set_xlabel('X')
        ax.set_ylabel('Y')
        ax.set_zlabel('Z')
        p.show()   
    def demo3Dplot(self):
        import matplotlib as mpl
        import pylab as p
        #import matplotlib.axes3d as p3
        from mpl_toolkits.mplot3d import axes3d;#没有错误,只是pydev没有解析好
        import mpl_toolkits.mplot3d.axes3d as p3;#没有错误,只是pydev没有解析好
        
        x = numpy.arange(-5, 5, 0.1)
        y = numpy.arange(-5, 5, 0.1)
        xx, yy = numpy.meshgrid(x, y)
        z = numpy.sin(xx**2+yy**2)/(xx**2+yy**2)
        fig=p.figure()
        ax = p3.Axes3D(fig)
        ax.plot_wireframe(x,y,z)
        ax.set_xlabel('X')
        ax.set_ylabel('Y')
        ax.set_zlabel('Z')
        p.show()
    def demoHeat(self):
        import matplotlib as mpl
        import matplotlib.pyplot as plt 
        x = numpy.arange(-5, 5, 0.1)
        y = numpy.arange(-5, 5, 0.1)
        xx, yy = numpy.meshgrid(x, y)
        zz= numpy.sin(xx**2+yy**2)/(xx**2+yy**2)
        plt.figure()
        plt.hexbin(xx, yy, zz)
        plt.show()

if __name__ == "__main__":
    hh=simpleBayesian()
    hh.mexec()
    print 'over'