### Probability Exercises | Joshua Shapiro | 30 March 2017

1. CODE: Download Coin.java, CoinExample.java, and RandTools.java. Then compile and execute.

* **DONE**

2. CODE: Download StrangeCoin.class. This class models a biased coin in which Pr[heads] is not 0.5. Write a program to estimate Pr[heads] for this coin.

* **The probability is about 0.6**

3. CODE: Modify the above program, writing your code in CoinExample3.java, to estimate the probability of obtaining exactly 2 heads in 3 flips. Can you reason about the theoretically correct answer?

* **The probability is about 0.432**

4. CODE: This exercise has to do with obtaining tails in the first two flips using the biased (Pr[heads]=0.6):
    * What is the probability that, in 3 flips, the third and only the third flip results in heads?
    * **The probability is about 0.096**
    * What is the probability that, with an unlimited number of flips, you need at least three flips to see heads for the first time?
    * **The probability is about 0.16**
    * Modify the above example, writing your code in CoinExample4.java and CoinExample5.java.
    * **DONE**

5. CODE: Roll a pair of dice (or roll one die twice). What is the probability that the first outcome is odd AND the second outcome is even? Write code in DieRollExample2.java to simulate this event. You will need Die.java. See if you can reason about this theoretically.

* **The probability is about 0.25**

6. CODE: Estimate the following probabilities when two cards are drawn without replacement.
    * The probability that the first card is a club given that the second is a club.
    * **The probability is about 0.235**
    * The probability that the first card is a diamond given that the second is a club.
    * **The probability is about 0.255**
    * That is, we are interested in those outcomes when the second card is a club. Write your code for the two problems in CardExample2.java and CardExample3.java. You will need CardDeck.java.
    * **DONE**

7. List all possible events of the sample space $\Omega = {heads, tails}$.

* **Answer: $\emptyset$, heads, tails, {heads, tails}**

8. How many possible events are there for a sample space of size |$\Omega$| = n?

* **There are $2^n$ possible events for a sample space of size n.**

9. How many such numbers (probabilities) need to be specified for a die roll? How many are needed for a roll of two dice?

* **Assuming we specify the probability for every event, for a single die roll we would need to specify $2^6 = 64$ probabilities. For a roll of two dice that number would be $2^{36}$ probabilities.**

