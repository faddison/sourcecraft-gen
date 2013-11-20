package decorator;

// This object is stored in a list in BuildingData

public class BugData {

	private int bugType;
	private int bugPriority;
	private String methodName;
	
	public BugData (int bugType, int bugPriority, String methodName) {
		this.bugType = bugType;
		this.bugPriority = bugPriority;
		this.methodName = methodName;
	}
	
	public int getBugType() {
		return bugType;
	}
	public void setBugType(int bugType) {
		this.bugType = bugType;
	}
	public int getBugPriority() {
		return bugPriority;
	}
	public void setBugPriority(int bugPriority) {
		this.bugPriority = bugPriority;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String toString() {
		return "bugtype: " + this.bugType + " methodName: " + this.methodName;
	}
	
	
}
