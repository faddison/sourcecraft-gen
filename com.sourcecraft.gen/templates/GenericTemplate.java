package templates;

import metrics.AbstractMetrics;
import planner.AbstractPlanner;
import designer.AbstractDesigner;

public class GenericTemplate extends AbstractTemplate {

	public GenericTemplate(AbstractPlanner planner, AbstractDesigner<?> designer, AbstractMetrics metrics)
	{
		this.planner = planner;
		this.designer = designer;
		this.metrics = metrics;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
