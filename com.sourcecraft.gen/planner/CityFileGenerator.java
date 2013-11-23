package planner;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.lang.Math;

import parser.SimpleClassParser;
import metrics.SimpleClassMetrics;

public class CityFileGenerator 
{

	private static int total_blocks = 0;
	private static boolean scale = false;

	public String generate(ArrayList<SimpleClassMetrics> classes, String filename)
	{
		PrintWriter writer;
		try 
		{
			writer = new PrintWriter(filename, "UTF-8");
//			Parser p = new Parser();
//			p.parse(classes);
//			p.showData(classes);
			generateGrid(classes, writer);
//			generateBox(25, writer);
			writer.close();
			System.out.println(String.format("lines : %d", total_blocks));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return filename;
		
	}
	
	private int findLargestItem(ArrayList<SimpleClassMetrics> classList){
		int length = classList.size();
		int max = 0;
		for (int i = 0; i < length; i++)
		{
			if (scale)
			{
				if (max < Math.log(classList.get(i).getNumAttributes()))
					max = (int) Math.log(classList.get(i).getNumAttributes());
			}
			else
			{
				if (max < classList.get(i).getNumAttributes())
					max = (int) classList.get(i).getNumAttributes();
			}
		}
		return max;
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

	public void generateGrid(ArrayList<SimpleClassMetrics> classList, PrintWriter writer){

		classList = filterClassList(classList);
		
		// cell size is based off of the largest attribute number
		int cellSize = findLargestItem(classList) + 1;

		// numGrids will depend on the number of classes we look through
		double numGrids = (double) classList.size();

		// numCell is the number of cells we have per side (assume square world)
		int numCell = (int) Math.ceil(Math.sqrt(numGrids));

		// gridSide is the length of each side of the grid
		int gridSide = cellSize * numCell;

		// counter will keep track of how many buildings we've built so far
		int counter = 0;
		
		int cellPadding = 10;

		// builds out the grid then builds structs in each one
		for (int i = 0; i < gridSide; i += cellSize + cellPadding) 
		{
			for (int j = 0; j < gridSide; j += cellSize + cellPadding)
			{
				if (counter < classList.size())
				{
					System.out.println("Building structure "+(counter+1));
					generateBuilding(classList, counter, i, j, writer);
					counter++;
				}
			}
		}

	}
	
	private boolean shouldSetBlock(int x, int y, int z, int x_offset, int z_offset, int height, int dimension)
	{
		return ((y == height - 1) ||
				(x == x_offset + dimension - 1) ||
				(z == z_offset + dimension - 1) ||
				(x == x_offset) ||
				(z == z_offset));
	}

	// in this method, inputs x and y determine the beginning x and z coordinates of each grid
	private void generateBuilding(ArrayList<SimpleClassMetrics> classList, int gridNum, int x_offset, int z_offset, PrintWriter writer)
	{

		// get a class and return its method and attributes
		SimpleClassMetrics c = classList.get(gridNum);
		
		int dimension = 0;
		int height = 0;
		if (scale)
		{
			dimension = (int) Math.log(c.getNumAttributes()); 
			height = (int) Math.log(c.getNumMethods()) + 1;
		}
		else
		{
			dimension = c.getNumAttributes() + 1;
			height = c.getNumMethods() + 1;
		}

			//make each block based on attributes, x starting at x going downwards and z going to the right
		
		for (int x = x_offset; x < x_offset + dimension; x++)
		{
			for (int z = z_offset; z < z_offset + dimension; z++)
			{
				for (int y = 0; y < height; y++)
				{
					if (shouldSetBlock(x, y, z, x_offset, z_offset, height, dimension))
					{
						writer.print(String.format("%d %d %d %d\n", 1, x, y, z));
						total_blocks++;
					}
						
				}
			}
		}
	}
}
