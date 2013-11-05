package designer;

import java.util.ArrayList;
import java.util.List;

import core.BlockEntity;
import core.BuildingEntity;
import core.CityEntity;
import metrics.AbstractMetrics;

public abstract class AbstractDesigner<T extends AbstractMetrics> {
	
	protected DesignerHelper designerHelper = new DesignerHelper();
	public abstract CityEntity design(ArrayList<T> metrics);

}
