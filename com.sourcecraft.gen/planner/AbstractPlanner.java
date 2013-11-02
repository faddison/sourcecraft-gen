package planner;

import java.util.List;

import core.BlockEntry;

public abstract class AbstractPlanner {

	public abstract String plan(List<List<BlockEntry>> blockEntries);
}
