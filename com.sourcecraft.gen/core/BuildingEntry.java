package core;

import java.util.ArrayList;

import metrics.AbstractMetrics;

public class BuildingEntry 
{
	private AbstractMetrics metrics;
	private ArrayList<BlockEntry> blockEntries;
	private BuildingData buildingData;
	public BuildingEntry(AbstractMetrics metrics,
			ArrayList<BlockEntry> blockEntries, BuildingData buildingData) {
		super();
		this.metrics = metrics;
		this.blockEntries = blockEntries;
		this.buildingData = buildingData;
	}
	public AbstractMetrics getMetrics() {
		return metrics;
	}
	public void setMetrics(AbstractMetrics metrics) {
		this.metrics = metrics;
	}
	public ArrayList<BlockEntry> getBlockEntries() {
		return blockEntries;
	}
	public void setBlockEntries(ArrayList<BlockEntry> blockEntries) {
		this.blockEntries = blockEntries;
	}
	public BuildingData getBuildingData() {
		return buildingData;
	}
	public void setBuildingData(BuildingData buildingData) {
		this.buildingData = buildingData;
	}
	
}
