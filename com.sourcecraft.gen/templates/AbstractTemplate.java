package templates;

import designer.AbstractDesigner;
import metrics.AbstractMetrics;
import parser.AbstractParser;
import planner.AbstractPlanner;

public abstract class AbstractTemplate {

	protected AbstractParser parser;
	protected AbstractMetrics metrics;
	protected AbstractDesigner designer;
	protected AbstractPlanner planner;

	public AbstractTemplate()
	{
	}
	
	public void run()
	{
		//planner.plan(designer.design(parser.parse(metrics)));
	}
}
