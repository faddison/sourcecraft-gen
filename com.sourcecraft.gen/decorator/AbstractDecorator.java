package decorator;

import java.util.ArrayList;

import core.BuildingEntity;
import core.CityEntity;

public abstract class AbstractDecorator {
	
	public abstract CityEntity decorateCity (CityEntity city);

}
