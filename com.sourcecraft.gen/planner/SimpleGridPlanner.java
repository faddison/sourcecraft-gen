package planner;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.lang.Math;

import core.BlockEntity;
import core.BuildingData;
import core.BuildingEntity;
import core.CityEntity;
import core.Point3D;
import parser.SimpleClassParser;
import util.Mathematician;
import metrics.SimpleClassMetrics;

public class SimpleGridPlanner extends AbstractPlanner
{

	private static int total_blocks = 0;
	
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
		ImprovedGrid grid = new ImprovedGrid();
		
		ArrayList<ArrayList<BuildingEntity>> buildingList = grid.generate(cityEntry);		
		
		int maxLength = Mathematician.get4thPower(cityEntry.getCityData().getMaxLength());
		int maxPower = Mathematician.get4thRoot(cityEntry.getCityData().getMaxLength());
		
		ArrayList<Point> cellLocations = getCellLocations(maxLength, buildingList.size());
		
		for (Point p: cellLocations)
		{
			for (ArrayList<BuildingEntity> list: buildingList)
			{
				int cellOffset = getCellOffset(list.size(), maxPower);
				for (int i = 0; i < list.size() / 2; i++)
				{
					for (int j = 0; j < list.size() / 2; j++)
					{
						BuildingEntity building = list.get(i+j);
						int x = p.x + i * cellOffset;
						int z = p.y + j * cellOffset;
						placeBuildingBlocks(writer, building, x, 0, z);
					}
				}
			}	
		}	
	}
	
	private void placeBuildingBlocks(PrintWriter writer, BuildingEntity building, int xOffset, int yOffset, int zOffset)
	{
		for (BlockEntity blockEntity: building.getBlockEntries())
		{
			Point3D p = blockEntity.getPoint().translate(xOffset,  yOffset, zOffset);
			writer.print(String.format("%d %d %d %d", blockEntity.getBlockData().getId(), p.getX(), p.getY(), p.getZ()));	
		}
	}
	
	private ArrayList<Point> getCellLocations(int maxLength, int numCells)
	{
		return new ArrayList<Point>();
	}
	
	private int getCellOffset(int listSize, int maxPower)
	{
		return 0;
	}
	
	
}
