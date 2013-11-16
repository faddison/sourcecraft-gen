package parser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import metrics.SimpleBugsMetrics;

public class SimpleBugsParser extends DefaultHandler {

	private ArrayList<SimpleBugsMetrics> bugs = new ArrayList<SimpleBugsMetrics>();
	private SimpleBugsMetrics sbm = new SimpleBugsMetrics();

	public ArrayList<SimpleBugsMetrics> getBugs() {
		return bugs;
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("BugInstance")) {

			String type = attributes.getValue("type");
			String category = attributes.getValue("category");
			String priority = attributes.getValue("priority");
			
			sbm = new SimpleBugsMetrics();
			sbm.setPriority(Integer.parseInt(priority));
			sbm.setType(type);
			sbm.setCategory(category);
		}

		if (qName.equalsIgnoreCase("Sourceline")) {

			String className = attributes.getValue("sourcefile");
			sbm.setClassName(className);
		}

		if (qName.equalsIgnoreCase("Method")) {

			String methodName = attributes.getValue("name");
			sbm.setMethodName(methodName);
		}	
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("BugInstance")) {
			bugs.add(sbm);
			
		}
	}

	public void characters(char[] ch, int start, int length) {

	}
	
}
