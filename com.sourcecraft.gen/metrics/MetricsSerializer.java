package metrics;

import java.awt.List;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MetricsSerializer<T extends AbstractMetrics>
{
	public void serialize(ArrayList<T> list, String filename)
	{
        try 
        {
        	FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list); 
            oos.close();
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
