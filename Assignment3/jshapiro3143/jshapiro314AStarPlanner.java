// Template for implementing Cost-Based Planner.

import java.util.*;


public class jshapiro314AStarPlanner implements jshapiro314Planner {

    // Limit the total number of expansions.
    static int maxMoves = 100000;

    // The frontier = all those states that have been generated but not
    // yet explored (visited).
    LinkedList<State> frontier;

    // The list of all states that have been visited.
    LinkedList<State> visitedStates;

    // Count # moves.
    int numMoves;


    public LinkedList<State> makePlan (jshapiro314PlanningProblem problem, State start, Boolean isPrePlanning, double targetX, double targetY)
    {
        // Initialize.
	frontier = new LinkedList<State> ();
	visitedStates = new LinkedList<State> ();
	numMoves = 0;

        // The start node is the first one to place in frontier.
	frontier.add (start);

	while (numMoves < maxMoves) {

            // If nothing to explore, we're done.
	    if (frontier.size() == 0) {
		break;
	    }

	    // Get first node in frontier and expand.
	    State currentState = removeBest ();
            // problem.drawState (currentState);

            // If we're at a goal node, build the solution.
	    if (problem.satisfiesGoal (currentState, targetX, targetY)) {
		return makeSolution (currentState);
	    }

	    numMoves ++;

	    // Put in visited list.
	    visitedStates.add (currentState);

	    // Expand current state (look at its neighbors) and place in frontier.
	    ArrayList<State> neighbors = problem.getNeighbors (currentState);
	    for (State s: neighbors) {
		if ( ! visitedStates.contains (s) ) {
                    int index = frontier.indexOf (s);
                    if (index >= 0) {
                        State altS = frontier.get (index);
                        if (s.costFromStart < altS.costFromStart) {
                            frontier.set (index, s);
                        }
                    }
                    else {
                        frontier.add (s);
                    }
		}
	    }

	    if (numMoves % 100 == 0) {
		System.out.println ("After " + numMoves + ": |F|=" + frontier.size() + "  |V|=" + visitedStates.size());
	    }

	} // endwhile

	System.out.println ("Cost-based: No solution found after " + numMoves + " moves");
	return null;
    }


    State removeBest ()
    {
        // INSERT YOUR CODE HERE
        State bestNode  = frontier.get(0);
        double minCost = bestNode.costFromStart + bestNode.estimatedCostToGoal;
        for(int i=1;i<frontier.size();i++){
        	if(frontier.get(i).costFromStart + frontier.get(i).estimatedCostToGoal < minCost){
        		bestNode = frontier.get(i);
        		minCost = frontier.get(i).costFromStart + frontier.get(i).estimatedCostToGoal;
        	}
        }

        frontier.remove(bestNode);
        return bestNode;
    }



    public LinkedList<State> makeSolution (State goalState)
    {
	LinkedList<State> solution = new LinkedList<State> ();
	solution.add (goalState);

        // Start from the goal and work backwards, following
        // parent pointers.
	State currentState = goalState;
	while (currentState.getParent() != null) {
	    solution.addFirst (currentState.getParent());
	    currentState = currentState.getParent();
	}

	System.out.println ("Cost: Solution of length=" + solution.size() + " found with cost=" + goalState.costFromStart + " after " + numMoves + " moves");

	return solution;
    }

    public void prePlan(jshapiro314PlanningProblem problem, State start){
        
    }
}
