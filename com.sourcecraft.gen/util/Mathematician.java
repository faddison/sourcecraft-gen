package util;

public class Mathematician 
{
	public static int nthRoot(int n, int root)
	{
		return (int) Math.ceil(Math.pow(n, 1.0/4));
	}
	
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
	
	public static int ceilSqrt(int n)
	{
		return (int) Math.ceil(Math.sqrt(n));
	}
	
	public static int floorSqrt(int n)
	{
		return (int) Math.floor(Math.sqrt(n));
	}
	
	public static int ceilLog2(int n)
	{
		return (int) Math.ceil(Math.log(n)/Math.log(2));
	}
	
	public static int floorLog2(int n)
	{
		return (int) Math.floor(Math.log(n)/Math.log(2));
	}
	
	public static int floor2ndPower(int n)
	{
		return (int) Math.pow(2, floorLog2(n));
	}
	
	public static int ceil2ndPower(int n)
	{
		return (int) Math.pow(2, ceilLog2(n));
	}
}
