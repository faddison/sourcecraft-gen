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

		// sort in descending order of length
		Collections.sort(cityEntity.getBuildingEntries());
		// build a map to store number and size of cells required by classes
		HashMap<BuildingEntity, Integer> gridSizes = new HashMap<BuildingEntity, Integer>();
		HashMap<Integer, Integer> numGrids = new HashMap<Integer, Integer>();
		
		int size = ((BuildingEntity)cityEntity.getBuildingEntries().get(0)).getBuildingData().getLength();
		int maxSize = size;
		int count = 0;
		size = (int) Math.round(Math.ceil(maxSize / 4)) * 4;
		
		for (BuildingEntity e: (ArrayList<BuildingEntity>) cityEntity.getBuildingEntries())
		{
			if (e.getBuildingData().getLength() < maxSize / 4)
				size /= 4;
			if (numGrids.containsKey(size))
				numGrids.put(size, numGrids.get(size)+1);
			else
				numGrids.put(size, 1);
			gridSizes.put(e,size);
		}
		
		int totalCells = 0;
		
		for (Integer key: numGrids.keySet())
		{
			totalCells += numGrids.get(key) / (maxSize / key);
		}
		
		return null;
	}
	
	private void generateHelper()
	{
			
	}
}
