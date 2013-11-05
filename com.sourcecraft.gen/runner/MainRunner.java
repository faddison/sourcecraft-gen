package runner;

import mapper.CityMapGenerator;
import parser.Parser;
import planner.CityFileGenerator;

public class MainRunner {

	/**
	 * This should run everything
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Parser parser = new Parser();
		CityFileGenerator cityGen = new CityFileGenerator();
		CityMapGenerator mapGen = new CityMapGenerator();
		
		mapGen.map(
			cityGen.generate(
				parser.parse("sources/SweetHomeStructure.xml"), "cities/box.txt"), "maps/map.txt" );
			
	}

}
