package planner;

import java.awt.Point;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import util.Mathematician;
import core.BlockEntity;
import core.BuildingEntity;
import core.CityEntity;
import core.Point3D;

public class RailwayPlanner extends AbstractPlanner
{

	private static int total_blocks = 0;
	private BlockWriter blockWriter = new BlockWriter();
	
	
	@Override
	public String plan(CityEntity cityEntry, String filename) 
	{
		
		try 
		{
			PrintWriter writer;
			writer = new PrintWriter(filename, "UTF-8");
			//buildPlans(writer, cityEntry);
			writer.close();
			System.out.println(String.format("lines : %d", total_blocks));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String setPaths(CityEntity cityEntry, String filename, ArrayList<Point> cellLocations, int cellLength)//, BlockWriter writer)
	{
		try
		{
			PrintWriter writer;
			writer = new PrintWriter(filename, "UTF-8");
			buildRails(writer, cityEntry, cellLocations, cellLength);
			writer.close();
			System.out.println(String.format("lines : %d", total_blocks));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void buildRails(PrintWriter writer, CityEntity cityEntry, ArrayList<Point> cellLocations, int cellLength)
	{
		int maxX = -1;			// easy to override because we have no negative numbers
		int maxZ = -1;			// easy to override because we have no negative numbers
		int minX = 1000000000;  // easy to override
		int minZ = 1000000000;  // easy to override
		int railID = 41; 		// rail has id 66, activator rail 157, powered rail 27, detector rail 28
		int torchID = 57;		// redstone torch is 76 for on, 75 for off
		
		for (Point p: cellLocations){
			int currX = p.x;
			int currZ = p.y;
			if (currX < minX)
				minX = currX;
			if (currX > maxX)
				maxX = currX;
			if (currZ < minZ)
				minZ = currZ;
			if (currZ > maxZ)
				maxZ = currZ;
		}
		
		
		for (int x = minX; x <= maxX + cellLength; x++)
		{
			for (int z = minZ; z <= maxZ + cellLength; z++)
			{
				int xJump = x % cellLength;
				int zJump = z % cellLength;
				int xQuotient = maxX / cellLength;
				int zQuotient = maxZ / cellLength;
				int finalX = (xQuotient + 1) * cellLength;
				int finalZ = (zQuotient + 1) * cellLength;
				

				if ( xJump == 0 || zJump == 0 ){
					writer.print(String.format("%d %d %d %d\n", torchID, x - 2, 0, z - 2));
					writer.print(String.format("%d %d %d %d\n", railID, x - 1, 0, z - 1));
					writer.print(String.format("%d %d %d %d\n", torchID, x , 0, z));
				}
			}
		}
		// for generating a wall instead of a grid around the city
//		int wallHeight = 10;
//		int wallID = 45;		// brick wall ID
//		for (int x = minX; x <= maxX; x++)
//		{
//			for (int z = minZ; z <= maxZ; z++)
//			{
//				for (int y = 0; y <= wallHeight; y++)
//				{
//					if(x == minX || x == (int) maxX / cellLength || z == minZ || z == (int) maxZ / cellLength)
//						{
//						writer.print(String.format("%d %d %d %d\n", wallID, x, y, z));
//						}
//				}
//			}
//		}
	}
	
	
	
}
