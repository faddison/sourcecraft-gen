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
		int iRailID = 66;
		int wallHeight = 4;
		int wallID = 45;		// brick wall ID = 45
		int railJump = cellLength + 3;
		int groundID = 3; 		// 2 for grass, 3 for dirt

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

		int xQuotient = maxX / railJump;
		int zQuotient = maxZ / railJump;
		int finalX = (xQuotient + 1) * railJump;
		int finalZ = (zQuotient + 1) * railJump;

		// generate torches
		for (int x = minX; x <= finalX + railJump; x++)
		{
			for (int z = minZ; z <= finalZ + railJump; z++)
			{
				int xJump = x % railJump;
				int zJump = z % railJump;


				if ( xJump == 0 || zJump == 0 ){
					writer.print(String.format("%d %d %d %d\n", torchID, x - 2, 0, z - 2));
					writer.print(String.format("%d %d %d %d\n", torchID, x , 0, z));
				}
			}
		}
		// generate rails at non-intersections
		for (int x = minX; x <= finalX + railJump; x++)
		{
			for (int z = minZ; z <= finalZ + railJump; z++)
			{
				int xJump = x % railJump;
				int zJump = z % railJump;


				if ( xJump == 0 || zJump == 0 ){
					writer.print(String.format("%d %d %d %d\n", railID, x - 1, 0, z - 1));
				}
				
			}
		}
		// generate rails at non-central intersections
				for (int x = minX; x <= finalX + railJump; x++)
				{
					for (int z = minZ; z <= finalZ + railJump; z++)
					{
						int xJump = x % railJump;
						int zJump = z % railJump;


						if ( xJump == 0 && zJump == 0 && (x == minX || x == finalX + railJump || z == minZ || z == finalZ + railJump)){
							
							if (x != finalX + railJump)
								writer.print(String.format("%d %d %d %d\n", railID, x, 0, z - 1));
							if (z != minZ)
								writer.print(String.format("%d %d %d %d\n", railID, x - 1, 0, z - 2));
							if (x != minX)
								writer.print(String.format("%d %d %d %d\n", railID, x - 2, 0, z - 1));
							if (x != finalZ + railJump)
								writer.print(String.format("%d %d %d %d\n", railID, x - 1, 0, z));
							
							writer.print(String.format("%d %d %d %d\n", iRailID, x - 1, 0, z - 1));
							
							
						}
						
					}
				}
		
		

		// for generating a wall instead of a grid around the city
		for (int x = minX - 3; x <= finalX + railJump + 1; x++)
		{
			for (int z = minZ - 3; z <= finalZ + railJump + 1; z++)
			{
				for (int y = 0; y <= wallHeight; y++)
				{
					if(x == minX - 3 || x == finalX + railJump + 1|| z == minZ - 3 || z == finalZ + railJump + 1)
					{
						writer.print(String.format("%d %d %d %d\n", wallID, x, y, z));
					}
				}
			}
		}
		// generate rail system at intersections
				for (int x = minX; x <= finalX + railJump; x++)
				{
					for (int z = minZ; z <= finalZ + railJump; z++)
					{
						int xJump = x % railJump;
						int zJump = z % railJump;


						if ( x != minX && x != finalX + railJump && z != minZ && z!= finalZ + railJump && xJump == 0 && zJump == 0 ){
							int centreX = x - 1;
							int centreZ = z - 1;
							// upper levels for X
							writer.print(String.format("%d %d %d %d\n", groundID, centreX - 3, 0, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX - 2, 0, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX - 1, 0, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX + 1, 0, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX + 2, 0, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX + 3, 0, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX - 2, 1, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX - 1, 1, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX + 1, 1, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX + 2, 1, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX - 1, 2, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX + 1, 2, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX, 0, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX, 1, centreZ));
							writer.print(String.format("%d %d %d %d\n", groundID, centreX, 2, centreZ));
							writer.print(String.format("%d %d %d %d\n", railID, centreX - 4, 0, centreZ));
							writer.print(String.format("%d %d %d %d\n", railID, centreX - 3, 1, centreZ));
							writer.print(String.format("%d %d %d %d\n", railID, centreX - 2, 2, centreZ));
							writer.print(String.format("%d %d %d %d\n", railID, centreX - 1, 2, centreZ));
							writer.print(String.format("%d %d %d %d\n", railID, centreX + 1, 2, centreZ));
							writer.print(String.format("%d %d %d %d\n", railID, centreX + 2, 2, centreZ));
							writer.print(String.format("%d %d %d %d\n", railID, centreX + 3, 1, centreZ));
							writer.print(String.format("%d %d %d %d\n", railID, centreX + 4, 0, centreZ));
							writer.print(String.format("%d %d %d %d\n", railID, centreX, 2, centreZ));
							writer.print(String.format("%d %d %d %d\n", 0, centreX, 0, centreZ));
							writer.print(String.format("%d %d %d %d\n", 0, centreX, 1, centreZ));
							
							// lower levels for z
							writer.print(String.format("%d %d %d %d\n", 0, centreX, -1, centreZ - 1));
							writer.print(String.format("%d %d %d %d\n", 0, centreX, -1, centreZ));
							writer.print(String.format("%d %d %d %d\n", 0, centreX, -1, centreZ + 1));
							writer.print(String.format("%d %d %d %d\n", railID, centreX, -1, centreZ - 2));
							writer.print(String.format("%d %d %d %d\n", railID, centreX, -2, centreZ - 1));
							writer.print(String.format("%d %d %d %d\n", railID, centreX, -2, centreZ + 1));
							writer.print(String.format("%d %d %d %d\n", railID, centreX, -1, centreZ + 2));
							writer.print(String.format("%d %d %d %d\n", railID, centreX, -2, centreZ));

						}
					}
				}
		
	}



}
