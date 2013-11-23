package templates;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import core.CityEntity;
import mapper.MapGenerator;
import metrics.MetricsDeserializer;
import metrics.MetricsSerializer;
import metrics.SerializationWrapper;
import metrics.SimpleBugsMetrics;
import metrics.SimpleClassMetrics;
import decorator.BugDecorator;
import designer.SimpleDesigner;
import parser.SimpleClassAndBugs;
import planner.CityFileGenerator;
import planner.ImprovedGridPlanner;

public class DecoratorTemplate extends AbstractTemplate<SimpleClassMetrics> {
	
	
	public DecoratorTemplate()
	{
		super();
		designer = new SimpleDesigner();
		planner = new ImprovedGridPlanner();
		parser = new SimpleClassAndBugs();
		serializationWrapper = new SerializationWrapper<SimpleClassMetrics>();
		decorator = new BugDecorator();
		
		metricsFilename = "metrics/simpleclassmetrics-list.list";
		sourceFilename = "sources/SweetHomeStructure.xml";
		bugsFilename = "sources/SweetHomeBugResults.xml";
		cityFilename = "cities/box-new.txt";
		mapFilename = "maps/map-new.txt";
	}

	// suppress type check because we know what the types are
	// this needs to be resolved but for now its fine
	@Override
	public void run() 
	{
		try 
		{
			File file = new File(cityFilename);
			if (!file.exists())
				file.createNewFile();
			file = new File(mapFilename);
			if (!file.exists())
				file.createNewFile();

			ArrayList<SimpleClassMetrics> metricsList = new ArrayList<SimpleClassMetrics>();
			metricsList =  parser.startParsing(sourceFilename, bugsFilename);
			
			ArrayList<SimpleBugsMetrics> simpleBugsList = new ArrayList<SimpleBugsMetrics>();
			simpleBugsList = parser.parseForBugs(bugsFilename);

			CityEntity cityEntity = designer.design(metricsList);
			planner.plan(cityEntity, cityFilename);
			(new MapGenerator()).map(cityFilename, cityFilename, mapFilename);
			
			decorator.decorateCity(cityEntity, simpleBugsList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
