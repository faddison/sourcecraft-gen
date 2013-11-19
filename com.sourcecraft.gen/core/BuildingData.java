package core;

import java.util.ArrayList;

import decorator.BugData;
import decorator.BugDecorator;

public class BuildingData {

	private int length;
	private int width;
	private int height;
	private int blocks;
	private ArrayList<BugData> decorateList;
	
	public BuildingData() {
		this.decorateList = new ArrayList<BugData>();
	}
	
	public BuildingData(int length, int width, int height) {
		super();
		this.length = length;
		this.width = width;
		this.height = height;
		this.decorateList = new ArrayList<BugData>();
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
	
	public ArrayList<BugData> getDecorateList() {
		return this.decorateList;
	}

	// Only add highest priority bug for each method
	public void addToDecorateList(BugData bug) {
		if (shouldAddDecorateList(bug)) {
			this.decorateList.add(bug);
		}
	}
	
	private boolean shouldAddDecorateList(BugData bug) {
		if (bug.getMethodName() == null) {
			BugDecorator.methodsWithoutNames++;
			return false;
		}
		if (this.decorateList.isEmpty()) {
			return true;
		}
			// If bug entry exists with a lower priority, 
			// remove lower priority bug in same method, then replace with the higher priority
		
		for (BugData b : this.decorateList) {
			if (b.getMethodName().equals(bug.getMethodName())) {
				if (b.getBugType() == bug.getBugType()) {
					BugDecorator.bugEquallyBad++;
					return false;
				}
				else if (b.getBugType() > bug.getBugType()) {
					BugDecorator.bugLessBad++;
					return false;
				}
				else {
					this.decorateList.remove(b);
					BugDecorator.bugReplaceWithWorse++;
					return true;
				}
			}
		}
		
		return true;
	}
}

