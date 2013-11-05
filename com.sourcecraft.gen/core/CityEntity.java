package core;

import java.util.ArrayList;

import metrics.AbstractMetrics;

public class CityEntity<T extends AbstractMetrics>
{
	private ArrayList<T> classMetricsList;
	private ArrayList<BuildingEntity> buildingEntries;
	private CityData cityData;
	
	public CityEntity(ArrayList<T> classMetricsList,
			ArrayList<BuildingEntity> buildingEntries, CityData cityData) {
		super();
		this.classMetricsList = classMetricsList;
		this.buildingEntries = buildingEntries;
		this.cityData = cityData;
	}
	public ArrayList<T> getClassMetricsList() {
		return classMetricsList;
	}
	public void setClassMetricsList(ArrayList<T> classMetricsList) {
		this.classMetricsList = classMetricsList;
	}
	public ArrayList<BuildingEntity> getBuildingEntries() {
		return buildingEntries;
	}
	public void setBuildingEntries(ArrayList<BuildingEntity> buildingEntries) {
		this.buildingEntries = buildingEntries;
	}
	public CityData getCityData() {
		return cityData;
	}
	public void setCityData(CityData cityData) {
		this.cityData = cityData;
	}
}
