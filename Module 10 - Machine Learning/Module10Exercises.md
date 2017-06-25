---
geometry: top=1in, bottom=1in, left=1in, right=1in
---

### Module 20 Exercises | Joshua Shapiro | 20 June 2017


3. CODE: For the point problem:
    * Create 10 training points from one of the distributions.
    * Compile and upload NullClassifier and click the "train" button.
    * Next, examine the code in NullClassifier. How does the screen output correspond to the generated data?

4. CODE: Consider the char problem:
    * How would the data for a single char be represented as a d-dimensional point?
    * Examine the code in CharProblem.java and identify the manner by which the samples are constructed from line-segments. Where in the code is this being done?
    * Find a place in the code to print out the dimension d for each test sample (which you create on the right side). What is a typical value of d? What kind of character data will have large d?

5. CODE: Similarly, what is the size of d for the face problem? Add code to FaceProblem.java to print out these sizes. Is d the same for all samples?

6. Which of the three applications we've seen has this problem?

7. For the point example (see the picture above) with M = 7:
    * What is $x^{(3)}$?
    * What is $x_1^{(3)}$?
    * What is $y^{(3)}$?

8. CODE: Download (into the classifierJar directory) and modify NearestNeighbor.java to implement the Nearest-Neighbor algorithm.
    * Try it on the point problem for various numbers of generated points, and for different distributions.
    * Can you create a test point that makes it fail?
    * Apply the algorithm tot he char problem. Why doesn't it work?

9. What is the running time of K-Nearest-Neighbor in terms of M, the number of training samples, and K? Does d matter in the running time? What data structure is most appropriate for this computation?

10. Consider a database of $10^6$ greyscale images each of size 0.1 megabytes. For K = 10, what is the number of arithmetic operations (say, multiplications) for a single classification?

11. CODE: Download, compile, and execute SimpleNN.java. You will also need DataGenerator.java.
    * Examine SimpleNN to confirm that is implements the Nearest-Neighbor algorithm for samples that come out of DataGenerator.
    * What is the probability of error?
    * Change the < to <= in the if condition at the end of classify(). What is the probability of error?
    * Explain why there's such a big difference.

12. CODE: Download DataExample.java and execute. You will also need DataGenerator.java and RandTool.java. Write code to estimate the probability that data is generated from class 0. What is the probability that the data comes from class 1?

13. CODE: Add code to estimate Pr[X = 3|C = 0] and Pr[X = 3|C = 1].
    * Why is it that the sum of these two does not equal 1? Which equation does result in 1?
    * If you were given a test sample x = 3 which of the two classes would you think it likely came from?

14. CODE: Write down the conditional pmf Pr[X = m|C = 1] by examining the code in DataGenerator.

15. CODE: Implement the algorithm in DataExample2.java, which has the code for evaluation. Try a different algorithm to see if the error improves.

16. CODE: Copy the algorithm over to DataExample3.java, which estimates the error when x = 3.
    * What is the error?
    * Next, change Pr[C = 0] (which is now 0.4) to 0.9. What is the error now?
    * How would you change the algorithm to improve the error?

17. For the case x = 3, compute Pr[X = x|C = 0], Pr[C = 0], Pr[X = x|C = 1], and Pr[C = 1] by hand when Pr[C = 0] = 0.4. Then, compute both when Pr[C = 0] = 0.9.

19. Why isn't E differentiable with respect to $w_i$?

20. Why?

23. CODE: Download and add code to Sigmoid.java to compute and display the sigmoid function in the range [-10,10]. You will also need Function.java and SimplePlotPanel.java. How could you make it approximate a step function even more closely?

25. Determine weights for each of the three units above so that the result achieves the XOR function. Then, build a truth table and show how the combination results in computing XOR.




















2. Why is this true? What is the minimum (unconstrained) value of f(x,y) = 3x + 4y?

* **For any linear, unconstrained problem, the minimum will always be -$\infty$.**

3. Go back to your calc book and find an example of a "hard to differentiate" function.

* **Answer: $\frac{4x^2 + log(2^{tan(x^{sin(x)})})}{5x + cos(2^x)}$**

4. What's an example of a function that's continuous but not differentiable? Consider the weird function f(x) where f(x) = 1 if x is rational and f(x) = 0 otherwise. Is this continuous? Differentiable?

* **The absolute value function is not differentiable.**
* **The weird function is not continuous and therefore not differentiable.**

5. Consider the function $f(x) = \frac{x}{\mu_1 - \lambda x} + \frac{1 - x}{\mu_2 - \lambda (1 - x)}$
    * Compute the derivative f'(x). Can you solve f'(x) = 0?

