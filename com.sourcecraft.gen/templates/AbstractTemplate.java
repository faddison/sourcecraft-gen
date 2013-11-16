package templates;

import designer.AbstractDesigner;
import metrics.AbstractMetrics;
import metrics.SerializationWrapper;
import parser.AbstractParser;
import planner.AbstractPlanner;

public abstract class AbstractTemplate<T extends AbstractMetrics> {

	protected AbstractDesigner<T> designer;
	protected AbstractPlanner planner;
	protected AbstractPlanner rails;
	protected AbstractMetrics metrics;
	protected SerializationWrapper<T> serializationWrapper;
	protected AbstractParser<T> parser;
	
	protected String metricsFilename;
	protected String sourceFilename;
	protected String cityFilename;
	protected String mapFilename;
	
	public abstract void run();
}
