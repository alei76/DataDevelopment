package dynamicProxy;

public class Test {
    public static void main(String[] args) {
        DynamicProxy proxy = new DynamicProxy();
        IResource iresource = proxy.create();// ���ض�̬����
        iresource.operationA();
    }
}