package planner;

import java.awt.Point;
import java.io.PrintWriter;
import java.util.ArrayList;

import util.Mathematician;
import core.BlockEntity;
import core.BuildingEntity;
import core.CityEntity;
import core.Point3D;

public class ImprovedGridPlanner extends AbstractPlanner
{

	private static int total_blocks = 0;
	
	@Override
	public String plan(CityEntity cityEntry, String filename) 
	{
		
		try 
		{
			PrintWriter writer;
			writer = new PrintWriter(filename, "UTF-8");
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
		
		int count = 1;
		for (int cellIndex = 0; cellIndex < buildingList.size(); cellIndex++)
		{
			Point p = cellLocations.get(cellIndex);
			ArrayList<BuildingEntity> list = buildingList.get(cellIndex);
			int cellOffset = getCellOffset(list.size(), maxPower);
			int i = 0;
			int j = 0;
			int k = 0;
			
			// this loop alternates between incrementing i and j by checking if k is even
			for (BuildingEntity building: list)
			{
				int x = p.x + i * cellOffset;
				int z = p.y + j * cellOffset;
				
				// sometimes we put null buildings inside, just for fun
				if (building != null)
				{
					System.out.println(String.format("Writing building %d",count));
					placeBuildingBlocks(writer, building, x, 0, z);
					count++;
				}
				
				if (Mathematician.isEven(k))
					i++;
				else
					j++;
				k++;
			}
		}	
	}
	
	private void placeBuildingBlocks(PrintWriter writer, BuildingEntity building, int xOffset, int yOffset, int zOffset)
	{
		for (BlockEntity blockEntity: building.getBlockEntries())
		{
			Point3D p = blockEntity.getPoint().translate(xOffset,  yOffset, zOffset);
			//System.out.println(String.format("Placing block: %d, %d, %d", p.getX(), p.getY(), p.getZ()));
			writer.print(String.format("%d %d %d %d\n", blockEntity.getBlockData().getId(), p.getX(), p.getY(), p.getZ()));	
		}
	}
	
	private ArrayList<Point> getCellLocations(int maxLength, int numCells)
	{
		ArrayList<Point> list = new ArrayList<Point>();
		int size = (int) Math.ceil(numCells / 2);
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				list.add(new Point(i*maxLength, j*maxLength));
			}
		}
		return list;
	}
	
	private ArrayList<Point> getCellLocationsSpiral(int maxLength, int numCells)
	{
		return new ArrayList<Point>();
	}
	
	/*
	 * Given the size of the list and the maximum power of 4 determine where to place
	 * smaller buildings within the full size of the cell. The max power corresponds 
	 * to the dimensions of the largest cell, 4 ^ maxPower.
	 * 
	 * For example, if the largest cell is size 16x16 and the list size is 4 (max power = 2), 
	 * then we need to place those four buildings at 
	 * the right offsets in the master cell. In the 16x16 case the Cell offset returned by 
	 * this method would be 4. If the list size was 8, then the offset would be 2.
	 * 
	 * The list size should be guaranteed to be a power of 4
	 */
	private int getCellOffset(int listSize, int maxPower)
	{
		return (int)Math.pow(4, maxPower)/listSize;
	}
	
	
}