* **Derivative below:**
    * $f(x) = \frac{x}{\mu_1 - \lambda x} + \frac{1 - x}{\mu_2 - \lambda (1 - x)}$
    * $\implies f'(x) = \frac{\mu_1 - \lambda * x + \lambda * x}{mu_1 - \lambda * x)^2} + \frac{-\mu_2 + \lambda - \lambda * x - \lambda - \lambda * x}{(\mu_2 - \lambda + \lambda * x)^2}$
    * $\implies f'(x) = \frac{(\mu_1)(\mu_2 - \lambda + \lambda * x)^2 - (\mu_2)(\mu_1 - \lambda * x)^2}{(\mu_1 - \lambda * x)^2(\mu_2 - \lambda + \lambda * x)^2}$
* **Solving for f'(x) = 0:**
    * $\frac{(\mu_1)(\mu_2 - \lambda + \lambda * x)^2 - (\mu_2)(\mu_1 - \lambda * x)^2}{(\mu_1 - \lambda * x)^2(\mu_2 - \lambda + \lambda * x)^2} = 0$
    * $\implies (\mu_1)(\mu_2 + \lambda (x - 1))^2 - (\mu_2)(\mu_1 - \lambda * x)^2 = 0$
    * $\implies \mu_1\lambda^2 - \mu_1^2\mu_2 - 2\mu_1\lambda\mu_2 + \mu_1\mu_2^2 + x(4\mu_1\lambda\mu_2 - 2\mu_1\lmabda^2) + x^2(\mu_1\lambda^2 - \lambda^2\mu_2) = 0$
    * ...skipping a few steps...
    * $\implies x = \pm \sqrt{\frac{\mu_1\mu_2(\mu_1 - \lambda + \mu_2)^2}{\lambda^2(\mu_1 - \mu_2)^2}} - \frac{4\mu_1\lambda\mu_2 - 2\mu_1\lambda^2}{2(\mu_1\lambda^2 - \lambda^2\mu_2)}

6. CODE: Download and execute BracketSearch.java.
    * What is the running time in terms of M and N?
    * If we keep MN constant (e.g., MN = 24), what values of M and N produce best results?

* **The run time is O(MN)**
* **For the function in BracketSearch, M = 12 and N = 2 produced best results, with a minimum found at 2.5001 (actual minimum at 2.5).**

7. Draw an example of a function for which bracket-search fails miserably, that is, the true minimum is much lower than what's found by bracket search even for large M and N.

* **Any function with many peaks and valleys will cause this to fail.**
* **NEED TO DRAW SOMETHING HERE**

8. What is the number of function evaluations in terms of M and N for the bracket-search algorithm?

* **There are (M+1)N + 1 function evaluations.**

9. What is the number of function evaluations in terms of M and N for the 2D bracket-search algorithm? How does this generalize to n dimensions?

* **There are $((M+1)^2+1)N$ function evaluations for the 2D bracket-search algorithm.**
* **There are $((M+1)^n+1)N$ function evaluations for the n-dimensional bracket-search algorithm.**

10. CODE: Add code to MultiBracketSearch.java to find the minimum of:
    $f(x_1,x_2) = (x_1 - 4.71)^2 + (x_2 - 3.2)^2 + 2(x_1 - 4.71)^2 * (x_2 - 3.2)^2$

* **With M = 6 and N = 4, x1 = 4.691358024691359,  x2 = 3.2098765432098757, numFuncEvals=120**

11. CODE: Modify BracketSearch2.java to use the proportional-difference stopping condition.

* **With M = 10, N = 4, bestx = 4.72, bestf = 2.5001, prevBestf = 2.5080999999999998**

16. CODE: Download and execute GradientDemo.java
    * How many iterations does it take to get close to the optimum?
    * What is the effect of using a small $\alpha$ (e.g., $\alpha$ = 0.001)?
    * In the method nextStep(), print out the current value of x, and the value of xf'(x) before the update.
    * Set $\alpha$ = 1 Explain what you observe.
    * What happens when $\alpha$ = 10?

* **It takes around 150 iterations to get close to the optimum.**
* **A smaller alpha requires more iterations to get to the optimum.**
* **Alpha is too large when set to 1 and jumps over the minimum, never able to find it. Its jumps are always consistent, so it alternates between the same values.**
* **When alpha is set to 10, it also is never able to find the minimum. However, the jumps are not consistent and always moves farther away from the minimum.**

17. CODE: Download GradientDemo2.java and examine the function being optimized.
    * Fill in the code for computing the derivative.
    * Try an initial value of x at 1.8. Does it converge?
    * Next, try an initial value of x at 5.8. What is the gradient at the point of convergence?

* **It converges at the global minimum with a value of x = 1.8.**
* **When x = 5.8 it only finds a local minimum**
