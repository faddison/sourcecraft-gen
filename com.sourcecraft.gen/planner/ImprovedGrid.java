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

		HashMap<Integer, ArrayList<BuildingEntity>> buildingSizes = new HashMap<Integer, ArrayList<BuildingEntity>>();
		
		for (BuildingEntity b: cityEntity.getBuildingEntries())
		{
			int power = Mathematician.ceilLog2((b.getBuildingData().getLength()));
			if (!buildingSizes.containsKey(power))
				buildingSizes.put(power, new ArrayList<BuildingEntity>());
			ArrayList<BuildingEntity> list = buildingSizes.get(power);
			list.add(b);
			buildingSizes.put(power, list);
		}
		
		ArrayList<ArrayList<BuildingEntity>> buildingList = new ArrayList<ArrayList<BuildingEntity>>();
		
		int maxPower = Mathematician.ceilLog2((cityEntity.getCityData().getMaxLength()));
		int elements = 0;
		
		for (int power: buildingSizes.keySet())
		{
			elements = getNumElements(maxPower, power);
			buildingList.addAll(divideList(buildingSizes.get(power), elements));
		}
		
		return buildingList;
	}
	
	private int getNumElements(int maxPower, int power)
	{
		int elements = 0;
		if (maxPower == power)
			elements = 1;
		else 
		{
			// this is to allow for building padding
			elements = (int) ((Math.pow(2, maxPower)*Math.pow(2, maxPower))/ (Math.pow(2, power)*Math.pow(2, power)));
			if (power < (maxPower / 2) + 1)
				elements -= (int) (Math.pow(2, maxPower)/Math.pow(2, power)) * 2;
		}
		return elements;
	}
	
	private ArrayList<ArrayList<BuildingEntity>> divideList(ArrayList<BuildingEntity> list, int n)
	{
		ArrayList<ArrayList<BuildingEntity>> newList = new ArrayList<ArrayList<BuildingEntity>>();
		
		int i = 0;
		// floor power ensures there are no null buildings and each cell is full
		// for this luxury we must sacrifice a few buildings, in some cases a lot.
		int limit = Mathematician.floor2ndPower(list.size());
		limit = (limit == 0)? 1 : limit; 
		limit = list.size();
		for (i = 0; i < limit; i += n)
		{
			ArrayList<BuildingEntity> subList = new ArrayList<BuildingEntity>();
			for (int j = i; j < i + n; j++)
			{
				if (j < list.size())
					subList.add(list.get(j));
				else
					break; // make sure to check for this later
			}
			newList.add(subList);
		}
		return newList;
	}
}
