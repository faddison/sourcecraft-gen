package planner;

import java.util.ArrayList;
import java.util.HashMap;

import util.Mathematician;
import core.BuildingEntity;
import core.CityEntity;

public class ImprovedGrid 
{	
	public ArrayList<ArrayList<BuildingEntity>> generate(CityEntity cityEntity)
	{

		cityEntity.getBuildingEntries();
		HashMap<Integer, ArrayList<BuildingEntity>> buildingSizes = new HashMap<Integer, ArrayList<BuildingEntity>>();
		
		for (BuildingEntity b: cityEntity.getBuildingEntries())
		{
			int power = Mathematician.get4thRoot(b.getBuildingData().getLength());
			if (!buildingSizes.containsKey(power))
				buildingSizes.put(power, new ArrayList<BuildingEntity>());
			ArrayList<BuildingEntity> list = buildingSizes.get(power);
			list.add(b);
			buildingSizes.put(power, list);
		}
		
		ArrayList<ArrayList<BuildingEntity>> buildingList = new ArrayList<ArrayList<BuildingEntity>>();
		
		int maxPower = Mathematician.get4thRoot((cityEntity.getCityData().getMaxLength()));
		int elements = 0;
		
		for (int power: buildingSizes.keySet())
		{
			elements = (power == maxPower)? 1 : (int) Math.pow(4, (maxPower - power));
			buildingList.addAll(divideList(buildingSizes.get(power), elements));
		}
		
		return buildingList;
	}
	
	private ArrayList<ArrayList<BuildingEntity>> divideList(ArrayList<BuildingEntity> list, int n)
	{
		ArrayList<ArrayList<BuildingEntity>> newList = new ArrayList<ArrayList<BuildingEntity>>();
		
		int i = 0;
		for (i = 0; i < list.size(); i += n)
		{
			ArrayList<BuildingEntity> subList = new ArrayList<BuildingEntity>();
			for (int j = i; j < i + n; j++)
			{
				if (j < list.size())
					subList.add(list.get(j));
				else
					subList.add(new BuildingEntity(null, null, null)); // make sure to check for this later
			}
			newList.add(subList);
		}
		return newList;
	}
}
