package com;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.python.core.Py;
import org.python.core.PyDictionary;

public class PyUtil 

{		
	public static PyDictionary toPyDictionary(Map map)
	{ 
		Map m = new HashMap();
		
		Iterator it = map.entrySet().iterator(); while(it.hasNext()) 
		{
			Map.Entry e = (Map.Entry)it.next();
			m.put(Py.java2py(e.getKey()), Py.java2py(e.getValue()));	
		}
		
		return new PyDictionary(new Hashtable(m));
	}
	
}
