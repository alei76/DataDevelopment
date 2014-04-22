package IterableWithInnerClass;


public class IterableUsingInnerClass {
	
	 private Object[] items;
	 private int next = 0;
	 
	 public IterableUsingInnerClass(int size) 
	 
	 {  // ���캯��
		 items = new Object[size];
	 }
	 
	 
	 public void add(Object x) 
	 {
	  if (next < items.length)
		  items[next++] = x;
	 }
	 private class IterableUsingInnerClassSelector implements Selector 
	 {
		 // inner class
		  private int i = 0;
		  public boolean end() 
		  {
		   return i == items.length;
		  }
		  
		  public Object current() 
		  {
			  return items[i]; // ֱ������enclose���field
		  }
		  
		  public void next() 
		  {
			  if (i < items.length)
				  i++;
		  }
	 }
	 public Selector getSelector() {
		 return new IterableUsingInnerClassSelector();
	 }
	 
	 
	 public static void main(String[] args) {
	  
		 IterableUsingInnerClass hi = new IterableUsingInnerClass(10);
		 for (int i = 0; i < 10; i++)
			 hi.add(Integer.toString(i));
		 Selector selector = hi.getSelector(); // ��ø����innerclass��������
		 while (!selector.end()) {
			 System.out.print(selector.current() + " ");
			 selector.next();
		 }
	 }

}




