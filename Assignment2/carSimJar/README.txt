Joshua Shapiro
jshapiro314@gwu.edu
2 March 2017

Assignment 2 Submission:

I used an A* path finding approach to calculate the path to goal. My cost from start increased by 5 at each change in state since states were 5 pixels apart. My estimated cost to goal is the euclidean distance between the current state and the goal. Once the A* algorithm found a path. the solution list was passed to the move method. There it looks at the current state and the next state. It then rotates the car until it reaches the appropriate rotation, and after that sets the velocity so that the next state will be reached. The velocity and rotational velocity are never set at the same time -- the car only either rotates or moves forward. Once the car reached the next state, it was set as the current state and the next state was pulled from the solution. This continued until the car reached the goal. To check for obstacles, a bounding rectangle was put around the unicycle. In the isValid method that checks if a neighbor is valid, this bounding rectangle is checked to see if it intersects with any of the obstacles. As the state stores the rotation of the unicycle, the bounding rectangle is also rotated. This allows for a more accurate check since it prevents a corner from hitting an obstacle. Originally an eclipse was used instead of a bounding rectangle, but the hits function in the simulator uses a rectangle. This resulted in visually successful paths with no hits, but the hit counter would still show that collisions occured.

My submission consists of 5 modified files which will be explained below. The files not explained below were part of the original carSim jar.



jshapiro314UnicycleState.java:
	This file is a modified version of the ArmStates file from module 2. It holds the x and y coordinates of the unicycle at the given state as well as its orientation and its parent state.
jshapiro314Planner.java:
	This file is a modified version of the ArmPlanner file from module 2. Not much has changed, other than support for the unicycle states mentioned above. This class handles actually running the A* algorithm and returns the solution.
jshapiro314UnicycleProblem:
	This file holds the methods unique to solving the unicycle problem. It was based off of the ArmProblem file from module 2. This class holds the method that checks if neighbor states are valid (which was explained above) as well as the method that finds the neighbor states to begin with. The neighbor finding method is extremely similar to the Arm neighbor finding method. It looks 5 pixels away from the current state in 8 equidistant directions. These neighbors are then checked to make sure they are valid and then added to the frontier. Methods in this class are typically called by jshapiro314Planner.java.
jshapiro314UnicycleController.java
	This is the file that is loaded into the simulator to actually run the code. It instantiates the other classes as needed, calls the planner, passes the solution to the move method, and then iterates over each state as explained above. It also is responsible for printing the states and current location of the unicycle to the screen as the path is traversed.
CarGUI.java
	This was part of CarSimJar, but was modified to support the custom scene. Instead of changing scene 1, I changed scene 5 since its goal was already off the screen. Now scene 5 illustrates that the unicycle will not take the shortest path if it can't fit through it.


To compile unpack the jar, run javac *.java
To run type "java CarGUI auto" and then in the load-controller box type "jshapiro314UnicycleController"

I am using java version 1.8.0_77 on macOS 10.12.3
