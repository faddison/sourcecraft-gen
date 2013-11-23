package parser;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import metrics.SimpleBugsMetrics;

import org.xml.sax.SAXException;

public class ParseBugs extends AbstractParser<SimpleBugsMetrics> {

	private ArrayList<SimpleBugsMetrics> bugs = new ArrayList<SimpleBugsMetrics>();
	
	// Parsing SweetHomeBugResults.xml
	public ArrayList<SimpleBugsMetrics> parse(String filename) {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			SimpleBugsParser handler = new SimpleBugsParser();
			saxParser.parse(filename, handler);
			bugs = handler.getBugs();
			
//			printBugs();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bugs;
	}
	
	private void printBugs() {
		
		for (int i=0; i<bugs.size(); i++) {
			
			SimpleBugsMetrics bm = bugs.get(i);
			
			String name = bm.getClassName();
			String category = bm.getCategory();
			String methodName = bm.getMethodName();
			String type = bm.getType();
			int priority = bm.getPriority();
			
			System.out.println("Class name: " + name);
			System.out.println("Category: " + category);
			System.out.println("Method name: " + methodName);
			System.out.println("Type: " + type);
			System.out.println("Priority: " + priority); 
			System.out.println();
		}
	}
}
