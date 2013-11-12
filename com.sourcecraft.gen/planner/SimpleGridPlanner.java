package planner;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.lang.Math;

import core.BlockEntity;
import core.BuildingEntity;
import core.CityEntity;
import core.Point3D;
import parser.SimpleClassParser;
import metrics.SimpleClassMetrics;

public class SimpleGridPlanner extends AbstractPlanner
{

	private static int total_blocks = 0;
	private static boolean scale = false;
	
	@Override
	public String plan(CityEntity cityEntry) 
	{
		
		try 
		{
			PrintWriter writer;
			writer = new PrintWriter("box.txt", "UTF-8");
			buildPlans(writer, cityEntry);
			writer.close();
			System.out.println(String.format("lines : %d", total_blocks));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void buildPlans(PrintWriter writer, CityEntity cityEntry)
	{
		ImprovedGrid grid2 = new ImprovedGrid();
		grid2.generate(cityEntry);
		SimpleGrid grid = new SimpleGrid(cityEntry.getClassMetricsList());
		for (int i = 0; i < cityEntry.getBuildingEntries().size(); i++)
		{
			Point cell = grid.getCells().get(i);
			BuildingEntity buildingEntry = (BuildingEntity) cityEntry.getBuildingEntries().get(i);
			for (int j = 0; j < buildingEntry.getBlockEntries().size(); j++)
			{
				BlockEntity blockEntry = buildingEntry.getBlockEntries().get(j);
				Point3D p = blockEntry.getPoint().translate(cell.x, 0, cell.y);
				writer.print(String.format("%d %d %d %d", blockEntry.getBlockData().getId(), p.getX(), p.getY(), p.getZ()));
			}
		}		
	}
	
	private ArrayList<SimpleClassMetrics> filterClassList(ArrayList<SimpleClassMetrics> classList)
	{
		
		int maxBuildings = 50;
		
		ArrayList<SimpleClassMetrics> newList = new ArrayList<SimpleClassMetrics>();
		
		for (SimpleClassMetrics c: classList)
		{
			if (isValidBuilding(c) && newList.size() < maxBuildings + 1)
				newList.add(c);
		}
		return newList;
	}
	
	private boolean isValidBuilding(SimpleClassMetrics c)
	{
		int minDimension = 5;
		int maxDimension = 10;
		int minHeight = 5;
		int maxHeight = 50;
		
		return ((c.getNumAttributes() > minDimension) &&
				(c.getNumAttributes() < maxDimension) &&
				(c.getNumMethods() > minHeight) &&
				(c.getNumMethods() < maxHeight));
	}
}
