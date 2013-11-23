package metrics;

public class SimpleBugsMetrics extends AbstractMetrics {

	private int priority;
	private String type;
	private String category;
	private String className;
	private String methodName;
	
	public SimpleBugsMetrics() {
		
	}
	
	private SimpleBugsMetrics(String className, String methodName, String type, int priority) {
		this.className = className;
		this.methodName = methodName;
		this.type = type;
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getSeverity() {
		return 0;
	}
	
	
}
