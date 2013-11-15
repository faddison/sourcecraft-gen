package designer;

import java.util.ArrayList;

import metrics.SimpleClassMetrics;
import core.BuildingData;
import core.BuildingEntity;

public class DesignerHelper 
{
	public boolean shouldSetBlock(int x, int y, int z, int x_offset, int z_offset, int height, int dimension)
	{
		return ((y == height - 1) ||
				(x == x_offset + dimension - 1) ||
				(z == z_offset + dimension - 1) ||
				(x == x_offset) ||
				(z == z_offset));
	}
	
	public ArrayList<SimpleClassMetrics> filterBuildingEntities(ArrayList<SimpleClassMetrics> classMetricsList)
	{
		
		int maxBuildings = 5000;
		
		ArrayList<SimpleClassMetrics> list = new ArrayList<SimpleClassMetrics>();
		
		for (SimpleClassMetrics c: classMetricsList)
		{
			if (isValidBuilding(c) && list.size() < maxBuildings + 1)
				list.add(c);
		}
		return list;
	}
	
	public boolean isValidBuilding(SimpleClassMetrics c)
	{
		int minDimension = 1;
		int maxDimension = 300;
		int minHeight = 1;
		int maxHeight = 300;
		
		return ((c.getNumAttributes() > minDimension) &&
				(c.getNumAttributes() < maxDimension) &&
				(c.getNumMethods() > minHeight) &&
				(c.getNumMethods() < maxHeight));
	}
}
