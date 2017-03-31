Joshua Shapiro
jshapiro314@gwu.edu
30 March 2017

Assignment 3 Submission:
I am using java version 1.8.0_77 on macOS 10.12.3
Each section below contains a summary and notes about how the problem was solved, results, and how to run the code. Any files that were modified or written by me start with jshapiro314.

******************************************
Part 1: Car Chase
******************************************

***Summary:
*************************************
The car chase consists of 2 files, jshapiro314CarChase.java and jshapiro314TrooperSimulator.  jshapiro314CarChase.java handles the GUI and is based off of Professor Simha's Incline.java. It shows a blue dot representing the speeder and a red dot representing the cop. When the cop catches up to the speeder, the GUI will pause the simulation and show the time it took for the cars to catch up as well as the current position of the cop and the speeder. Note: the positions will vary slightly as the timestep used in the GUI is too coarse to get an exact measurement (the timestep is set at 0.1 seconds). jshapiro314TrooperSimulator handles the actual equations that are used to calculate the position of the cop and the speeder. It has a move method which is called by jshapiro314CarChase.java to update the position of each car by 1 timestep. jshapiro314TrooperSimulator also has a solve method which prints out the time when the cars meet as well as the distance of the speeder (which should be about the same as the distance of the cop). Effectively, this runs the simulation without the GUI. It is also more accurate since the timestep is set at 0.001 seconds instead of 0.1 seconds.

***Results:
*************************************
Using the GUI (less precise results): The speeder will be caught at t = 16.6 seconds. The speeder will be at 1660 and the cop will be at 1663.32
Using the simulator (more precise): The speeder will be caught at t = 16.667 seconds. The speeder will be at 1666.59 and the cop will be at 1666.63

***How to run the code:
*************************************
1) javac *.java
2) To run just the simulator (no gui): java jshapiro314TrooperSimulator
3) To run the gui: java jshapiro314CarChase. NOTE: YOU MAY NEED TO ADJUST THE SCREEN SIZE



******************************************
Part 2: Skydiver
******************************************

***Summary:
*************************************
The skydiver problem consists of 2 files, jshapiro314SkyDive.java and jshapiro314SkyDiverSimulator.java. It is organized in the same way that the Car Chase is. jshapiro314SkyDive.java handles the GUI and is based off of Professor Simha's Incline.java. It shows a red dot representing the skydiver and it turns blue when the parachute is deployed. To decide when the parachute is deployed, jshapiro314SkyDive.java contains a double variable called deployTime. It stores the time the parachute should open in milliseconds. THE GUI DOES NOT GENERATE THIS VALUE ON ITS OWN. Instead, jshapiro314SkyDiverSimulator.java can output this value. Then this value should be entered in jshapiro314SkyDive.java in MILLISECONDS. For the sake of submission, jshapiro314SkyDive.java already has the value filled in, so the GUI works as it should. When the skydiver hits the ground, the simulation pauses and shows the time, height of the skydiver, and velocity. Because of the step size (which is set to 0.001) being too coarse, the skydiver's final distance will be slightly less than 0. Also, it should be noted that the velocity will be negative, since the skydiver is falling down. jshapiro314SkyDiverSimulator.java is similar to jshapiro314TrooperSimulator.java. It has a move method which is called by jshapiro314SkyDive.java to update the position of the skydiver by 1 timestep. Additionally it has a solve method which prints out when the parachute should be opened and the final velocity when the skydiver hits the ground. This solve method works by opening the parachute at different times until it finds the time that leads to a final velocity close to -40 (since velocity will be negative). This also uses a stepsize of 0.001. The time this output is what should be input to jshapiro314SkyDive.java.

***Results:
*************************************
The parachute should be opened at about 12.874 seconds and will result in a final velocity of around -39.95 (these numbers are from the simulator not the GUI. In the GUI it will show -39.75).

***How to run the code:
*************************************
1) javac *.java
2) To run just the simulator (no gui): java jshapiro314SkyDiverSimulator
3) To run the gui: java jshapiro314SkyDive. NOTE: YOU MAY NEED TO ADJUST THE SCREEN SIZE



******************************************
Part 3: Pre-A* Planner
******************************************

