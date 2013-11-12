package planner;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import core.BuildingEntity;
import core.CityEntity;
import metrics.SimpleClassMetrics;

public class ImprovedGrid 
{
	ArrayList<BuildingCell> buildingCells;
	
	public ArrayList<Point> generate(CityEntity cityEntity)
	{

		cityEntity.getBuildingEntries();
		HashMap<Integer, ArrayList<BuildingEntity>> buildingSizes = new HashMap<Integer, ArrayList<BuildingEntity>>();
		
		for (BuildingEntity b: cityEntity.getBuildingEntries())
		{
			int power = get4thPower(b.getBuildingData().getLength());
			if (!buildingSizes.containsKey(power))
				buildingSizes.put(power, new ArrayList<BuildingEntity>());
			ArrayList<BuildingEntity> list = buildingSizes.get(power);
			list.add(b);
			buildingSizes.put(power, list);
		}
		
		ArrayList<ArrayList<BuildingEntity>> buildingList = new ArrayList<ArrayList<BuildingEntity>>();
		
		int maxPower = get4thPower(cityEntity.getCityData().getMaxLength());
		int elements = 0;
		
		for (int power: buildingSizes.keySet())
		{
			elements = (maxPower == power)? 1 : (maxPower - power) * 4;
			buildingList.addAll(divideList(buildingSizes.get(power), elements));
		}
		
		return null;
	}
	
	private int get4thPower(int n)
	{
		return (int) Math.ceil(Math.pow(n, 1 / 4));
	}
	
	private ArrayList<ArrayList<BuildingEntity>> divideList(ArrayList<BuildingEntity> list, int n)
	{
		ArrayList<ArrayList<BuildingEntity>> newList = new ArrayList<ArrayList<BuildingEntity>>();
		
		int i = 0;
		for (i = 0; i < list.size(); i += n);
		{
			ArrayList<BuildingEntity> subList = new ArrayList<BuildingEntity>();
			for (int j = i; j <  + n; j++)
			{
				if (j < list.size())
					subList.add(list.get(j));
				else
					break;
			}
			newList.add(subList);
		}
		return newList;
	}
}
