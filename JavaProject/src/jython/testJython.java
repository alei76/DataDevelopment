package jython;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.python.core.PyException;
import org.python.core.PyFloat;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import com.PyUtil;

public class testJython {

	
	PythonInterpreter interp1 = new PythonInterpreter();
	
	void test()
	{
		
		interp1.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");  
		interp1.exec("print days[1];");  
	
		interp1.set("a", "This is a test"); 
		interp1.exec("print(a)");
		interp1.exec("print a[5:]"); 
		String[] s = { "How", "Do", "You", "Do?" }; 
		interp1.set("b", s);
		interp1.exec("for x in b: print x[0], x"); // set() only takes Objects, so it can't 
		/// figure out primitives. Instead,
		// you have to use wrappers:
		
		interp1.set("c", new PyInteger(1)); 
		interp1.set("d", new PyFloat(2.2));
	
		interp1.exec("print c + d");
		// You can also use Java's object wrappers: 
		interp1.set("c", new Integer(9)); 
		interp1.set("d", new Float(3.14)); 
		interp1.exec("print c + d");
		// Define a Python function to print arrays: 
		//interp1.exec("def prt(x): \n" + " print x \n" + " for i in x: \n" + "print i, \n" + " print x.__class__\n");
		// Arrays are Objects, so it has no trouble // figuring out the types contained in arrays: 
		Object[] types = {
		new boolean[]{ true, false, false, true }, 
		new char[]{ 'a', 'b', 'c', 'd' },
		new byte[]{ 1, 2, 3, 4 }, 
		new int[]{ 10, 20, 30, 40 }, 
		new long[]{ 100, 200, 300, 400 }, 
		new float[]{ 1.1f, 2.2f, 3.3f, 4.4f }, 
		new double[]{ 1.1, 2.2, 3.3, 4.4 }};
		
		ArrayList x = new ArrayList();
		for(int i = 0; i < 10; i++) x.add(new Integer(i * 10));
		
		Map m = new HashMap();
		m.put(new Integer(1), new Character('a')); m.put(new Integer(3), new Character('b')); 
		m.put(new Integer(5), new Character('c')); m.put(new Integer(7), new Character('d')); 
		m.put(new Integer(11), new Character('e')); 
		System.out.println("m: " + m); 
		interp1.set("m",m);
		
		interp1.set("g", x);
		interp1.exec("print g"); 
		interp1.exec("print g[1]");
		// ... But it's not quite smart enough // to treat it as a Python array: 
		interp1.exec("print g.__class__");
		interp1.set("m", PyUtil.toPyDictionary(m)); 
		interp1.exec("print m, m.__class__, " + "m[1], m[1].__class__");
		interp1.exec("for x in m.keys():print x,m[x]");
		
		PyObject obj=interp1.get("a");
		System.out.println("a = " + obj);
	}
	
	
	public static void main(String [] args) throws PyException 
	{
		new testJython().test();
	}
}
