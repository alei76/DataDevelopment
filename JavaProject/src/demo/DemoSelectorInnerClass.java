package demo;

interface Selector {
	boolean end();

	Object current();

	void next();
}

public class DemoSelectorInnerClass {
	private Object[] items;
	private int next = 0;

	public DemoSelectorInnerClass(int size) {
		items = new Object[size];
	}

	public void add(Object x) {
		if (next < items.length)
			items[next++] = x;
	}

	private class hiSelector implements Selector {// inner class
		private int i = 0;

		public boolean end() {
			return i == items.length;
		}

		public Object current() {
			return items[i];   // 直接引用enclose类的field
		}

		public void next() {
			if (i < items.length)
				i++;
		}

	}

	public Selector selector() {
		return new hiSelector();
	}

	public static void main(String[] args) {
		DemoSelectorInnerClass hi = new DemoSelectorInnerClass(10);
		

		  
		for (int i = 0; i < 10; i++)
			hi.add(Integer.toString(i));
		Selector selector = hi.selector();  // 获得该类的innerclass即迭代器
		while (!selector.end()) {
			System.out.print(selector.current() + " ");
			selector.next();
		}
	}
}