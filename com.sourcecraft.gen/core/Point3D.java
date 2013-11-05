package core;

public class Point3D {
	private int x;
	private int y;
	private int z;
	
	public Point3D(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	
	
	public Point3D translate(int x, int y, int z)
	{
		return new Point3D(this.x + x, this.y + y, this.z + z);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
}
