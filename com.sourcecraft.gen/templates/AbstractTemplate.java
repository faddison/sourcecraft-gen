package templates;

import designer.AbstractDesigner;
import metrics.AbstractMetrics;
import parser.AbstractParser;
import planner.AbstractPlanner;

public abstract class AbstractTemplate {

	protected AbstractDesigner designer;
	protected AbstractPlanner planner;
	protected AbstractMetrics metrics;
	
	public abstract void run();
}
