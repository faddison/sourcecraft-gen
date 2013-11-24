package templates;

import java.awt.Point;
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
import planner.RailwayPlanner;

public class MergedDecoRailTemplate extends AbstractTemplate<SimpleClassMetrics> {
	
	
	public MergedDecoRailTemplate()
	{
		super();
		designer = new SimpleDesigner();
		planner = new ImprovedGridPlanner();
		rails = new RailwayPlanner();
		parser = new SimpleClassAndBugs();
		serializationWrapper = new SerializationWrapper<SimpleClassMetrics>();
		decorator = new BugDecorator();
		
		metricsFilename = "metrics/simpleclassmetrics-list.list";
		sourceFilename = "sources/SweetHomeStructure.xml";
		bugsFilename = "sources/SweetHomeBugResults.xml";
//		sourceFilename = "sources/simulator313structure.xml";
//		bugsFilename = "sources/simulator313bugs.xml";
		cityFilename = "cities/box-new.txt";
		railFilename = "cities/rail-box.txt";
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
			file = new File(railFilename);
			if (!file.exists())
				file.createNewFile();
			

			ArrayList<SimpleClassMetrics> metricsList = new ArrayList<SimpleClassMetrics>();
			metricsList =  parser.startParsing(sourceFilename, bugsFilename);
			
			ArrayList<SimpleBugsMetrics> simpleBugsList = new ArrayList<SimpleBugsMetrics>();
			simpleBugsList = parser.parseForBugs(bugsFilename);

			CityEntity cityEntity = designer.design(metricsList);
			decorator.decorateCity(cityEntity, simpleBugsList);
			planner.plan(cityEntity, cityFilename);
			ArrayList<Point> cellLocations = ((ImprovedGridPlanner) planner).getCellList();
			int cellLength = ((ImprovedGridPlanner) planner).getCellLength();
			((RailwayPlanner) rails).setPaths(cityEntity, railFilename, cellLocations, cellLength);//, writer);
			(new MapGenerator()).map(cityFilename, railFilename, mapFilename);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
