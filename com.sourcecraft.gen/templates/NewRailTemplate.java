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
import metrics.SimpleClassMetrics;
import designer.SimpleDesigner;
import parser.SimpleClassParser;
import planner.BlockWriter;
import planner.CityFileGenerator;
import planner.ImprovedGridPlanner;
import planner.RailwayPlanner;

public class NewRailTemplate extends AbstractTemplate<SimpleClassMetrics> {
	
	
	public NewRailTemplate()
	{
		super();
		designer = new SimpleDesigner();
		planner = new ImprovedGridPlanner();
		rails = new RailwayPlanner();
		serializationWrapper = new SerializationWrapper<SimpleClassMetrics>();
		// parser now outdated from AbstractTemplate
//		parser = new SimpleClassParser();
		
		metricsFilename = "metrics/simpleclassmetrics-list.list";
		sourceFilename = "sources/SweetHomeStructure.xml";
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
			
			ArrayList<SimpleClassMetrics> metricsList = new ArrayList<SimpleClassMetrics>();
			
			file = new File(metricsFilename);
			if (file.exists())
			{
				metricsList = serializationWrapper.deserializer.deserialize(metricsFilename);
			}
			else
			{
				serializationWrapper.serializer.serialize(metricsList, metricsFilename);
			}
				
			CityEntity cityEntity = designer.design(metricsList);
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
