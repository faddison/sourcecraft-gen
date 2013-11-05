package designer;

public class DesignerHelper 
{
	public boolean shouldSetBlock(int x, int y, int z, int x_offset, int z_offset, int height, int dimension)
	{
		return ((y == height - 1) ||
				(x == x_offset + dimension - 1) ||
				(z == z_offset + dimension - 1) ||
				(x == x_offset) ||
				(z == z_offset));
	}
}
