---
geometry: top=1in, bottom=1in, left=1in, right=1in
---

### Module 8 Exercises | Joshua Shapiro | 15 June 2017


3. CODE: Suppose that X $\sim$ Uniform(0,1) and g(X) = $X^2$. Modify UniformExample.java to estimate the pdf of g(X) and its average value. You will also need RandTool.java and DensityHistogram.java What do you notice about the average value of g(X) compared with the average value of X?

* **The average value of x is 0.5000630164767703. The average value of g(X) is 0.33308660577988347. It seems like the average value of g(X) is relatively close to the average value of x squared. Below is the pdf.**
* **![alt](./images/q3.png)**

6. CODE: Suppose X $\sim$ Uniform(0,1) and g(X) is the function g(X) = $(x-0.5)^2$. Work out E[g(X)] by hand. Then estimate the average value of the rv g(X) by adding code to UniformExample4.java.

* **To calculate the expected value by hand...**
    * $E[g(X)] = \int_{-\infty}^{+\infty} g(X)f(X)$
    * $\implies E[g(X)] = \int_{0}^{1} (x-0.5)^2 \frac{1}{1-0}$
    * $\implies E[g(X)] = \int_{-0.5}^{0.5} (u)^2$
    * $\implies E[g(X)] = \Eval{\frac{u^3}{3}}{-0.5}{0.5}$
    * $\implies E[g(X)] = \frac{1}{12} = 0.0833$
* **The code shows the average value of g(X) to be 0.08302358930311014.**

7. Suppose:
    * X is a rv with range 9, 11 and pmf Pr[X = 9] = 0.5, Pr[X = 11] = 0.5
    * Y is a rv with range 8, 12 and pmf Pr[Y = 8] = 0.5, Pr[Y = 12] = 0.5
    * Z is a rv with range 8, 10, 12 and pmf Pr[Z = 8] = 0.1, Pr[Z = 10] = 0.8, Pr[Z = 12] = 0.1
    * Compute var[X], var[Y], var[Z].

* ** For X:**
    * $E[X] = \sum_{k} k * Pr[X = k] = 9 * 0.5 + 11 * 0.5 = 10$
    * $g(X) = (X-\mu)^2 = (X-10)^2$
    * $Var[X] = E[g(X)] = \sum_{k} (k-10)^2 * Pr[X = k] = 1 * 0.5 + 1 * 0.5 = 1$

* ** For Y:**
    * $E[Y] = \sum_{k} k * Pr[Y = k] = 8 * 0.5 + 12 * 0.5 = 14$
    * $g(Y) = (Y-\mu)^2 = (Y-14)^2$
    * $Var[Y] = E[g(Y)] = \sum_{k} (k-14)^2 * Pr[Y = k] = 36 * 0.5 + 4 * 0.5 = 20$

* ** For Z:**
    * $E[Z] = \sum_{k} k * Pr[Z = k] = 8 * 0.1 + 10 * 0.8 + 12 * 0.1 = 10$
    * $g(Z) = (Z-\mu)^2 = (Z-10)^2$
    * $Var[Z] = E[g(Z)] = \sum_{k} (k-10)^2 * Pr[Z = k] = 4 * 0.1 + 0 * 0.8 + 4 * 0.1 = 0.8$

8. CODE: Download and execute PointGeneratorExample.java. You will also need PointGenerator.java and PointDisplay.java. Increase the number of points to 1000. Can you guess the distribution of X? Of Y?

* **X seems to have a uniform distribution.**
* **Y seems to have a gaussian distribution.**
* **![alt](./images/q8.png)**

9. CODE: Download and modify PointGeneratorExample2.java to display density histograms for X and Y.

* **The histogram of X:**
    * **![alt](./images/q9a.png)**
* **The histogram of Y:**
    * **![alt](./images/q9b.png)**

10. CODE: Download and examine PointGeneratorExample3.java. The first part of the code estimates Pr[Y $\in$ [5,7]]. Add code below to estimate Pr[Y $\in$ [5,7] | X $\in$ [3,4]]. Are these events independent?

* **Pr[Y in [5,7]]: 0.34286**
* **Pr[Y in [5,7] | X in [3,4]]: 0.341532114095291**
* **As these two values are effectively equal, this shows that the events are independent (Pr[A|B] = Pr[A]).**

24. CODE: Execute UniformVarianceExample.java and obtain the two estimates.

* **Mean estimate: 0.5216167255358847**
* **Std-dev estimate: 0.28058637197873465**

26. If $\mu$' = 0.48 and $\sigma$' = 0.27 and obtain the number of samples needed.

