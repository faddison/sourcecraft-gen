package decorator;

import java.util.ArrayList;

import metrics.SimpleClassMetrics;

import core.BlockConstants;
import core.BlockData;
import core.BlockEntity;
import core.BuildingEntity;
import core.CityEntity;

public class BugDecorator extends AbstractDecorator {

	@Override
	public CityEntity decorate(CityEntity city) {
		
		ArrayList<BuildingEntity> buildingEntries = city.getBuildingEntries();
		
		// Loop through every building in the city
		for (BuildingEntity buildingEntity : buildingEntries) {
			
			ArrayList<BlockEntity> blockEntries = buildingEntity.getBlockEntries();
			
			//TODO
			// get number of methods with errors
			
			// Loop through each block in the building
			for (BlockEntity be : blockEntries) {
				// BlockData.setId(BlockConstants.BRICK);
			}
			
			
			
		}
		
		return null;
	}

}
