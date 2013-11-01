package templates;

import designer.AbstractDesigner;
import metrics.AbstractMetrics;
import parser.AbstractParser;
import planner.AbstractPlanner;

public abstract class AbstractTemplate {

	private AbstractParser parser;
	private AbstractMetrics metrics;
	private AbstractDesigner designer;
	private AbstractPlanner planner;

	public AbstractTemplate()
	{
	}
	
	public void run()
	{
		//planner.plan(designer.design(parser.parse(metrics)));
	}
}
