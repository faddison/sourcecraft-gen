package runner;

import templates.AbstractTemplate;
import templates.DecoratorTemplate;
import templates.NewSimpleTemplate;
import templates.OldSimpleTemplate;

public class MainRunner {

	/**
	 * This should run everything
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try 
		{
			AbstractTemplate template;
			
			//template = new OldSimpleTemplate();
			template = new DecoratorTemplate();
					
			template.run();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
