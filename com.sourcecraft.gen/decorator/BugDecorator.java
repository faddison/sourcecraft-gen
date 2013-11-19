package decorator;

import java.util.ArrayList;

import metrics.SimpleBugsMetrics;
import metrics.SimpleClassMetrics;
import core.BlockConstants;
import core.BlockData;
import core.BlockEntity;
import core.BuildingData;
import core.BuildingEntity;
import core.CityEntity;

public class BugDecorator extends AbstractDecorator {
	
	// USED FOR DEBUGGING
	private int decoratedBlocks = 0;
	private int decoratedMethods = 0;
	public static int methodsWithoutNames = 0;
	
	public static int bugReplaceWithWorse = 0;
	public static int bugEquallyBad = 0;
	public static int bugLessBad = 0;

	public BugDecorator() {
		
	}
	
	// Should decorate the entire city
	// Probably change to void
	
	@Override
	public CityEntity decorateCity (CityEntity city, ArrayList<SimpleBugsMetrics> bugList) {
		
		// Copy old city data
//		CityEntity cityEntity = new CityEntity();
//		city.setBuildingEntries(city.getBuildingEntries());
//		city.setCityData(city.getCityData());
		
		createDecorationLists(city, bugList);
		ArrayList<BuildingEntity> buildingEntity = city.getBuildingEntries();
		
		for (BuildingEntity be : buildingEntity) {
			BuildingData buildingData = be.getBuildingData();
			ArrayList<BlockEntity> blocks = be.getBlockEntries();
			ArrayList<BugData> decorateList = buildingData.getDecorateList();
			
			if (decorateList.isEmpty()) {
				continue;
			}
			
			// Starts decorating methods from the bottom of the building
			for (int i = 0; i < decorateList.size(); i++) {
				
//				System.out.println("Decorating: " + decorateList.get(i).getMethodName() + " in class " + be.getMetrics().getClassName());
				
				decorateMethod(blocks, i, decorateList.get(i));
				decoratedMethods++;
			}
//			System.out.println("");
		}
		System.out.println("");
		System.out.println("total decorated blocks: " + decoratedBlocks);
		System.out.println("total decorated methods: " + decoratedMethods);
		System.out.println("methods without names: " + methodsWithoutNames);
//		System.out.println("");
//		System.out.println("bugs replace with worse ones: " + bugReplaceWithWorse);
//		System.out.println("bugs equally bad: " + bugEquallyBad);
//		System.out.println("bug less bad: " + bugLessBad);
		
		return city;
	}
	
	// Creates a list of methods to decorate in each BuildingData object
	// If the same method has several bugs, only the worst bug is added to the list
	
	private void createDecorationLists (CityEntity city, ArrayList<SimpleBugsMetrics> bugList) {
		
		ArrayList<BuildingEntity> buildingEntries = city.getBuildingEntries();

		for(SimpleBugsMetrics bug : bugList) {
			
			for (BuildingEntity buildingEntity : buildingEntries) {
				
				SimpleClassMetrics scm = buildingEntity.getMetrics();
				
				// Match every bug to a BuildingEntity, and then add it the DecorateList
				if (bug.getClassName().equals(scm.getClassName())) {
					BuildingData buildingData = buildingEntity.getBuildingData();
					
					int bugRating = BugConstants.setBugRating(bug.getCategory());
					BugData tempBug = new BugData(bugRating, bug.getPriority(), bug.getMethodName());
					
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
					decoratedBlocks++;
				}
				else if (bugData.getBugType() == BugConstants.BAD_PRACTICE) {
					BlockData newBlockData = new BlockData(BlockConstants.DIRT);
					blockEntity.setBlockData(newBlockData);
					decoratedBlocks++;
				}
				else if (bugData.getBugType() == BugConstants.CORRECTNESS) {
					BlockData newBlockData = new BlockData(BlockConstants.COBBLESTONE);
					blockEntity.setBlockData(newBlockData);
					decoratedBlocks++;
				}
				else if (bugData.getBugType() == BugConstants.EXPERIMENTAL) {
					BlockData newBlockData = new BlockData(BlockConstants.SAND);
					blockEntity.setBlockData(newBlockData);
					decoratedBlocks++;
				}
				else if (bugData.getBugType() == BugConstants.MALICIOUS_CODE) {
					BlockData newBlockData = new BlockData(BlockConstants.LAVA);
					blockEntity.setBlockData(newBlockData);
					decoratedBlocks++;
				}
				else if (bugData.getBugType() == BugConstants.PERFORMANCE) {
					BlockData newBlockData = new BlockData(BlockConstants.DIRT);
					blockEntity.setBlockData(newBlockData);
					decoratedBlocks++;
				}
			}
		}
	}


	
}
	

