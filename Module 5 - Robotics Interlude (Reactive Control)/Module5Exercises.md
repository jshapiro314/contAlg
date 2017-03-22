### Module 5 Exercises | Joshua Shapiro | 30 March 2017

1. CODE: Recall the winch example: Winch.java. Try different torque values to lift the load to the desired height. You will also need Function.java and SimplePlotPanel.java. Upon clicking "stop" you will see how the distance y varies with time.

2. CODE: Implement proportional control for the horizontal case in the nextStep() method of Winch2.java. Experiment with different values of the constant Kp, for example Kp = 5, 10, 50. What do you observe? Where have you seen this dynamic behavior before? What is the value of yMax in the code?

3. CODE: Implement proportional-derivative control for the horizontal case in Winch3.java. Experiment with different values of the constants Kp and Kd. For example: Kd = 10, 100, 1000 (using previous values of Kp). Does any value of Kd fix the problem?

4. CODE: Apply PD-control to the vertical case, writing that version in Winch4.java. Again, experiment with different values of Kp. What do you observe?

5. CODE: Apply PID-control to the vertical case, writing your code in Winch5.java. Experiment with different Ki values.

6. CODE: Download and unpack carSim.jar. Use java CarGUI manual on the command-line to run in manual mode, then select the Unicycle and "basic sensor". Drive around some obstacles (scene-3 or scene-4) and observe how the sensors work.

7. CODE: Examine the file BasicSensorPack.java. You can access the variable sonarDistance[] to get these distances. Here, sonarDistance[0] is the distance in front (North of the vehicle). Next, use this template to write a simple car controller for Scene-3 that goes towards the obstacle, stops just before it and turns left to face North. Don't forget to select "Basic sensor" in carGUI when testing.

8. CODE: Implement the above algorithm in your controller using $\alpha = 10, \delta = 40$ and a forward velocity of 10, with Scene-3 as the setting. The NE distance is sonarDistance[7] whereas the SE distance is sonarDistance[5] (going anti-clockwise). Recall that we need to set the steering angle $\phi > 0$ to turn left and $\phi < 0$ to turn right. What do you notice when the unicycle tries to turn right at the obstacle corner?

9. CODE: Implement the above algorithm in your controller. Use vel=2 as the reduced speed. Does it solve the problem?

10. CODE: Implement proportional control in your controller. Use a small (e.g. vel=2) velocity throughout. Experiment with different values of the proportionality constant Kp. What do you observe when the vehicle turns a corner?

11. CODE: Add derivative control to your controller. Experiment with different values of the two proportionality constants Kp and Kd. What do you observe?

12. CODE: See if bounding the sensor values (as inputs to the algorithm) makes a difference. Observe what happens as the vehicle turns near the third obstacle corner (near the x-axis, as it's headed down).

13. CODE: Implement the above algorithm and see if it works. Can you suggest improvements?

14. CODE: Implement the above in MySimpleCarController2.java and see if it works. What do you observe?

15. CODE: Implement the above algorithm and see if it works.

16. CODE: Implement the above in MySimpleCarController3.java and see if it works. The open-to-goal test has been implemented for you.

17. How can you determine whether it's closer to turn clockwise vs. anticlockwise?

18. What happens if we are too close to an obstacle on the left side? What changes are needed for obstacle avoidance on the left?

19. Assuming obstacle avoidance works, draw on paper a scenario on paper where the above goal-seeking algorithm fails to reach the goal. Suggest a better algorithm.
