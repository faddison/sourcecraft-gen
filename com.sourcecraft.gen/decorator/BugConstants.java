package decorator;

public class BugConstants {
	
	// These numbers can be changed if needed
	// 6 is the worst bug type, 1 is the least
	
	public static final int STYLE = 1;
	public static final int BAD_PRACTICE = 2;
	public static final int CORRECTNESS = 3;
	public static final int EXPERIMENTAL = 4;
	public static final int MALICIOUS_CODE = 5;
	public static final int PERFORMANCE = 6;
	
	
	// Helper method to transform String -> int when creating a BugData object
	
	public static int setBugRating(String bugName) {
		if (bugName.equals("STYLE"))
			return STYLE;
		else if(bugName.equals("BAD_PRACTICE"))
			return BAD_PRACTICE;
		else if (bugName.equals("CORRECTNESS"))
			return CORRECTNESS;
		else if (bugName.equals("EXPERIMENTAL"))
			return EXPERIMENTAL;
		else if (bugName.equals("MALICIOUS_CODE"))
			return MALICIOUS_CODE;
		else if (bugName.equals("PERFORMANCE"))
			return PERFORMANCE;
		else
			return 0;
				
				
	}
	
}
