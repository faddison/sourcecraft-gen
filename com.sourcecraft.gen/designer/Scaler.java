package designer;

public class Scaler
{
	public int scale(int n, ScalerType type)
	{
		switch (type)
		{
		case FACTOR:
			return factorScale(n);
		case LOGARITHMIC:
			return logarithmicScale(n);
		default:
			return 0;
		}
	}
	
	private int factorScale(int n)
	{
		return (int) Math.ceil(n/5);
	}
	
	private int logarithmicScale(int n)
	{
		return (int) Math.log(n);
	}
}
