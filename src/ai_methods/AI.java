package ai_methods;

import units.Action;
import units.Scrum;

public interface AI {

	public abstract Action computeAction(Scrum s);
	
}
