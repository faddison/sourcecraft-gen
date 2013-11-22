package mapper;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MapGenerator {

	public void map(String inputfile1, String inputfile2, String outputfile) 
	{
		ArrayList<Point> mapList = new ArrayList<Point>();
		mapList.addAll(readCity(inputfile1));
		mapList.addAll(readCity(inputfile2));
		writeCity(mapList, outputfile);	
	}
	
	private ArrayList<Point> readCity(String filename)
    {
		System.out.println(String.format("Reading %s", filename));
		ArrayList<Point> list = new ArrayList<Point>();
		int xMax = 0;
		int zMax = 0;
		
		
    	try
    	{
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int count = 1;
			while ((strLine = br.readLine()) != null)   
			{
				
				String[] args = strLine.split(" ");
				int blockID = Integer.parseInt(args[0]);
				int x = Integer.parseInt(args[1]);
				int y = Integer.parseInt(args[2]);
				int z = Integer.parseInt(args[3]);
				Point p = new Point(x,z);
				if (!list.contains(p))
				{
					System.out.println(String.format("Point %d: %d, %d",count, x, z));
					list.add(new Point(x, z));
					if (x > xMax) xMax = x;
					if (z > zMax) zMax = z;
					count++;
				}
				//System.out.println(String.format("New point: %d, %d", x, z));
			}
			
			in.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
    	
    	// last point in the list is the max x and z coordinate, respectively
    	list.add(new Point(xMax, zMax));
    	return list;
	
    }
	
	private void writeCity(ArrayList<Point> list, String outputfile)
	{
		System.out.println(String.format("Wrtiting %s", outputfile));
		try 
		{
			PrintWriter writer = new PrintWriter(outputfile, "UTF-8");
			Point max = list.get(list.size() - 1);
			list.remove(list.size() - 1);
			
			for (int i = 0; i < max.x; i++)
			{
				System.out.println(String.format("Constructing row %d",i));
				for (int j = 0; j < max.y; j++)
				{
					Point coord = new Point(i,j);
					if (list.contains(coord))
						writer.print("1");
					else 
						writer.print("0");
				}
				writer.println();
			}
			writer.print(String.format("\nx, y : %d, %d",max.x, max.y));
			writer.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
