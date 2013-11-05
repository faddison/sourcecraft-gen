package designer;

public class DesignerFactory {

	public static AbstractDesigner<?> create(DesignerType type)
	{
		switch (type)
		{
			case SIMPLEDESIGNER:
				return new SimpleDesigner();
			default:
				return null;			
		}
	}
}
