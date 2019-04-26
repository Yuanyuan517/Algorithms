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

We have 3 commonly used notations in the algorithm theory:
1. Big Theta, to classify algorithms
2. Big Oh, to develop upper bounds
3. Big Omega, to develop lower bounds.
4. Tilde, ~ 10 N^2, to provide approximate model.
Common mistake is interpreting big-Oh as an approximate model. 
The course will focus on approximate models: use Tilde-notation.
### Interview Questions
1. **3-SUM in quadratic time** Design an algorithm for the 3-SUM problem that takes time proportional to n^2 in the worst case. You may assume that you can sort the nn integers in time proportional to n^2 or better.
> Hint: given an integer 𝚡 and a sorted array 𝚊[] of nn distinct integers, design a linear-time algorithm to determine if there exists two distinct indices 𝚒 and 𝚓 such that 𝚊[𝚒]+𝚊[𝚓]==𝚡.
```java
ArrayList<Integer> data = new ArrayList<>(Integer);
...
Collections.sort(data);

for (int i = 0; i < data.size()-2; i++) {
	int j = i + 1;
	int k = data.size() - 1;
	while (j < k) {
		int sum = data.get(i) + data.get(j) + data.get(k);
		if (sum == 0) {
			System.out.println("Found!");
		} else {
			if (sum >= 0)
				k--;
			else
				j++;
		}
	}
}
```

2. **Search in a bitonic array** An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of nn distinct integer values, determines whether a given integer is in the array.

Standard version: Use ∼3lgn compares in the worst case.
Signing bonus: Use ∼2lgn compares in the worst case (and prove that no algorithm can guarantee to perform fewer than ∼2lgn compares in the worst case).
> Hints: Standard version. First, find the maximum integer using ∼1lgn compares—this divides the array into the increasing and decreasing pieces.
Signing bonus. Do it without finding the maximum integer.
[3 lg n](https://www.geeksforgeeks.org/find-element-bitonic-array/ "3 lg n")
```java
if (key > arr[index]) { 
            return -1; 
        } else if (key == arr[index]) { 
            return index; 
        } else { 
            int temp = ascendingBinarySearch(arr, 0, index - 1, key); 
            if (temp != -1) { 
                return temp; 
            } 
  
            // Search in right of k  
            return descendingBinarySearch(arr, index + 1, n - 1, key); 
        } 
```

For sigining bonus, check [here](https://stackoverflow.com/questions/19372930/given-a-bitonic-array-and-element-x-in-the-array-find-the-index-of-x-in-2logn)
