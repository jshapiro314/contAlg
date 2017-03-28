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
    * **Te probability is about 0.16**
    * Modify the above example, writing your code in CoinExample4.java and CoinExample5.java.
    * **DONE**

5. CODE: Roll a pair of dice (or roll one die twice). What is the probability that the first outcome is odd AND the second outcome is even? Write code in DieRollExample2.java to simulate this event. You will need Die.java. See if you can reason about this theoretically.

* **The probability is about 0.25**

6. CODE: Estimate the following probabilities when two cards are drawn without replacement.
    * The probability that the first card is a club given that the second is a club.
    * The probability that the first card is a diamond given that the second is a club.
    * That is, we are interested in those outcomes when the second card is a club. Write your code for the two problems in CardExample2.java and CardExample3.java. You will need CardDeck.java.

7. List all possible events of the sample space $\Omega = {heads, tails}$.

8. How many possible events are there for a sample space of size |$\Omega$| = n?

9. How many such numbers (probabilities) need to specified for a die roll? How many are needed for a roll of two dice?

10. Use the axioms to show that Pr[A'] = 1-Pr[A] for any event A.

11. Suppose we draw two cards without replacement:
    * What is a compact way of representing the sample space for two card drawings without replacement? What is the size of the sample space?
    * What subset corresponds to the event "both are spades"?
    * What is the cardinality of this subset?
    * What, therefore, is the probability that "both are spades"?
    * What is the probability of "first card is a spade"? Work this out by describing the subset and its cardinality.

12. Consider this experiment: flip a coin repeatedly until you get heads. What is the sample space?

13. Consider an experiment with three coin flips. What is the probability that 3 heads occurred given that at least one occurred?

14. Consider two card drawings without replacement. What is the probability that the second card is a club given that the first is a club?

15. Why? Explain by describing the events $C_1$ and $C_2$ as subsets of the sample space and evaluating their cardinalities (sizes).

16. Compute Pr[$D_1|C_2$].

17. Finish (by hand) the calculation above.

18. CODE: Write down the desired probabilities in terms of what's given in the model, and complete the calculations. Write a program to confirm your calculations:
    * Download LabTest.java and LabTestExample.java.
    * Write your code in LabTestExample
