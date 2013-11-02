package designer;

public class DesignerFactory {

	public static AbstractDesigner<?> create(DesignerType type)
	{
		switch (type)
		{
			case SIMPLEDESIGNER:
				return new SimpleDesigner();
			case COMPLEXDESIGNER:
				return new ComplexDesigner();
			default:
				return null;			
		}
	}
}