* **Answer below:**
    * $n > (\frac{1.96 \sigma'}{0.03 \mu'})^2$
    * $\implies n > (\frac{1.96 * 0.27}{0.03 * 0.48})^2$
    * $\implies n > (\frac{0.05292}{0.0144})^2$
    * $\implies n > 3.675^2 \implies n > 13.5$

27. If $\mu$' = 0.48 and $\sigma$' = 0.27, obtain f when n = 500 samples are used.

* **Answer below:**
    * $f = \frac{1.96 \sigma'}{\sqrt{n} \mu'}$
    * $\implies f = \frac{1.96 * 0.27}{\sqrt{500} * 0.48}$
    * $\implies f = \frac{0.5292}{10.733126292} = 0.0493$

28. Express $\delta$ as a function of $\sigma$' and n.

* **Answer below:**
    * $f = \frac{1.96 \sigma'}{\sqrt{n} \mu'}$
    * $\implies \delta = f * \mu = \frac{1.96 \sigma' \mu}{\sqrt{n} \mu'}$
    * $\implies \delta = \frac{1.96 \sigma' \frac{S_n}{n}}{\sqrt{n} \frac{S_n * n}{N}}$
    * $\implies \delta = \frac{1.96 * \sigma' * N}{\sqrt{n} * n^2}$

29. Code: Use Stats.java to colect statistics about the single-server queue in Queue.java.
    * Estimate the mean interarrival time. How many samples are needed for a 5% (f = 0.05) confidence interval?
    * Estimate the mean time in system. Again, how many samples are needed for a 5% (f = 0.05) confidence interval? Consider that the true mean system time is E[S] = 4.0 when the arrival rate is 0.75 and the service rate is 1.0.
    * Explain why it may be inappropriate to compute confidence intervals when estimating the mean system time.

* **The mean interarrival time is 1.3544762695277544.**
* **For a 5% confidence interval:**
    * $n > (\frac{1.96 * \sqrt{1.912}}{0.05 * 1.354})^2$
    * $\implies n > (\frac{2.710}{0.0677})^2 = 1602.6$
* **The mean time in system is 2.814388534740915.**
* **For a 5% confidence interval:**
    * $n > (\frac{1.96 * \sqrt{8.388}}{0.05 * 2.814})^2$
    * $\implies n > (\frac{5.6766}{0.1407})^2 = 1627.7$
* **It may be inappropriate to compute confidence intervals when estimating the mean system time as the mean system time is dependent on the arrival rate and service rate. So it would only be appropriate to compute a confidence interval of a mean system time given an arrival rate and service rate.**












2. CODE: Download and execute Queue.java, which is a simulation of a single server queue. Cursorily examine the code.
    * What data structures are being used?
    * Where in the code are interarrival times being generated? From what distribution?
    * Where are service times being generated? From what distribution?

* **The data structures are a linkedList and PriorityQueue.**
* **scheduleArrival calls randomInterarrivalTime to generate an interarrival. This random value is being generated by an exponential distribution.**
* **scheduleDeparture calls randomServiceTime to generate a service time. This random value is being generated by an exponential distribution.**

3. CODE: Add code in method randomInterarrivalTime() in Queue.java to estimate the average interarrival time. What does this number have to do with the value of variable arrivalRate in the program?

* **Since arrivalRate is the gamma value, the average interarrival time is 1.3558307457972822, about 1/arrivalRate. The arrivalRate was set to 0.75.**

6. CODE: Execute Queue.java to estimate the average time in system.

* **The average time in system is 2.814388534740915.**

7. CODE: Execute Queue.java to estimate the average waiting time. Subtract this from the estimate of the average system-time. What do you get? Is it what you expect?

* **average system time - average waiting time = 2.814 - 1.891 = 0.923. This means the average service time is 0.923. This makes sense as the service time is pulled from an exponential distribution with a gamma value of 1 (as set by the serviceRate).**

8. CODE: Modify Queue.java to estimate the arrival rate $\lambda$ and see if it matches the stated arrival rate in the program. Relate this to your earlier estimate of the average interarrival time.

* **The average arrival rate was 1.3473237480392466. This does not match the arrival rate of 0.75, but is extremely close to 1/0.75. This does however match the earlier estimate of the average interarrival time, which does make sense. Effectively, the average time between arrivals should be the same as the average time of arrivals.**

10. CODE: Fix the service rate at $\mu$ = 1 and vary the arrival rate: try $\lambda$ = 0.5, 0.75, 1.25. What do you observe when $\lambda$ = 1.25?

* **As lambda increases, people arrive quicker. This causes longer wait times since the service rate is fixed at 1. When lambda = 1.25, the average wait time becomes 996.55.**

11. What about it?

* **As lambda approaches 0, the number of individuals showing up drastically decreases. This results in an average wait approaching 0, and average system time will match average service time.**

12. CODE: Vary $\lambda$ as follows: 0.1, 0.2, ... , 0.9 and estimate d. What is the shape of the function d($\lambda,\mu$)?

* **![alt](./images/q12.png)**
* **The function is exponential in nature.**

14. For $\lambda$ = 0.75 and $\mu$ = 1, estimate the probability that an arriving customer finds the server free. Try this for $\lambda$ = 0.5, 0.6 as well. Can you relate this probability to $\lambda$ = 0.75 and $\mu$ = 1?

* **$\lambda$ = 0.5 $\implies$ 0.499**
* **$\lambda$ = 0.6 $\implies$ 0.4157**
* **$\lambda$ = 0.75 $\implies$ 0.2649**
* **It seems the probability that an arriving customer finds the server free is 1-$\lambda$ when $\mu$ = 1. See code for how the probability was calculated.**

15. CODE: We will focus on two distributions: the distribution of the number in the system, and the system time. Let rv M denote the number of customers seen by an arriving customer, and let rv D denote the system time experienced by a random customer.
    * Is M discrete or continuous? What about D? What is the range of M? Of D?
    * For $\lambda$ = 0.75 and $\mu$ = 1, obtain the appropriate histogram of M. Which well-known distribution does this look like?
    * For $\lambda$ = 0.75 and $\mu$ = 1, obtain the appropriate histogram of D. Which well-known distribution does this look like?
    * Note: use PropHistogram.java or DensityHistogram.java as appropriate.

* **M is discrete since it has to be a positive integer (or 0). It's range is all positive integers and 0.**
* **D is continuous since it can be any positive value (or 0). It's range is [0,$\infty$).**
* **![alt](./images/q15a.png)**
* **Above is the histogram of M. This looks like an exponential distribution.**
* **![alt](./images/q15b.png)**
* **Above is the histogram of D. This looks also like an exponential distribution.**

16. CODE: Download and execute the above simulation: ThreeQueue.java.
    * What is the average system time?
    * Examine the code to see where the system time is being accumulated.
    * Change the queue choice (between 1 and 2) to "shortest-queue" instead of "uniform-random" and see the difference this makes.
    * In what kind of application is "uniform-random" better than "shortest-queue"?
    * Measure the rate of departures from the system and explain the value you get.

* **The average system time is 3.804660969648594.**
* **The system time is being accumulated in the handleDeparture method.**
* **If the shortest queue is chosen, the average system time becomes 3.0558167780687597.**
* **If you want each line to handle the same number of customers (even if one server is slower than another) than uniform random is better.**
* **The average rate of departures would be the time the last customer leaves divided by the number of departures. This equals 0.9900373077350547.**

19. Examine the code in the molecular simulation from Module 4. Is this synchronous or asynchronous?

* **This code is synchronous.**

20. Find a simulation of the Game-of-Life. Is this synchronous or asynchronous?

* **At each step everything updates at once, so it is synchronous.**

21. CODE: Fill in code in Raindrop.java to obtain histograms of X and T respectively. Use s = 1, h = 10, p = 0.5.
    * What is the likely distribution of X?
    * Vary h: try h = 20, 30, 40, 50. What is the relationship between E[T] and h?
    * Note: use PropHistogram.java or DensityHistogram.java as appropriate

* **The distribution of both X and T are gaussian (x is the first figure).**
* **![alt](./images/q21a.png)**
* **![alt](./images/q21b.png)**
* **E[T] is about twice the height.**

22. Do you know the historical significance of the distributions of X and T?

* **No, is it that most natural phenomena follow normal distributions?**

23. What is the size of the eventlist for the single-server queue? For the three-queue example?

* **The size of the eventlist is the number of arrivals and departures for the single-server queue. It is the same for the three queue example, though it could be rewritten to use 3 different event queues instead of 1.**

24. If the eventlist has n events, how long does it take for each operation (in order-notation)?

* **If a linked list is being used...***
    * **Removing the first element takes O(1)**
    * **Inserting an event takes O(n)**

25. If instead of a sorted-list, we use an unsorted list, how long does each operation take?

* **If a linked list is being used...**
    * **Removing the minimum takes O(n)**
    * **Inserting an event takes O(1)**
