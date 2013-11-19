package templates;

import java.io.File;
import java.util.ArrayList;

import mapper.MapGenerator;
import metrics.MetricsDeserializer;
import metrics.MetricsSerializer;
import metrics.SerializationWrapper;
import metrics.SimpleClassMetrics;
import designer.SimpleDesigner;
import parser.SimpleClassParser;
import planner.CityFileGenerator;
import planner.ImprovedGridPlanner;

public class OldSimpleTemplate extends AbstractTemplate {
	
	
	public OldSimpleTemplate()
	{
		super();
		designer = new SimpleDesigner();
		planner = new ImprovedGridPlanner();
		serializationWrapper = new SerializationWrapper<SimpleClassMetrics>();
//		parser = new ParseClassAndBugs();
		
		metricsFilename = "metrics/simpleclassmetrics-list.list";
		sourceFilename = "sources/SweetHomeStructure.xml";
		cityFilename = "cities/box.txt";
		mapFilename = "maps/map.txt";
	}

	// suppress type check because we know what the types are
	// this needs to be resolved but for now its fine
	@SuppressWarnings("unchecked")
	@Override
	public void run() 
	{
		ArrayList<SimpleClassMetrics> metricsList = new ArrayList<SimpleClassMetrics>();
		
		File file = new File(metricsFilename);
		if (file.exists())
		{
			metricsList = (ArrayList<SimpleClassMetrics>) serializationWrapper.deserializer.deserialize(metricsFilename);
		}
		else
		{
//			metricsList =  (ArrayList<SimpleClassMetrics>) parser.parse(sourceFilename);
			serializationWrapper.serializer.serialize(metricsList, metricsFilename);
		}
			
		(new CityFileGenerator()).generate(metricsList, cityFilename);
		(new MapGenerator()).map(cityFilename, mapFilename);
		
	}
}
