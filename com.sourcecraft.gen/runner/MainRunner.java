package runner;

import templates.AbstractTemplate;
import templates.DecoratorTemplate;
import templates.MergedDecoRailTemplate;
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
			//template = new DecoratorTemplate();
			//template = new NewRailTemplate();
			template = new MergedDecoRailTemplate();
					
			template.run();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
