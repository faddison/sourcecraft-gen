package metrics;

import java.util.ArrayList;

import parser.ParseBugs;
import parser.ParseClasses;

public class SimpleClassAndBugsMetrics extends AbstractMetrics {

	//ArrayList<SimpleClassMetrics> classes = new ArrayList<SimpleClassMetrics>();
	//ArrayList<SimpleBugsMetrics> bugs = new ArrayList<SimpleBugsMetrics>();
	
	public SimpleClassAndBugsMetrics() {
		super();
	}

	public static ArrayList<SimpleClassMetrics> startParsing(String sourcefile, String bugfile) {

		ParseClasses pc = new ParseClasses();
		ArrayList<SimpleClassMetrics> classes = pc.parse(sourcefile);

		ParseBugs pb = new ParseBugs();
		ArrayList<SimpleBugsMetrics> bugs = pb.parse(bugfile);

		combineResults(classes, bugs);
		printSeverity(classes);
		
		return classes;
	}
	
	/*
	 * Update the severity field of each class in which a bug was found (default severity=0)
	 */
	private static void combineResults(ArrayList<SimpleClassMetrics> classes, ArrayList<SimpleBugsMetrics> bugs) {
		
		for (int i=0; i<bugs.size(); i++) {
		
			int severity = bugs.get(i).getPriority();
			String className = bugs.get(i).getClassName();
			
			for (int j=0; j<classes.size(); j++) {
				String currentClass = classes.get(j).getClassName();
				
				if (currentClass.equalsIgnoreCase(className)) {
					classes.get(j).setSeverity(severity);
				}
			}
		}
	}
	
	private static void printSeverity(ArrayList<SimpleClassMetrics> classes) {
		
		for (int i=0; i<classes.size(); i++) {
			
			SimpleClassMetrics cp = classes.get(i);
			
			String name = cp.getClassName();
			int severity = cp.getSeverity();
			
			System.out.println("Class name: " + name);
			System.out.println("Severity: " + severity);
			System.out.println();
		}
	}
	
//	public static void main(String[] args) {
//		String sourcefile = "sources/SweetHomeStructure.xml";
//		String bugfile = "sources/SweetHomeBugResults.xml";
//		startParsing(sourcefile, bugfile);
//	}

}
