package core;

public class BlockData
{
	public static final int STONE = 1;
	public static final int DIRT = 3;
	public static final int LAVA = 11;
	public static final int GLASS = 20;
	public static final int BRICK = 45;
	
	private int id;

	public BlockData(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
