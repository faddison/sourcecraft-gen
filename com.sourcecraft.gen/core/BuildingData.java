package core;

public class BuildingData {

	private int length;
	private int width;
	private int height;
	private int blocks;
	
	public BuildingData()
	{}
	
	
	public BuildingData(int length, int width, int height) {
		super();
		this.length = length;
		this.width = width;
		this.height = height;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public int getBlocks() {
		return blocks;
	}


	public void setBlocks(int blocks) {
		this.blocks = blocks;
	}
}
