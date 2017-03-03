
import java.util.*;

public class MazeHandPlanner implements Planner {

    public LinkedList<State> makePlan (PlanningProblem problem, State start)
    {
	LinkedList<State> plan = new LinkedList<State> ();
	State s = new MazeState (null,10,0,2);
	plan.add (s);
	plan.add (new MazeState (null,10,0,3));
	plan.add (new MazeState (null,10,0,4));
	plan.add (new MazeState (null,10,1,4));
	plan.add (new MazeState (null,10,1,5));
	plan.add (new MazeState (null,10,1,6));
	plan.add (new MazeState (null,10,2,6));
	plan.add (new MazeState (null,10,3,6));
	plan.add (new MazeState (null,10,3,5));
	plan.add (new MazeState (null,10,3,4));
	return plan;
    }

}
