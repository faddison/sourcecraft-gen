package templates;

import decorator.AbstractDecorator;
import designer.AbstractDesigner;
import metrics.AbstractMetrics;
import metrics.SerializationWrapper;
import parser.AbstractParser;
import parser.SimpleClassAndBugs;
import planner.AbstractPlanner;
import planner.RailwayPlanner;

public abstract class AbstractTemplate<T extends AbstractMetrics> {

	protected AbstractDesigner<T> designer;
	protected AbstractPlanner planner;
	protected RailwayPlanner rails;
	protected AbstractMetrics metrics;
	protected SerializationWrapper<T> serializationWrapper;
	protected SimpleClassAndBugs parser;
	protected AbstractDecorator decorator;
	
	protected String metricsFilename;
	protected String railFilename;
	protected String bugsFilename;
	protected String sourceFilename;
	protected String cityFilename;
	protected String mapFilename;
	
	public abstract void run();
}
