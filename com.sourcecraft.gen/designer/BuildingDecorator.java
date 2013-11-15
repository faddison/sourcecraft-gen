package designer;

import core.BlockConstants;

public class BuildingDecorator {
	
	private final int bugRatio = 5;
	/*
	 *  bugRatio is just multiplied with bugSeverity, which is either 1 or 2. (When 0, chooseBlockId is never used)
	 *  For now either each 5th or each 10th block is brick, depending on the bugSeverity
	 */
	
	
	private int blockCounter = 0;
	
	public BuildingDecorator () {
		
	}
	
	
	public int chooseBlockId (int bugSeverity) {
		
		int blockRatio = bugSeverity*bugRatio;
		blockCounter++;
		if (blockCounter % blockRatio == 0) {
			return BlockConstants.BRICK;
		}
		else {
			return BlockConstants.STONE;
		}
	}
	
	
}
