package planner;

import java.awt.Point;
import java.io.PrintWriter;
import java.util.ArrayList;

import metrics.SimpleClassMetrics;

public class SimpleGrid 
{
	ArrayList<Point> cells;
	
	public SimpleGrid(ArrayList<SimpleClassMetrics> classList)
	{
		this.cells = generate(classList);
	}
	
	public ArrayList<Point> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Point> cells) {
		this.cells = cells;
	}

	private ArrayList<Point> generate(ArrayList<SimpleClassMetrics> classList)
	{
		ArrayList<Point> cells = new ArrayList<Point>();
		
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
					cells.add(new Point(i,j));
					counter++;
				}
			}
		}
		
		return cells;
	}
	
	private int findLargestItem(ArrayList<SimpleClassMetrics> classList)
	{
		int length = classList.size();
		int max = 0;
		for (int i = 0; i < length; i++)
		{
				if (max < classList.get(i).getNumAttributes())
					max = (int) classList.get(i).getNumAttributes();
		}
		return max;
	}

}
