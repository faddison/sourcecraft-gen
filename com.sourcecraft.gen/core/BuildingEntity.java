package core;

import java.util.ArrayList;

import metrics.AbstractMetrics;

public class BuildingEntity implements Comparable<BuildingEntity>
{
	private AbstractMetrics metrics;
	private ArrayList<BlockEntity> blockEntries;
	private BuildingData buildingData;
	public BuildingEntity(AbstractMetrics metrics,
			ArrayList<BlockEntity> blockEntries, BuildingData buildingData) {
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
	public ArrayList<BlockEntity> getBlockEntries() {
		return blockEntries;
	}
	public void setBlockEntries(ArrayList<BlockEntity> blockEntries) {
		this.blockEntries = blockEntries;
	}
	public BuildingData getBuildingData() {
		return buildingData;
	}
	public void setBuildingData(BuildingData buildingData) {
		this.buildingData = buildingData;
	}
	

	@Override
	public int compareTo(BuildingEntity buildingEntity) 
	{	
		return buildingEntity.buildingData.getLength() - this.buildingData.getLength();
	}
	
}
