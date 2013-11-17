package core;

import java.util.ArrayList;

import metrics.AbstractMetrics;
import metrics.SimpleClassMetrics;

public class BuildingEntity implements Comparable<BuildingEntity>
{
	private SimpleClassMetrics metrics;
	private ArrayList<BlockEntity> blockEntries;
	private BuildingData buildingData;
	public BuildingEntity(SimpleClassMetrics metrics,
			ArrayList<BlockEntity> blockEntries, BuildingData buildingData) {
		super();
		this.metrics = metrics;
		this.blockEntries = blockEntries;
		this.buildingData = buildingData;
	}
	public SimpleClassMetrics getMetrics() {
		return metrics;
	}
	public void setMetrics(SimpleClassMetrics metrics) {
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
