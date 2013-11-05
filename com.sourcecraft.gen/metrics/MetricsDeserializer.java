package metrics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MetricsDeserializer<T extends AbstractMetrics> 
{
	public ArrayList<T> deserialize(String filename)
	{
        ArrayList<T> list = new ArrayList<T>();
        
		try
        {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList<T>) ois.readObject();
            ois.close();
            
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
		
		return list;
    }
}