***Summary:
*************************************
The Pre-A* Planner consists of a lot of the same files that were in the Planning.jar provided in module 3. However, I removed any files that were not associated with the ArmProblem, and I modified the interfaces for the PlanningProblem and Planner to better for my solution. For this reason, my jshapiro314PreAStarPlanner.java can only be run in my GUI. I have also included jshapiro314AStarPlanner.java that has been modified to fit my interfaces. This allows a proper comparison between the two algorithms. As there are a lot of files with a lot of modifications, I will break down what was done to each file below.

ArmState.java: NO MODIFICATION
State.java: NO MODIFICATION
UniformRandom.java: NO MODIFICATION

jshapiro314ArmProblem.java: I modified the satisfiesGoal method to take in a state as well as the goal coordinates. Before, it simply looked at the global goal coordinates. This modification was done so I could run a planner from the start state to a pre state without having to modifiy the actual goal. I also removed the obstacle, as I didn't think it was pertinent to the assignment.

jshapiro314PreAStarPlanner.java: This is where the actual algorithm change was implemented. This required a couple changes to occur. First, the makePlan method now takes in the goal coordinates and a boolean to see if we are preplanning or planning. This allows the makePlan method to be called to compute pre-plans, and it also allows the makePlan method to find which prePlanned method to use when solving for the actual goal (this is what the boolean is for). The method prePlan is new. It generates 5 plans to 5 coordinates specified in the method. This method calls makePlan to generate the actual plans. This could be modified to generate as many coordinates as desired, but since I was only required to do 5 I chose 5 points using the following logic. The arm can cover almost all points in a quarter circle with a radius the total length of the arm. I then decided I wanted 3 points evenly spaced on a quarter-circle permiter with a radius of 3/4 the length of the arm, and 2 points evenly spaced on a quarter-circle perimter with a radius 1/2 the length of the arm. This allows the 5 points to still be useful even if the number of links or the size of each link for the robot is modified. These plans are then stored in a hashMap read by the makePlan method (when teh boolean value is set to false). The makePlan method decides which of the 5 points is closest to the goal (based on euclidean distance), and then just computes the steps from teh point to goal instead of from the start.

jshapiro314PlanningGUI.java: I removed all parts of the GUI that were not related to the the robot arm problem. I then modified the load button method to call my prePlan method from jshapiro314PreAStarPlanner.java. The plan button executes the makePlan method from jshapiro314PreAStarPlanner.java as it did before.

jshapiro314Planner.java: This is the interface extended by jshapiro314PreAStarPlanner. Since I had modified the makePlan method's inputs and I added a new required method called prePlan, I had to modify the interface to reflect those changes. This is also why the old planners created in class can't be loaded into my planningGUI and why my pre A* planner can't be loaded into another planningGUI.

jshapiro314PlanningProblem.java: This is the interface jshapiro314ArmProblem.java implements. I had to modify the satisfiesGoal method signature since I changed the inputs in jshapiro314ArmProblem.java.

jshapiro314AStarPlanner.java: I just added the prePlan method which doesn't do anything and modified the inputs for the makePlan method so it could properly extend the jshapiro314Planner interface and work with my jshapiro314PlanningGUI.


***Results:
*************************************

I tested both for 5 goal positions:

[250, 30]
    PreA*: length = 82, cost = 405 after 10 moves
    A*: length = 72, cost = 355 after 739 moves
[150, 100]
    PreA*: length = 85, cost = 420 after 5 moves
    A*: length = 74, cost = 365 after 1263 moves
[20, 70]
    PreA*: length = 27, cost = 130 after 13 moves
    A*: length = 4, cost = 15 after 4 moves
[80, 200]
    PreA*: length = 28, cost = 135 after 1 move
    A*: length = 25, cost = 120 after 24 moves
[60, 250]
    PreA*: length = 35, cost = 170 after 8 moves
    A*: length = 35, cost = 170 after 34 moves

As you can see, results vary significantly. PreA* typically yields faster solutions since it precomputes places, but since the places it precomputes have to be used when going to the goal, it doesn't always find the most direct path. For a simple problem where A* can compute all distances fast enough (like the current problem) Pre-A* probably isn't worth it. But if this was a far more complex arm with more joints, I could see why pre-A* could be useful.

***How to run the code:
*************************************
1) javac *.java
2) java jshapiro314PlanningGUI
3) Once the GUI opens, either load jshapiro314PreAStarPlanner (for the preplanned algorithm) or load jshapiro314AStarPlanner.
4) The GUI behaves just like the normal planningGUI from this point on. Press plan to generate a plan, next to step through it.
