package metrics;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MetricsSerializer
{
	public void serialize(ArrayList<?> metricsList, String filename)
	{
        try 
        {
        	FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(metricsList); 
            oos.close();
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
