Joshua Shapiro
jshapiro314@gwu.edu
9 February 2017

Assignment 1 Submission:

My submission consists of 2 files which will be explained below.

jshapiro314CarSimulator.java:
	This is the file that tested all possible values to give me the optimal v1, v2, and theta values. It can be compiled with javac jshapiro314CarSimulator.java and can be run with java jshapiro314CarSimulator. It does a brute force approach testing all possible combinations of velocities between 0 and 10 and angles between 0 and 90 degrees. In order to speed up this process, I intuited that the best value of v1 (the left wheel) would be the fastest it could possibly go, so I kept that at a constant 10 and only varied v2 and theta. The step size for v2 was set at 0.1 (Interval variable) and the step size for theta was 0.001 radians (IntervalAngle variable). The rest of the code performs as required, with one other optimization. In step 1 (turning to set the initial angle), I simply move the left most wheel (v1) backwards. This rotate the car around its right wheel instead of its center, which allows for a shallower angle for the arc when approaching the goal. This seems to decrease the total time it takes to reach the end. In simulation I found that my best parameters were v1 = 10, v2 = 9.4, and theta = 0.75 radians. My total time was 10.81 seconds.

jshapiro314CarController.java:
	This is the file that runs the optimal parameters in the GUI. It can be compiled with javac jshapiro314CarController.java and can be run with java CarGUI auto and then loading in jshapiro314CarController. Due to the time step size of the GUI simulation, the final rotation back to 0 had to be slowed down so it didn't overshoot its goal. Additionally, this controller will work flawlessly with the accuracy set to low, but it will not have the car face right since it reaches the goal on the arc and the simulator stops. If set to high accuracy, the car will properly rotate to the correct direction and drive over the point, but because of the issue with time step size it cannot get to exactly 500,50. Instead it reaches 498.7,51.5. So while the car will not stop on its own, if you look at the terminal when the car runs over the goal on the GUI you can see its x and y coordinates to confirm it is getting extremely close. With low accuracy, I received a time of 10.1 seconds and with high accuracy I received a time of around 11.1 seconds (as shown in the terminal window when running the GUI simulator). 
