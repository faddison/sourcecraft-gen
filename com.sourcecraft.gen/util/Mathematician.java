package util;

public class Mathematician 
{
	public static int get4thRoot(int n)
	{
		return (int) Math.ceil(Math.pow(n, 1.0/4));
	}
	
	public static int get4thPower(int n)
	{
		return (int) Math.pow(4, get4thRoot(n));
	}
	
	public static boolean isEven(int n)
	{
		return (n % 2 == 0);
	}
	
	
}
