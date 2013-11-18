package decorator;

import java.util.ArrayList;

import metrics.SimpleClassMetrics;

import core.BlockConstants;
import core.BlockData;
import core.BlockEntity;
import core.BuildingData;
import core.BuildingEntity;
import core.CityEntity;

public class BugDecorator extends AbstractDecorator {

	// Should decorate the enitre city
	
	@Override
	public CityEntity decorateCity (CityEntity city) {
		
		// Copy old city data
		CityEntity cityEntity = new CityEntity();
		city.setBuildingEntries(city.getBuildingEntries());
		city.setCityData(city.getCityData());
		
		createDecorationLists(cityEntity);
		ArrayList<BuildingEntity> buildingEntity = cityEntity.getBuildingEntries();
		
		for (BuildingEntity be : buildingEntity) {
			BuildingData buildingData = be.getBuildingData();
			ArrayList<BlockEntity> blocks = be.getBlockEntries();
			ArrayList<BugData> decorateList = buildingData.getDecorateList();
			
			// Starts decorating methods from the bottom of the building
			for (int i = 0; i < decorateList.size(); i++) {
				decorateMethod(blocks, i, decorateList.get(i));
			}
		}
		
		return cityEntity;
	}
	
	// Creates a list of methods to decorate in each BuildingData object
	// If the same method has several bugs, only the worst bug is added to the list
	
	private void createDecorationLists (CityEntity city) {
		
		ArrayList<BuildingEntity> buildingEntries = city.getBuildingEntries();
		
		// Get city bugs list
		ArrayList<SimpleBugsMetrics> bugsList = city.getBugsList();
		
		for(SimpleBugsMetrics bug : bugsList) {
			
			for (BuildingEntity buildingEntity : buildingEntries) {
				
				SimpleClassMetrics scm = buildingEntity.getMetrics();
				
				// Match every bug to a BuildingEntity, and then add it the DecorateList
				if (bug.getClassName.equals(scm.getClassName())) {
					BuildingData buildingData = buildingEntity.getBuildingData();
					
					int bugRating = BugConstants.setBugRating(bug.getCategory);
					BugData tempBug = new BugData(bugRating, bug.getSeverity(), bug.getMethodName());
					buildingData.addToDecorateList(tempBug);
				}
			}
		}
	}

	
	// Changes the blockData of blocks that are in the height y
	
	private void decorateMethod (ArrayList<BlockEntity> blocks, int y, BugData bugData) {
		
		for (BlockEntity blockEntity : blocks) {
			
			if (blockEntity.getPoint().getY() == y) {
				
				if (bugData.getBugType() == BugConstants.STYLE) {
					BlockData newBlockData = new BlockData(BlockConstants.BRICK);
					blockEntity.setBlockData(newBlockData);
				}
				else if (bugData.getBugType() == BugConstants.BAD_PRACTICE) {
					BlockData newBlockData = new BlockData(BlockConstants.DIRT);
					blockEntity.setBlockData(newBlockData);
				}
				else if (bugData.getBugType() == BugConstants.CORRECTNESS) {
					BlockData newBlockData = new BlockData(BlockConstants.DIRT);
					blockEntity.setBlockData(newBlockData);
				}
				else if (bugData.getBugType() == BugConstants.EXPERIMENTAL) {
					BlockData newBlockData = new BlockData(BlockConstants.DIRT);
					blockEntity.setBlockData(newBlockData);
				}
				else if (bugData.getBugType() == BugConstants.MALICIOUS_CODE) {
					BlockData newBlockData = new BlockData(BlockConstants.DIRT);
					blockEntity.setBlockData(newBlockData);
				}
				else if (bugData.getBugType() == BugConstants.PERFORMANCE) {
					BlockData newBlockData = new BlockData(BlockConstants.DIRT);
					blockEntity.setBlockData(newBlockData);
				}
			}
		}
	}


	
}
	

