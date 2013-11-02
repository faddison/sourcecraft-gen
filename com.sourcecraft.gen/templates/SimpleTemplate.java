package templates;

import designer.DesignerFactory;
import designer.DesignerType;
import planner.PlannerFactory;
import planner.PlannerType;

public class SimpleTemplate extends AbstractTemplate {
	
	public SimpleTemplate()
	{
		super();
		planner = PlannerFactory.create(PlannerType.SIMPLEGRIDPLANNER);
		designer = DesignerFactory.create(DesignerType.SIMPLEDESIGNER);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
