package core;

import java.util.ArrayList;

public class CityEntity
{
	private ArrayList<BuildingEntity> buildingEntries;
	private CityData cityData;
	
	public CityEntity(ArrayList<BuildingEntity> buildingEntries, CityData cityData) {
		super();
		this.buildingEntries = buildingEntries;
		this.cityData = cityData;
	}
	
	public CityEntity() {
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
