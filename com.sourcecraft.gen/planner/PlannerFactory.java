package planner;

public class PlannerFactory {
	
	public static AbstractPlanner create(PlannerType type)
	{
		switch (type)
		{
			case SIMPLEGRIDPLANNER:
				return new SimpleGridPlanner();
			case COMPLEXGRIDPLANNER:
				return new ComplexGridPlanner();
			default:
				return null;			
		}
	}

}
