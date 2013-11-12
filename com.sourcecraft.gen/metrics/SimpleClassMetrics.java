package metrics;

import java.io.Serializable;
import java.util.List;

public class SimpleClassMetrics extends AbstractMetrics
{

	private List<String> methodNames;
	private List<String> attributeNames;
	private int numMethods;
	private int numAttributes;
	private int numLOC;
	private String className;
	private String packageName;
	
	public SimpleClassMetrics() {
		
	}
	
	public SimpleClassMetrics(String className, int numMethods, int numAttributes) {
		this.className = className;
		this.numMethods = numMethods;
		this.numAttributes = numAttributes;
	}

	public int getNumMethods() {
		return numMethods;
	}

	public void setNumMethods(int numMethods) {
		this.numMethods = numMethods;
	}

	public int getNumAttributes() {
		return numAttributes;
	}

	public void setNumAttributes(int numAttributes) {
		this.numAttributes = numAttributes;
	}

	public int getNumLOC() {
		return numLOC;
	}

	public void setNumLOC(int numLOC) {
		this.numLOC = numLOC;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public List<String> getMethodNames() {
		return methodNames;
	}

	public void setMethodNames(List<String> methodNames) {
		this.methodNames = methodNames;
	}

	public List<String> getAttributeNames() {
		return attributeNames;
	}

	public void setAttributeNames(List<String> attributeNames) {
		this.attributeNames = attributeNames;
	}

	
}
