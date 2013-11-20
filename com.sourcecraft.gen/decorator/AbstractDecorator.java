package decorator;

import java.util.ArrayList;

import metrics.SimpleBugsMetrics;

import core.BuildingEntity;
import core.CityEntity;

public abstract class AbstractDecorator {
	
	public abstract CityEntity decorateCity (CityEntity city, ArrayList<SimpleBugsMetrics> bugs);

}
