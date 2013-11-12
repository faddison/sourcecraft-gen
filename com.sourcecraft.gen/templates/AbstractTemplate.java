package templates;

import designer.AbstractDesigner;
import metrics.AbstractMetrics;
import metrics.MetricsDeserializer;
import metrics.MetricsSerializer;
import parser.AbstractParser;
import planner.AbstractPlanner;

public abstract class AbstractTemplate {

	protected AbstractDesigner<?> designer;
	protected AbstractPlanner planner;
	protected AbstractMetrics metrics;
	protected MetricsSerializer serializer;
	protected MetricsDeserializer<?> deserializer;
	protected AbstractParser<?> parser;
	
	protected String metricsFilename;
	protected String sourceFilename;
	protected String cityFilename;
	protected String mapFilename;
	
	public abstract void run();
}
