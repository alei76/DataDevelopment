package demo;

public  class DemoChainOfResponsibility
{
	
	public static void main(String[] args)
	{
		ChainOfResponsibility chain=new ChainOfResponsibility();// 第一个node的方法为空
		chain.add(new ConcreteBusiness());
		double[] arr={2.0,3.2};
		chain.execThisAlgorithm(arr);
	}
}

class ChainOfResponsibility
{
	
	private ChainOfResponsibility successor = null;
	public void add(ChainOfResponsibility succ)//add new node to the chain 
	{ 
		ChainOfResponsibility end = this; 
		while(end.successor != null)
		{
			end = end.successor; // Traverse list end.successor = succ;
		}
		end.successor = succ;
	}
	public double[] nextAlgorithm(double[] line) 
	{ 
			if(successor != null)// Try the next one in the chain:
			{
				return successor.execThisAlgorithm(line);
			}
			else return new double[] {}; // Nothing found 
	}
	public double[] execThisAlgorithm(double[] line) 
	{
		// FindMinima algorithm() is only the start of the chain; 
		//doesn't actually try to solve the problem: 
		return nextAlgorithm(line);
	
	}

}

class ConcreteBusiness extends ChainOfResponsibility // 执行父类的方法
{
	//@Override
    public double[] execThisAlgorithm(double[] line)
	{ 	
		System.out.println("LeastSquares.algorithm");
		boolean weSucceed = false;
		if(weSucceed) // Actual test/calculation here 
			return new double[] { 1.1, 2.2 }; // Dummy 
	    else // Try the next one in the chain: 
	    	return nextAlgorithm(line);  // succ 默认为
	}
}