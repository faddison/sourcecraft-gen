package planner;

import java.util.ArrayList;
import java.util.List;

import core.BlockEntry;
import core.BuildingEntry;

public abstract class AbstractPlanner {

	// returns filename
	public abstract String plan(ArrayList<BuildingEntry> buildingEntries);
}