10. Use the axioms to show that Pr[A'] = 1-Pr[A] for any event A.

* **Since Pr[A'] is defined as Pr[$\Omega$] - Pr[A], we can swap out Pr[$\Omega$] with 1 and get the equation above. Alternatively, since A' is all events other than A, we could just take the union of all disjoint events to get the probability of A'.**

11. Suppose we draw two cards without replacement:
    * What is a compact way of representing the sample space for two card drawings without replacement? What is the size of the sample space?

        **(0,1),(0,2),...,(0,51)**

        **(1,0),(1,2),...,(1,51)**

        **................................**

        **................................**

        **.....................,(51,50)**
    * **A compact way of representing the sample space $\Omega$ would be the following: $\Omega = \{(x,y) | 0 \leq x \leq 51, 0 \leq y \leq 51, x \neq y\}$**
    * **The size of the sample space is $51*52 = 2652$**
    * What subset corresponds to the event "both are spades"?
    * **Subset spades = $\{(x,y) | 0 \leq x \leq 12, 0 \leq y \leq 12, x \neq y \}$ where spades are represented as the cards numbered 0-12**
    * What is the cardinality of this subset?
    * **|spades| = 12 * 13 = 156**
    * What, therefore, is the probability that "both are spades"?
    * **Answer: $\frac{12*13}{51*52}$**
    * What is the probability of "first card is a spade"? Work this out by describing the subset and its cardinality.
    * **The subset would be $\{(x,y) | 0 \leq x \leq 12, x \neq y \}$. This has a cardinality of 13 * 51. The probability is then $\frac{13*51}{51*52} = \frac{13}{52}$**

12. Consider this experiment: flip a coin repeatedly until you get heads. What is the sample space?

* **The sample space is infinite. It can be described as $\{T^nH|n \geq 0\}$.**

13. Consider an experiment with three coin flips. What is the probability that 3 heads occurred given that at least one occurred?

* **Answer:**
    * $Pr[A|B] = \frac{Pr[A \cap B]}{Pr[B]}$
    * $Pr[B] = 7/8$
    * $Pr[A \cap B] = 1/8$
    * $Pr[A|B] = \frac{\frac{1}{8}}{\frac{7}{8}} = \frac{1}{7}$

14. Consider two card drawings without replacement. What is the probability that the second card is a club given that the first is a club?

* **Answer:**
    * $Pr[A|B] = \frac{Pr[A \cap B]}{Pr[B]}$
    * $Pr[B] = 13*51/52*51$
    * $Pr[A \cap B] = 13*12/52*51$
    * $Pr[A|B] = \frac{\frac{13*12}{52*51}}{\frac{13*51}{52*51}} = \frac{12}{51}$

15. Why? Explain by describing the events $C_1$ and $C_2$ as subsets of the sample space and evaluating their cardinalities (sizes).

* **Answer: We know the size of $C_1$ is 13 * 51 and the size of $C_2$ is also 13 * 51. If we look at the intersection of $C_1$ and $C-2$, the size is 13 * 12. Taking the sizes of these events and dividing by the size of the sample space (52 * 51) yields the probability for each event (since each card has an equal chance of getting chosen). Using these probabilities you can plug into the equations to return the answers produced in the module.**

16. Compute Pr[$D_1|C_2$].

* **Answer:**
    * $Pr[D_1|C_2] = \frac{Pr[C_2|D_1] * Pr[D_1]}{Pr[C_2]}$
    * $Pr[D_1] = \frac{13*51}{52*51}$
    * $Pr[C_2] = \frac{13*51}{52*51}$
    * $PR[C_2|D_1] = \frac{Pr[C_2 \cap D_1]}{Pr[D_1]} = \frac{\frac{13*13}{52*51}}{13/52} = \frac{13}{51}$
    * $Pr[D_1|C_2] = \frac{\frac{13}{51} * \frac{13}{52}}{\frac{13}{52}} = \frac{13}{51}$

17. Finish (by hand) the calculation above.

* **Answer:**
    * $Pr[A] = Pr[A|B]Pr[B] + Pr[A|B']Pr[B']$
    * $Pr[A] = Pr[A \cap B] + Pr[A \cap B']$
    * $Pr[A \cap B] = \frac{4*3}{52*51}$
    * $Pr[A \cap B'] = \frac{48*4}{52*51}$
    * $Pr[A] = \frac{4*3}{52*51} + \frac{48*4}{52*51} = \frac{4*51}{52*51} = \frac{4}{52}$

18. CODE: Write down the desired probabilities in terms of what's given in the model, and complete the calculations. Write a program to confirm your calculations:
    * Download LabTest.java and LabTestExample.java.
    * Write your code in LabTestExample

    * **Probability that if a test is positive, the person is infected (Pr[sick|positive]):**
        * $Pr[sick] = 0.05, Pr[positive|sick] = 0.99, Pr[positive|well] = 0.03, Pr[well] = 0.95$
        * $Pr[sick|positive] = \frac{Pr[positive|sick] * Pr[sick]}{Pr[positive]}$
        * $Pr[positive] = Pr[positive|sick]Pr[sick] + Pr[positive|well]Pr[well] = 0.99*0.05 + 0.03*0.95 = 0.078$
        * $Pr[sick|positive] = \frac{0.99 * 0.05}{0.078} = 0.635$

    * **Probability that if a test is positive, the person is well (Pr[well|positive]): (using probabilities calculated above)**
        * $Pr[well|positive] = \frac{Pr[positive|well] * Pr[well]}{Pr[positive]} = \frac{0.03*0.95}{0.078} = 0.365$
