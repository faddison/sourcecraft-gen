package designer;

import java.util.ArrayList;

import core.CityEntity;
import metrics.AbstractMetrics;

public abstract class AbstractDesigner<T extends AbstractMetrics> {
	
	protected DesignerHelper designerHelper = new DesignerHelper();
	public abstract CityEntity design(ArrayList<T> metricsList);

}
