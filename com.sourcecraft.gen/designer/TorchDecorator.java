package designer;

import java.util.ArrayList;

import metrics.AbstractMetrics;
import metrics.SimpleClassMetrics;

import core.BlockConstants;
import core.BlockData;
import core.BlockEntity;
import core.BuildingData;
import core.BuildingEntity;
import core.CityEntity;
import core.Point3D;

public class TorchDecorator extends AbstractDecorator {

	@Override
	// Take in a cityEntity and return a new cityEntity where the buildings are decorated
	public CityEntity decorate(CityEntity cityEntity) {
		
		// Create a new city object to (to return) and copy in the data

		CityEntity newCityEntity = new CityEntity();
		newCityEntity.setBuildingEntries(cityEntity.getBuildingEntries());
		newCityEntity.setCityData(cityEntity.getCityData());
		
		ArrayList<BuildingEntity> buildingEntries = newCityEntity.getBuildingEntries();
		
		// Loop though every building in the city
		for (BuildingEntity buildingEnt : buildingEntries) {
			
			BuildingData tempBuildingData =  buildingEnt.getBuildingData();
			ArrayList<BlockEntity> blockEntities = buildingEnt.getBlockEntries();
			
			// Loop through every block in the building
			for (BlockEntity be : blockEntities) {
				
				int x = be.getPoint().getX();
				int y = be.getPoint().getY();
				int z = be.getPoint().getZ();
				
				int length = tempBuildingData.getLength();
				int width = tempBuildingData.getWidth();
				int height = tempBuildingData.getHeight();
				
				// Place 
				if (x == 0 && z == 0 && y == height ||
					x == 0 && z == width && y == height ||
					x == length && z == 0 && y == height ||
					x == length && z == width && y == height) {
					
						blockEntities.add(new BlockEntity(new Point3D(x, y+1, z), new BlockData(BlockConstants.TORCH)));
				}
			}
		}
		
		return newCityEntity;
	}
	
	
	
	
}
