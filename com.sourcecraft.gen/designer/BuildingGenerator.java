package designer;

import java.util.ArrayList;

import core.BlockData;
import core.BlockEntity;
import core.BuildingData;
import core.BuildingEntity;
import core.Point3D;

public class BuildingGenerator 
{
	public BuildingEntity generate(int length, int width, int block_id)
	{
		ArrayList<BlockEntity> blockEntities = new ArrayList<BlockEntity>();
		
		for (int x = 0; x < length; x++)
		{
			for (int z = 0; z < length; z++)
			{
				blockEntities.add(new BlockEntity(new Point3D(x, 0, z), new BlockData(block_id)));
			}
		}
		
		return new BuildingEntity(null, blockEntities, new BuildingData(length, width, 1));
	}
}
