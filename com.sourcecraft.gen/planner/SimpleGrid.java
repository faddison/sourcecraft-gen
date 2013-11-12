package planner;

import java.awt.Point;
import java.io.PrintWriter;
import java.util.ArrayList;

import core.BuildingEntity;
import core.CityEntity;
import metrics.SimpleClassMetrics;

public class SimpleGrid 
{
	ArrayList<Point> cells;
	
	public SimpleGrid(CityEntity cityEntity)
	{
		this.cells = generate(cityEntity);
	}
	
	public ArrayList<Point> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Point> cells) {
		this.cells = cells;
	}

	private ArrayList<Point> generate(CityEntity cityEntity)
	{
		ArrayList<Point> cells = new ArrayList<Point>();
		
		// cell size is based off of the largest attribute number
		int cellSize = cityEntity.getCityData().getMaxLength();

		// numGrids will depend on the number of classes we look through
		double numGrids = (double) cityEntity.getBuildingEntries().size();

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
				if (counter < cityEntity.getBuildingEntries().size())
				{
					cells.add(new Point(i,j));
					counter++;
				}
			}
		}
		
		return cells;
	}
}
