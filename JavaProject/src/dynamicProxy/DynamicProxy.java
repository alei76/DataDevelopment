package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



public class DynamicProxy implements InvocationHandler {
    private IResource IResource;
    public DynamicProxy() {
        IResource = new ConcreteResource();
    }

    public IResource create() 
    {
         //代理对象Proxy和被代理对象ConcreteResource都是实现了IResource接口
         return (IResource) Proxy.newProxyInstance(IResource.class.getClassLoader(), new Class[]{ IResource.class }, this);
        //newProxyInstance(被代理类的classLoader,被代理类的class类，代理类proxy（实现InvocationHandler接口@overide：invoke（）方法）的引用)
    }
    
    public Object invoke(Object obj, Method method, Object[] args) {
        Object o = null;
        System.out.println("inside invoke");
        try {
            
        	if (method.getName().equals("operationA")) 
            {
                System.out.println("OperationA in Proxy");
            } 
            else 
            {
            	System.out.println("inside Proxy");
                o = method.invoke(obj, args);// 否则，调用原方法
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

	
}