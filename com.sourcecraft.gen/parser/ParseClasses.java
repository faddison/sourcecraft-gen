package parser;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import metrics.SimpleBugsMetrics;
import metrics.SimpleClassMetrics;

public class ParseClasses extends AbstractParser<SimpleClassMetrics> {

	private ArrayList<SimpleClassMetrics> classes = new ArrayList<SimpleClassMetrics>();
	
	// Parsing SweetHomeStructure.xml
	public ArrayList<SimpleClassMetrics> parse(String filename) {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			SimpleClassParser handler = new SimpleClassParser();
			saxParser.parse(filename, handler);
			classes = handler.getClassMetrics();
			
			//printMetrics();
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return classes;
	}
	
	private void printMetrics() {
				
		for (int i=0; i<classes.size(); i++) {
			
			SimpleClassMetrics cp = classes.get(i);
			
			String name = cp.getClassName();
			String pName = cp.getPackageName();
			int methods = cp.getNumMethods();
			int attributes = cp.getNumAttributes();
			int severity = cp.getSeverity();
			
			System.out.println("Class name: " + name);
			System.out.println("Package name: " + pName);
			System.out.println("Number of methods: " + methods);
			System.out.println("Number of attributes: " + attributes);
			System.out.println("Severity: " + severity);
			System.out.println();
		}
	}
}
