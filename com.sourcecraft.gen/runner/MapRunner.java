package runner;

import mapper.MapGenerator;

public class MapRunner {

	public static void main(String[] args)
	{
		String cityFilename = "cities/box-new.txt";
		String mapFilename = "maps/map-new.txt";
		(new MapGenerator()).map(cityFilename, mapFilename);

	}

}
