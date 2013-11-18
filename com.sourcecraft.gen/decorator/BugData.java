package decorator;

// This object is stored in a list in BuildingData

public class BugData {

	private int bugType;
	private int bugSeverity;
	private String methodName;
	
	public BugData (int bugType, int bugSeverity, String methodName) {
		this.bugType = bugType;
		this.bugSeverity = bugSeverity;
		this.methodName = methodName;
	}
	
	public int getBugType() {
		return bugType;
	}
	public void setBugType(int bugType) {
		this.bugType = bugType;
	}
	public int getBugSeverity() {
		return bugSeverity;
	}
	public void setBugSeverity(int bugSeverity) {
		this.bugSeverity = bugSeverity;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
}
