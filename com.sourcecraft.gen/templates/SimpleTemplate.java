package templates;

import designer.SimpleDesigner;
import metrics.SimpleClassMetrics;
import parser.SimpleClassParser;
import planner.SimpleGridPlanner;

public class SimpleTemplate extends AbstractTemplate {
	
	public SimpleTemplate()
	{
		super();
		parser = new SimpleClassParser();
		planner = new SimpleGridPlanner();
		metrics = new SimpleClassMetrics();
		designer = new SimpleDesigner();
	}
}
