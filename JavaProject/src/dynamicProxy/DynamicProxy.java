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
         //�������Proxy�ͱ��������ConcreteResource����ʵ����IResource�ӿ�
         return (IResource) Proxy.newProxyInstance(IResource.class.getClassLoader(), new Class[]{ IResource.class }, this);
        //newProxyInstance(���������classLoader,���������class�࣬������proxy��ʵ��InvocationHandler�ӿ�@overide��invoke����������������)
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
                o = method.invoke(obj, args);// ���򣬵���ԭ����
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

	
}