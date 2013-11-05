package designer;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import core.BlockData;
import core.BlockEntry;
import core.BuildingData;
import core.BuildingEntry;
import core.Point3D;
import metrics.SimpleClassMetrics;

public class SimpleDesigner extends AbstractDesigner<SimpleClassMetrics>
{

	private boolean scale = false;
	
	@Override
	public ArrayList<BuildingEntry> design(ArrayList<SimpleClassMetrics> classMetricsList) 
	{
		ArrayList<BuildingEntry> buildingEntries = new ArrayList<BuildingEntry>();
				
		for (SimpleClassMetrics c: classMetricsList)
		{
			buildingEntries.add(createBuildingEntry(c));
		}
		
		return buildingEntries;
	}
	
	private BuildingEntry createBuildingEntry(SimpleClassMetrics c)
	{
		
		ArrayList<BlockEntry> blockEntries = new ArrayList<BlockEntry>();
		
		// get a class and return its method and attributes
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
		
		for (int x = 0; x < dimension; x++)
		{
			for (int z = 0; z < dimension; z++)
			{
				for (int y = 0; y < height; y++)
				{
					if (designerHelper.shouldSetBlock(x, y, z, 0, 0, height, dimension))
					{
						blockEntries.add(new BlockEntry(new Point3D(x,y,z),new BlockData(1)));
					}
						
				}
			}
		}
		
		return new BuildingEntry(c, blockEntries,new BuildingData());
	}

}
