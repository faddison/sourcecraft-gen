package planner;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.lang.Math;

import parser.Parser;
import metrics.ClassProperties;

public class CityFileGenerator {

	private static int total_blocks = 0;
	private static boolean scale = false;
	
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException 
	{
		PrintWriter writer = new PrintWriter("box.txt", "UTF-8");
		ArrayList<ClassProperties> classes = Parser.parse();
		//Parser p = new Parser();
		//p.parse(classes);
		//p.showData(classes);
		generateGrid(classes, writer);
		//generateBox(25, writer);
		writer.close();
		System.out.println(String.format("lines : %d", total_blocks));
	}
	public static int findLargestItem(ArrayList<ClassProperties> classList){
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
	
	private static ArrayList<ClassProperties> filterClassList(ArrayList<ClassProperties> classList)
	{
		
		int maxBuildings = 50;
		
		ArrayList<ClassProperties> newList = new ArrayList<ClassProperties>();
		
		for (ClassProperties c: classList)
		{
			if (isValidBuilding(c) && newList.size() < maxBuildings + 1)
				newList.add(c);
		}
		return newList;
	}
	
	private static boolean isValidBuilding(ClassProperties c)
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

	public static void generateGrid(ArrayList<ClassProperties> classList, PrintWriter writer){

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
		
		int cellPadding = 2;

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
	
	public static boolean shouldSetBlock(int x, int y, int z, int x_offset, int z_offset, int height, int dimension)
	{
		return ((y == height - 1) ||
				(x == x_offset + dimension - 1) ||
				(z == z_offset + dimension - 1) ||
				(x == x_offset) ||
				(z == z_offset));
	}

	// in this method, inputs x and y determine the beginning x and z coordinates of each grid
	public static void generateBuilding(ArrayList<ClassProperties> classList, int gridNum, int x_offset, int z_offset, PrintWriter writer)
	{

		// get a class and return its method and attributes
		ClassProperties c = classList.get(gridNum);
		
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
