//: innerclasses/Callbacks.java
// Using inner classes for callbacks
package innerclasses;

//Everything except getCallbackReference( ) in Callee2 is private. To allow any 
//connection to the outside world, the interface Incrementable is essential. Here you can see 
//how interfaces allow for a complete separation of interface from implementation. 

import static net.mindview.util.Print.*;

interface Incrementable {
  void increment();
}

// Very simple to just implement the interface:
class Callee1 implements Incrementable {
  private int i = 0;
  public void increment() {
    i++;
    print(i);
  }
}	

class MyIncrement {
  public void increment() { print("Other operation"); }
  static void f(MyIncrement mi) { mi.increment(); }
}	

// If your class must implement increment() in
// some other way, you must use an inner class:
class Callee2 extends MyIncrement 
{
	private int i = 0;
	public void increment()
	{
		super.increment();
		i++;
		print(i);
	}
	
	private class Closure implements Incrementable  //inner class 实现接口
	{
		//
		//The inner class Closure implements Incrementable to provide a hook back into Callee2—
		//but a safe hook. Whoever gets the Incrementable reference can, of course, only call 
		//increment( ) and has no other abilities (unlike a pointer, which would allow you to run 
		//wild). 
		//Caller takes an Incrementable reference in its constructor (although the capturing of the 
		//callback reference could happen at any time) and then, sometime later, uses the reference to 
		//"call back" into the Callee class. 
		//The value of the callback is in its flexibility; you can dynamically decide what methods will be 
		//called at run time. The benefit of this will become more evident in the Graphical User 
		//Interfaces chapter, where callbacks are used everywhere to implement GUI functionality.
		public void increment() 
		{
			// Specify outer-class method, otherwise
			// you'd get an infinite recursion:
			Callee2.this.increment();
		}
	}
	
	Incrementable getCallbackReference() 
	{
		return new Closure();
	}
}	

class Caller {
  private Incrementable callbackReference;
  Caller(Incrementable cbh) { callbackReference = cbh; }
  void go() { callbackReference.increment(); }
}

public class Callbacks {
  public static void main(String[] args) {
    Callee1 c1 = new Callee1();
    Callee2 c2 = new Callee2();
    MyIncrement.f(c2); //输出other operation，输出1
    Caller caller1 = new Caller(c1);
    Caller caller2 = new Caller(c2.getCallbackReference());
    caller1.go(); // 输出1
    caller1.go(); // 输出2
    caller2.go();// 输出other operation，输出2
    caller2.go(); //输出other operation，输出3
  }	
} /* Output:
Other operation   //SUPER.increment()
1
1
2
Other operation
2
Other operation
3
*///:~
