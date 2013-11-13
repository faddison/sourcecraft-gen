package templates;

import java.io.File;
import java.util.ArrayList;

import core.CityEntity;
import mapper.MapGenerator;
import metrics.MetricsDeserializer;
import metrics.MetricsSerializer;
import metrics.SerializationWrapper;
import metrics.SimpleClassMetrics;
import designer.SimpleDesigner;
import parser.SimpleClassParser;
import planner.CityFileGenerator;
import planner.ImprovedGridPlanner;

public class NewSimpleTemplate extends AbstractTemplate<SimpleClassMetrics> {
	
	
	public NewSimpleTemplate()
	{
		super();
		designer = new SimpleDesigner();
		planner = new ImprovedGridPlanner();
		serializationWrapper = new SerializationWrapper<SimpleClassMetrics>();
		parser = new SimpleClassParser();
		
		metricsFilename = "metrics/simpleclassmetrics-list.list";
		sourceFilename = "sources/SweetHomeStructure.xml";
		cityFilename = "cities/box.txt";
		mapFilename = "maps/map.txt";
	}

	// suppress type check because we know what the types are
	// this needs to be resolved but for now its fine
	@Override
	public void run() 
	{
		ArrayList<SimpleClassMetrics> metricsList = new ArrayList<SimpleClassMetrics>();
		
		File file = new File(metricsFilename);
		if (file.exists())
		{
			metricsList = serializationWrapper.deserializer.deserialize(metricsFilename);
		}
		else
		{
			metricsList =  (ArrayList<SimpleClassMetrics>) parser.parse(sourceFilename);
			serializationWrapper.serializer.serialize(metricsList, metricsFilename);
		}
			
		CityEntity cityEntity = designer.design(metricsList);
		planner.plan(cityEntity);
		(new MapGenerator()).map(cityFilename, mapFilename);
		
	}
}
