package parser;

import java.util.ArrayList;

import metrics.AbstractMetrics;
import metrics.SimpleBugsMetrics;
import metrics.SimpleClassMetrics;

import parser.ParseBugs;
import parser.ParseClasses;

public class SimpleClassAndBugs {
	
	//static ArrayList<SimpleBugsMetrics> onlyBugs = new ArrayList<SimpleBugsMetrics>();
	
	public SimpleClassAndBugs() {
		super();
	}

	public static ArrayList<SimpleClassMetrics> startParsing(String sourcefile, String bugfile) {

		ParseClasses pc = new ParseClasses();
		ArrayList<SimpleClassMetrics> classes = pc.parse(sourcefile);

		ParseBugs pb = new ParseBugs();
		ArrayList<SimpleBugsMetrics> bugs = pb.parse(bugfile);

		printBugs(bugs);
		
		return classes;
	}
	
	private static void printBugs(ArrayList<SimpleBugsMetrics> bugs) {
		
		for (int i=0; i<bugs.size(); i++) {
			
			System.out.println("Bug #" + i);
			System.out.println("Class name: " + bugs.get(i).getClassName());
			System.out.println("Method name: " + bugs.get(i).getMethodName());
			System.out.println("Category: " + bugs.get(i).getCategory());
			System.out.println("Severity: " + bugs.get(i).getPriority());
			System.out.println();			
		}
	}
	
	public static void main(String[] args) {
		String sourcefile = "sources/SweetHomeStructure.xml";
		String bugfile = "sources/SweetHomeBugResults.xml";
		startParsing(sourcefile, bugfile);
	}

}
