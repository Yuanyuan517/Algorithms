This week, the lecturer introduced why and how to analyze algorithms.
First why we analyze algorithms:
1. predict performance
2. compare algorithms
3. provide guarantees
4. understand theoretical basis . 

And the primary practical reason is to avoid performance bugs.
To be able to solve a large practical input, our program should be not slow and running out of memory.
Then we learn a framework for predicting performance and comparing algorithms:
1. Observe some feature of the natural world
2. Hypothesize a model that is consistent with the observations
3. Predict events using the hypothesis
4. Verify the predictions by making further observations
5. Validate by repeating until the hypothesis and observations agree.

With the example of 3-Sum, we analyze the running time under different input size.
First we draw the standard plot:
[standard plot](https://i.imgur.com/7Jp2Z72.png)
we can see it grows exponentially.
Then we draw the log-log plot (very often the straight line), we see it is a straight line with slope 3, clearly suggesting the hypothesis that the running time satisfies a power law of the form cn^3.
[log log plot](https://i.imgur.com/9yrfAmq.png)
Then we can get the running time along with the input size.

**Doubling hypothesis** is a quick way  to estimate the slope in a power-law relationship.
Doubling means to double the size of the input. In the following link (from the [site](https://introcs.cs.princeton.edu/java/41analysis/ "site")), we can see the elapsed time increases by about a factor of 8(ratio) when the input size doubles. 
lg ratio = b (slop) = 3
[link](https://i.imgur.com/nPlc1PL.png)
After getting b, to estimate a, we just need to use one running time and the corresponding N: 
51.1 (time) = a*8000(N)^3.

The constant a is determined by 2 factors:
(1) System independent effects: algorithm and input data (determines exponent b)
(2) System dependent effects: hardware (CPU, memory, cache, ...), software (compiler, interpreter, garbage collector, ...) and system (operating system, network, other apps, ...)
So algorithm is quite important in deciding the application performance for programmers

Next it talks about the order-of-growth:
Check the graph: [functions](https://i.imgur.com/nV8EI1F.png)
The order of growth for binary search is logarithmic (divide in half), for mergesort, it is linearithmic (divide and conquer).
