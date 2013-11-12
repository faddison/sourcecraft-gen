package core;

public class CityData {


	private int maxHeight;
	private int maxLength;
	private int maxWidth;
	
	public CityData(int maxHeight, int maxLength, int maxWidth) {
		super();
		this.maxHeight = maxHeight;
		this.maxLength = maxLength;
		this.maxWidth = maxWidth;
	}
	
	public CityData() {};
	
	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}
	
}
