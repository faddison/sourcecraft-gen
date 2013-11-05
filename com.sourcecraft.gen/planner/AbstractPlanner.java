package planner;

import java.util.ArrayList;
import java.util.List;

import core.BlockEntity;
import core.BuildingEntity;
import core.CityEntity;

public abstract class AbstractPlanner {

	// returns filename
	public abstract String plan(CityEntity cityEntry);
}
