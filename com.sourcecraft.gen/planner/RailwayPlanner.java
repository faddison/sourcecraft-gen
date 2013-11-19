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
		int railID = 27; 		// rail has id 66, activator rail 157, powered rail 27, detector rail 28
		int torchID = 76;		// redstone torch is 76 for on, 75 for off
		// 41: gold; 57: diamond
		int wallHeight = 4;
		int wallID = 45;		// brick wall ID = 45
		
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
		
		int xQuotient = maxX / cellLength;
		int zQuotient = maxZ / cellLength;
		int finalX = (xQuotient + 1) * cellLength;
		int finalZ = (zQuotient + 1) * cellLength;
		
		// generate torches
		for (int x = minX; x <= finalX + cellLength; x++)
		{
			for (int z = minZ; z <= finalZ + cellLength; z++)
			{
				int xJump = x % cellLength;
				int zJump = z % cellLength;
				

				if ( xJump == 0 || zJump == 0 ){
					writer.print(String.format("%d %d %d %d\n", torchID, x - 2, 0, z - 2));
					writer.print(String.format("%d %d %d %d\n", torchID, x , 0, z));
				}
			}
		}
		// generate rails
		for (int x = minX; x <= finalX + cellLength; x++)
		{
			for (int z = minZ; z <= finalZ + cellLength; z++)
			{
				int xJump = x % cellLength;
				int zJump = z % cellLength;
				

				if ( xJump == 0 || zJump == 0 ){
					writer.print(String.format("%d %d %d %d\n", railID, x - 1, 0, z - 1));
				}
			}
		}
		// for generating a wall instead of a grid around the city
		for (int x = minX - 3; x <= finalX + 1 + cellLength; x++)
		{
			for (int z = minZ - 3; z <= finalZ + 1 + cellLength; z++)
			{
				for (int y = 0; y <= wallHeight; y++)
				{
					if(x == minX - 3 || x == finalX + 1 + cellLength|| z == minZ - 3 || z == finalZ + 1 + cellLength)
						{
						writer.print(String.format("%d %d %d %d\n", wallID, x, y, z));
						}
				}
			}
		}
	}
	
	
	
}
