package designer;

import java.util.ArrayList;
import core.BlockData;
import core.BlockEntity;
import core.BuildingData;
import core.BuildingEntity;
import core.CityData;
import core.CityEntity;
import core.Point3D;
import metrics.SimpleClassMetrics;

public class SimpleDesigner extends AbstractDesigner<SimpleClassMetrics>
{

	private boolean scale = true;
	Scaler scaler = new Scaler();
	
	@Override
	public CityEntity design(ArrayList<SimpleClassMetrics> classMetricsList) 
	{
		classMetricsList = designerHelper.filterBuildingEntities(classMetricsList);
		
		ArrayList<BuildingEntity> buildingEntries = new ArrayList<BuildingEntity>();
		CityData cityData = new CityData();
		
		int count = 1;
		int maxLength = 0;
		int maxHeight = 0;
		
		for (SimpleClassMetrics c: classMetricsList)
		{
			System.out.println(String.format("Constructing building %s", count));
			buildingEntries.add(createBuildingEntry(c));
			if (scale)
			{
				maxLength = (int) scaler.scale(c.getNumAttributes(), ScalerType.FACTOR) > maxLength? scaler.scale(c.getNumAttributes(), ScalerType.FACTOR) : maxLength;
				maxHeight = (int) scaler.scale(c.getNumMethods(), ScalerType.FACTOR) > maxHeight? scaler.scale(c.getNumMethods(), ScalerType.FACTOR) : maxHeight;
			}
			else
			{
				maxLength = c.getNumAttributes() > maxLength? c.getNumAttributes() : maxLength;
				maxHeight = c.getNumMethods() > maxHeight? c.getNumMethods() : maxHeight;
			}
			count++;
		}
		
		cityData.setMaxHeight(maxHeight);
		cityData.setMaxLength(maxLength+1);
		cityData.setMaxWidth(maxLength+1);
		
		return new CityEntity(buildingEntries, cityData);
	}
	
	private BuildingEntity createBuildingEntry(SimpleClassMetrics c)
	{
		BuildingData buildingData = new BuildingData();
		ArrayList<BlockEntity> blockEntries = new ArrayList<BlockEntity>();
		
		// get a class and return its method and attributes
		int dimension = 0;
		int height = 0;
		
		if (scale)
		{
			dimension = (int) scaler.scale(c.getNumAttributes(), ScalerType.FACTOR) + 1; 
			height = (int) scaler.scale(c.getNumMethods(), ScalerType.FACTOR) + 1;
		}
		else
		{
			dimension = c.getNumAttributes() + 1;
			height = c.getNumMethods() + 1;
		}
		
		buildingData.setHeight(height);
		buildingData.setLength(dimension+1);
		buildingData.setWidth(dimension+1);

			//make each block based on attributes, x starting at x going downwards and z going to the right
		int count = 0;
		
		for (int x = 0; x < dimension; x++)
		{
			for (int z = 0; z < dimension; z++)
			{
				for (int y = 0; y < height; y++)
				{
					if (designerHelper.shouldSetBlock(x, y, z, 0, 0, height, dimension))
					{
							blockEntries.add(new BlockEntity(new Point3D(x,y,z),new BlockData(1)));
							count++;

					}
				}
			}
		}
		
		buildingData.setBlocks(count);	
		
		return new BuildingEntity(c, blockEntries, buildingData);
	}

}
