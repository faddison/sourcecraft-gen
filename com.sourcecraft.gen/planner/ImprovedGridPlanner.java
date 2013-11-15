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
		
		int maxLength = Mathematician.ceil2ndPower(cityEntry.getCityData().getMaxLength());
		int maxPower = Mathematician.ceilLog2(cityEntry.getCityData().getMaxLength());
		
		ArrayList<Point> cellLocations = getCellLocations(maxLength, buildingList.size());
		
		ArrayList<BuildingEntity> lostBuildings = new ArrayList<BuildingEntity>();
		
		int bcount = 1;
		int ncount = 1;
		int ntotal = 1;
		int btotal = 1;
		
		Collections.shuffle(buildingList);
		for (int cellIndex = 0; cellIndex < buildingList.size(); cellIndex++)
		{
			ncount = 0;
			bcount = 0;
			Point p = cellLocations.get(cellIndex);
			ArrayList<BuildingEntity> list = buildingList.get(cellIndex);
			int power = Mathematician.ceilLog2(list.get(0).getBuildingData().getLength());
			int cellOffset = (int) Math.pow(2,power);
			
			int xIndex = 0;
			int zIndex = 0;
			int xPadding = 0;
			int zPadding = 0;
			
			// cell offset might be wrong and number of building in grid is off by 1
			
			// this loop alternates between incrementing i and j by checking if k is even
			Collections.shuffle(list);
			for (BuildingEntity building: list)
			{
				int xOffset = (xIndex * cellOffset);// + xIndex;
				int zOffset = (zIndex * cellOffset);// + zIndex;
						
				int x = p.x + xOffset;
				int z = p.y + zOffset;
				
				
				// add padding to inside of cells
				// limit the number of buildings in each cell by counting the columns
				// and rows to account for padding
							
				// sometimes we put null buildings inside, just for fun
				if (building != null)
				{
					System.out.println(String.format("Writing building %d",bcount));
					placeBuildingBlocks(writer, building, x, 0, z);
					bcount++;
					btotal++;
					
					if (xOffset < maxLength - cellOffset - xIndex + 1)
					{
						xIndex++;
					}
					else if (zOffset < maxLength - cellOffset - zIndex + 1)
					{
						xIndex = 0;
						zIndex++;
					}
					else
					{
						xIndex = 0;
					}
					// we lose buildings here but its good enough for now
					
				}
				else
				{
					System.out.println(String.format("Null building %d", ncount));
					ncount++;
					ntotal++;
				}				
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
		int size = (int) Math.ceil(Math.sqrt(numCells));
		for (int x = 0; x < size; x++)
		{
			for (int y = 0; y < size; y++)
			{
				list.add(new Point(x * maxLength + x, y * maxLength + y));
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
	 * this method would be 4.
	 * 
	 * The list size should be guaranteed to be a power of 2
	 */
	
	
}
