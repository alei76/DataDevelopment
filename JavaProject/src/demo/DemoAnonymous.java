package demo;


public class DemoAnonymous{

	public Contents contents() 
	{
		return new Contents()
		{ // Insert a class definition
			private int i = 11;

			public int value() {
				return i;
			}
		}; // Semicolon required in this case
	}//inner

	public static void main(String[] args) {
		new DemoAnonymous().contents();
	}
} // /:~ 