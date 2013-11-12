package templates;

import java.io.File;
import java.util.ArrayList;

import mapper.MapGenerator;
import metrics.MetricsDeserializer;
import metrics.MetricsSerializer;
import metrics.SimpleClassMetrics;
import designer.SimpleDesigner;
import parser.SimpleClassParser;
import planner.CityFileGenerator;
import planner.SimpleGridPlanner;

public class SimpleTemplate extends AbstractTemplate {
	
	
	public SimpleTemplate()
	{
		super();
		designer = new SimpleDesigner();
		planner = new SimpleGridPlanner();
		serializer = new MetricsSerializer();
		deserializer = new MetricsDeserializer<SimpleClassMetrics>();
		parser = new SimpleClassParser();
		
		metricsFilename = "metrics/simpleclassmetrics-list.list";
		sourceFilename = "sources/SweetHomeStructure.xml";
		cityFilename = "cities/box.txt";
		mapFilename = "maps/map.txt";
	}

	// suppress type check because we know what the types are
	@SuppressWarnings("unchecked")
	@Override
	public void run() 
	{
		ArrayList<SimpleClassMetrics> metricsList = new ArrayList<SimpleClassMetrics>();
		
		File file = new File(metricsFilename);
		if (file.exists())
		{
			metricsList = (ArrayList<SimpleClassMetrics>) deserializer.deserialize(metricsFilename);
		}
		else
		{
			metricsList =  (ArrayList<SimpleClassMetrics>) parser.parse(sourceFilename);
			serializer.serialize(metricsList, metricsFilename);
		}
			
		(new CityFileGenerator()).generate(metricsList, cityFilename);
		(new MapGenerator()).map(cityFilename, mapFilename);
		
	}
}
