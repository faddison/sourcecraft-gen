package planner;

import java.util.List;

import core.BlockEntry;

public abstract class AbstractPlanner {

	// returns filename
	public abstract String plan(List<List<BlockEntry>> blockEntries);
}
