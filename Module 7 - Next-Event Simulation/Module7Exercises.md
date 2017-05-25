---
geometry: top=1in, bottom=1in, left=1in, right=1in
---

### Module 7 Exercises | Joshua Shapiro | 24 May 2017

2. CODE: Download and execute Queue.java, which is a simulation of a single server queue. Cursorily examine the code.
    * What data structures are being used?
    * Where in the code are interarrival times being generated? From what distribution?
    * Where are service times being generated? From what distribution?

3. CODE: Add code in method randomInterarrivalTime() in Queue.java to estimate the average interarrival time. What does this number have to do with the value of variable arrivalRate in the program?

6. CODE: Execute Queue.java to estimate the average time in system.

7. CODE: Execute Queue.java to estimate the average waiting time. Subtract this from the estimate of the average system-time. What do you get? Is it what you expect?

8. CODE: Modify Queue.java to estimate the arrival rate $\lambda$ and see if it matches the stated arrival rate in the program. Relate this to your earlier estimate of the average interarrival time.

10. Fix the service rate at $\mu$ = 1 and vary the arrival rate: try $\lambda$ = 0.5, 0.75, 1.25. What do you observe when $\lambda$ = 1.25?

11. What about it?

12. Vary $\lambda$ as follows: 0.1, 0.2, ... , 0.9 and estimate d. What is the shape of the function d($\lambda,\mu$)?

14. For $\lambda$ = 0.75 and $\mu$ = 1, estimate the probability that an arriving customer finds the server free. Try this for $\lambda$ = 0.5, 0.6 as well. Can you relate this probability to $\lambda$ = 0.75 and $\mu$ = 1?

15. CODE: We will focus on two distributions: the distribution of the number in the system, and the system time. Let rv M denote the number of customers seen by an arriving customer, and let rv D denote the system time experienced by a random customer.
    * Is M discrete or continuous? What about D? What is the range of M? Of D?
    * For $\lambda$ = 0.75 and $\mu$ = 1, obtain the appropriate histogram of M. Which well-known distribution does this look like?
    * For $\lambda$ = 0.75 and $\mu$ = 1, obtain the appropriate histogram of D. Which well-known distribution does this look like?
    * Note: use PropHistogram.java or DensityHistogram.java as appropriate.

16. CODE: Download and execute the above simulation: ThreeQueue.java.
    * What is the average system time?
    * Examine the code to see where the system time is being accumulated.
    * Change the queue choice (between 1 and 2) to "shortest-queue" instead of "uniform-random" and see the difference this makes.
    * In what kind of application is "uniform-random" better than "shortest-queue"?
    * Measure the rate of departures from the system and explain the value you get.

19. Examine the code in the molecular simulation from Module 4. Is this synchronous or asynchronous?

20. Find a simulation of the Game-of-Life. Is this synchronous or asynchronous?

21. CODE: Fill in code in Raindrop.java to obtain hisograms of X and T respectively. Use s = 1, h = 10, p = 0.5.
    * What is the likely distribution of X?
    * Vary h: try h = 20, 30, 40, 50. What is the relationship between E[T] and h?
    * Note: use PropHistogram.java or DensityHistogram.java as appropriate

22. Do you know the historical significance of the distributions of X and T?

23. What is the size of the eventlist for the single-server queue? For the three-queue example?

24. If the eventlist has n events, how long does it take for each operation (in order-notation)?

25. If instead of a sorted-list, we use an unsorted list, how long does each operation take?
