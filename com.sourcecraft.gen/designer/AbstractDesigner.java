package designer;

import java.util.ArrayList;
import java.util.List;

import core.BlockEntry;
import core.BuildingEntry;
import metrics.AbstractMetrics;

public abstract class AbstractDesigner<T extends AbstractMetrics> {
	
	protected DesignerHelper designerHelper = new DesignerHelper();
	public abstract ArrayList<BuildingEntry> design(ArrayList<T> metrics);

}
