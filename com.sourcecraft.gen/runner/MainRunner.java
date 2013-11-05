package runner;

import java.io.File;
import java.util.ArrayList;

import mapper.CityMapGenerator;
import metrics.MetricsDeserializer;
import metrics.MetricsSerializer;
import metrics.SimpleClassMetrics;
import parser.SimpleClassParser;
import planner.CityFileGenerator;

public class MainRunner {

	/**
	 * This should run everything
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String metricsFilename = "metrics/simpleclassmetrics-list.list";
		String sourceFilename = "sources/SweetHomeStructure.xml";
		String cityFilename = "cities/box.txt";
		String mapFilename = "maps/map.txt";
		
		ArrayList<SimpleClassMetrics> metricsList = new ArrayList<SimpleClassMetrics>();
		
		File file = new File(metricsFilename);
		if (file.exists())
		{
			metricsList = (new MetricsDeserializer<SimpleClassMetrics>()).deserialize(metricsFilename);
		}
		else
		{
			metricsList = (new SimpleClassParser()).parse(sourceFilename);
			(new MetricsSerializer<SimpleClassMetrics>()).serialize(metricsList, metricsFilename);
		}
			
		(new CityFileGenerator()).generate(metricsList, cityFilename);
		(new CityMapGenerator()).map(cityFilename, mapFilename);
	}

}
