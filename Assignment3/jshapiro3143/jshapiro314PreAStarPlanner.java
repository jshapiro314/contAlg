// Template for implementing Cost-Based Planner.

import java.util.*;


public class jshapiro314PreAStarPlanner implements jshapiro314Planner {

    // Limit the total number of expansions.
    static int maxMoves = 100000;

    // The frontier = all those states that have been generated but not
    // yet explored (visited).
    LinkedList<State> frontier;

    // The list of all states that have been visited.
    LinkedList<State> visitedStates;

    // Count # moves.
    int numMoves;

    // Create a hashtable that will store the precomputed plans.
    // It's key will be a 2 element array [x,y] and the value will be a linkedList<State> of the actual plans
    HashMap<double[], LinkedList<State>> prePlans = new HashMap<double[], LinkedList<State>>();


    public LinkedList<State> makePlan (jshapiro314PlanningProblem problem, State start, Boolean isPrePlanning, double targetX, double targetY)
    {
        // Initialize.
	frontier = new LinkedList<State> ();
	visitedStates = new LinkedList<State> ();
	numMoves = 0;

    //Check if we are preplanning (if so, we don't want to check the preplanned routes since they haven't been made yet!)
    if(!isPrePlanning){
        //If the preplanned routes have been made, lets see which of their goals is closest to the desired goal (euclidean distance).
        Set prePlannedGoals = prePlans.keySet();
        double[] closestGoal = new double[2];
        double closestDistance = Double.MAX_VALUE;

        Iterator it = prePlannedGoals.iterator();
        while(it.hasNext()){
            double[] temp = (double[])it.next();
            double distance = Math.sqrt((temp[0]-targetX) * (temp[0]-targetX) + (temp[1]-targetY) * (temp[1]-targetY));
            if(distance < closestDistance){
                closestDistance = distance;
                closestGoal = temp;
            }
        }

        //Now that we've identified the prePlan we are using, we need to change the start state to be the end state of the prePlan.
        System.out.println("using the preplan that bring the arm to: " + Arrays.toString(closestGoal));
        start = prePlans.get(closestGoal).getLast();
    }

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

    //Now add the prePlanned solution to the front of the solution (minus the last element since its the first element of the solution)
    //selectedPlan.removeLast();
    //solution.addAll(0,selectedPlan);

	return solution;
    }

    public void prePlan(jshapiro314PlanningProblem problem, State start){
        //To begin, we need to decide on how many preplans we are making, and where the preplans are going.
        //For the sake of the assignment, I will be using 5 preplans, and I have already decided the coordinates of the points.
        //These coordinates take into account the size of the arm, so if more links are added or the arm size changes these
        //points will work well for preplanning. If I wanted to allow the user to choose the number of preplans, the code would
        //have to be significantly modified to allow for destinations to be generated on the fly. This would require attempting
        //to get a uniform distribution in the destinations. As I am an undergrad this is not required.


        //To begin, lets generate our 5 end coordinates.
        jshapiro314ArmProblem myProblem = (jshapiro314ArmProblem)problem;
        double radius = myProblem.linkSize * (myProblem.numNodes - 1);

        double[] end1 = new double[2];
        double[] end2 = new double[2];
        double[] end3 = new double[2];
        double[] end4 = new double[2];
        double[] end5 = new double[2];

        end1[0] = radius/2 * Math.cos(Math.PI/2/3);
        end1[1] = radius/2 * Math.sin(Math.PI/2/3);

        end2[0] = radius/2 * Math.cos(2*Math.PI/2/3);
        end2[1] = radius/2 * Math.sin(2*Math.PI/2/3);

        end3[0] = 3*radius/4 * Math.cos(Math.PI/2/4);
        end3[1] = 3*radius/4 * Math.sin(Math.PI/2/4);

        end4[0] = 3*radius/4 * Math.cos(2*Math.PI/2/4);
        end4[1] = 3*radius/4 * Math.sin(2*Math.PI/2/4);

        end5[0] = 3*radius/4 * Math.cos(3*Math.PI/2/4);
        end5[1] = 3*radius/4 * Math.sin(3*Math.PI/2/4);

        System.out.println(Arrays.toString(end1));
        System.out.println(Arrays.toString(end2));
        System.out.println(Arrays.toString(end3));
        System.out.println(Arrays.toString(end4));
        System.out.println(Arrays.toString(end5));
        //Now lets calculate plans to each of these coordinates. Pass in true since we are preplanning.
        LinkedList<State> plan1 = makePlan(problem, start, true, end1[0], end1[1]);
        System.out.println("Ended plan 1");
        LinkedList<State> plan2 = makePlan(problem, start, true, end2[0], end2[1]);
        System.out.println("Ended plan 2");
        LinkedList<State> plan3 = makePlan(problem, start, true, end3[0], end3[1]);
        System.out.println("Ended plan 3");
        LinkedList<State> plan4 = makePlan(problem, start, true, end4[0], end4[1]);
        System.out.println("Ended plan 4");
        LinkedList<State> plan5 = makePlan(problem, start, true, end5[0], end5[1]);
        System.out.println("Ended plan 5");

        //Now we will store the goal coordinates and the plans in the hashmap preplans
        prePlans.put(end1, plan1);
        prePlans.put(end2, plan2);
        prePlans.put(end3, plan3);
        prePlans.put(end4, plan4);
        prePlans.put(end5, plan5);
    }

}
