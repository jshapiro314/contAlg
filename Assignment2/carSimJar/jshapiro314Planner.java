// Implementation of A* planner.
// Heavily based off of Professor Simha's code

import java.util.*;


public class jshapiro314Planner {

    // Limit the total number of expansions.
    static int maxMoves = 100000;

    // The frontier = all those states that have been generated but not
    // yet explored (visited).
    LinkedList<jshapiro314UnicycleState> frontier;

    // The list of all states that have been visited.
    LinkedList<jshapiro314UnicycleState> visitedStates;
    
    // Count # moves.
    int numMoves;

    //CONSTRUCTOR
    public jshapiro314Planner(){
        //do nothing
    }

    public LinkedList<jshapiro314UnicycleState> makePlan (jshapiro314UnicycleProblem problem, jshapiro314UnicycleState start)
    {
        // Initialize.
	frontier = new LinkedList<jshapiro314UnicycleState> ();
	visitedStates = new LinkedList<jshapiro314UnicycleState> ();
	numMoves = 0;

        // The start node is the first one to place in frontier.
	frontier.add (start);

	while (numMoves < maxMoves) {

            // If nothing to explore, we're done.
	    if (frontier.size() == 0) {
		break;
	    }

	    // Get first node in frontier and expand.
	    jshapiro314UnicycleState currentState = removeBest ();
            // problem.drawState (currentState);

            // If we're at a goal node, build the solution.
	    if (problem.satisfiesGoal (currentState)) {
		return makeSolution (currentState);
	    }

	    numMoves ++;

	    // Put in visited list.
	    visitedStates.add (currentState);

	    // Expand current state (look at its neighbors) and place in frontier.
	    ArrayList<jshapiro314UnicycleState> neighbors = problem.getNeighbors (currentState);
        //System.out.println("SIZE = " + neighbors.size());
	    for (jshapiro314UnicycleState s: neighbors) {
		if ( ! visitedStates.contains (s) ) {
                    int index = frontier.indexOf (s);
                    if (index >= 0) {
                        jshapiro314UnicycleState altS = frontier.get (index);
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


    jshapiro314UnicycleState removeBest ()
    {
        // INSERT YOUR CODE HERE
        jshapiro314UnicycleState bestNode  = frontier.get(0);
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
    


    public LinkedList<jshapiro314UnicycleState> makeSolution (jshapiro314UnicycleState goalState)
    {
	LinkedList<jshapiro314UnicycleState> solution = new LinkedList<jshapiro314UnicycleState> ();
	solution.add (goalState);

        // Start from the goal and work backwards, following
        // parent pointers.
	jshapiro314UnicycleState currentState = goalState;
	while (currentState.getParent() != null) {
	    solution.addFirst (currentState.getParent());
	    currentState = currentState.getParent();
	}

	System.out.println ("Cost: Solution of length=" + solution.size() + " found with cost=" + solution.get(solution.size()-1).costFromStart + " after " + numMoves + " moves\n\n\n");


    for(int i=0;i<solution.size();i++){
        System.out.println(solution.get(i));
    }
	return solution;
    }    

}
