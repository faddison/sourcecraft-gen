package planner;

import java.util.ArrayList;

import core.BuildingData;
import core.BuildingEntity;

public class PlannerHelper 
{
	public ArrayList<BuildingEntity> filterBuildingEntities(ArrayList<BuildingEntity> buildingEntities)
	{
		
		int maxBuildings = 50;
		
		ArrayList<BuildingEntity> list = new ArrayList<BuildingEntity>();
		
		for (BuildingEntity b: buildingEntities)
		{
			if (isValidBuilding(b.getBuildingData()) && list.size() < maxBuildings + 1)
				list.add(b);
		}
		return list;
	}
	
	public boolean isValidBuilding(BuildingData d)
	{
		int minDimension = 5;
		int maxDimension = 10;
		int minHeight = 5;
		int maxHeight = 50;
		
		return ((d.getLength() > minDimension) &&
				(d.getLength() < maxDimension) &&
				(d.getHeight() > minHeight) &&
				(d.getHeight() < maxHeight));
	}
}
