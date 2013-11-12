package runner;

import templates.AbstractTemplate;
import templates.SimpleTemplate;

public class MainRunner {

	/**
	 * This should run everything
	 * @param args
	 */
	public static void main(String[] args) 
	{
		AbstractTemplate template = new SimpleTemplate();
		template.run();
	}

}
