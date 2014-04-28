//: innerclasses/controller/Controller.java
// The reusable framework for control systems.
package innerclasses.controller;
import java.util.*;

public class Controller {
  // A class from java.util to hold Event objects:
  private List<Event> eventList = new ArrayList<Event>();
  public void addEvent(Event c) { eventList.add(c); }
  public void run() {
    while(eventList.size() > 0)
      // Make a copy so you're not modifying the list
      // while you're selecting the elements in it:
      for(Event e : new ArrayList<Event>(eventList))
        if(e.ready()) {
          System.out.println(e);
          e.action();
          eventList.remove(e);
        }
  }
} ///:~

/*Note that so far in this design you know nothing about exactly what an Event does. And this 
is the crux of the design¡ªhow it "separates the things that change from the things that stay 
the same." Or, to use my term, the "vector of change" is the different actions of the various 
kinds of Event objects, and you express differentactions by creating different Event 
subclasses. */

//This is where inner classes come into play. They allow two things: 
//1.  The entire implementation of a control framework is created in a single class, thereby 
//encapsulating everything that¡¯s unique about that implementation. Inner classes are 
//used to express the many different kinds of action( ) necessary to solve the problem. 
//2.  Inner classes keep this implementation frombecoming awkward, since you¡¯re able to 
//easily access any of the members in the outer class. Without this ability the code might 
//become unpleasant enough that you¡¯d end up seeking an alternative. 