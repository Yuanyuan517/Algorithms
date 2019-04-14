### Key Points Analysis

- It is not to implement Union-Find data structure, but it is to strengthen the understanding of the data structure and to know how to use it to solve problems.
- The percolation problem has several terms:
- 	- Percolation: the system that is able to go through from top to bottom
- 	- Full Site: an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. So if a system percolates, there is a full site in the bottom.
- 	- Backwash: when use a Union-Find data structure, to test the percolation, we introduce a virtual top and a virtual bottom sites, if the virtual top and bottom sites connect, we can prove the system percolates. However, there maybe a problem for the validation of full site, the common error is: 
when we open the site, if the site A is the bottom site, we will connect it to the virtual bottom site, when the system percolates, i.e. 2 virtual sites connect, because of the transitivity, the site A also connect to the virtual top site. Then to  decide if it is full site, we check if the site is connected to the virtual top site. The error case is like the following graph:
[![Backwash example](https://drkbl.com/images/percolation-backwash.png "Backwash example")](https://drkbl.com/images/percolation-backwash.png "Backwash example")    
The error code implementation: 

```java

public class Percolation {
    private final int size;
	private final int n;
	private int count;
	private boolean[] open;
	private final WeightedQuickUnionUF uf;
	
	public Percolation(int n)                // create n-by-n grid, with all sites blocked
	{
		if (n <= 0)
			throw new java.lang.IllegalArgumentException();
		this.size = n*n;
		this.n = n;
		open = new boolean[n*n];
		uf = new WeightedQuickUnionUF(n*n+2);
	}
	
    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
    		if (isOpen(row, col))
    			return;
    		int num = convertTo1D(row, col);
    		open[num] = true;
    		count++;
    		
    		// connect the elem to the four neighbors who are 1, too
    		connectToOpenNeighbors(row, col);
    }
    
    private void connectToOpenNeighbors(int row, int col) {
    	
    	int num = convertTo1D(row, col);
    	if (row > 1)
			attemptToConnect(num, row-1, col);
    	else {
    		uf.union(num, size); // means it is a full site
    	}
		if (col > 1)
			attemptToConnect(num, row, col-1);
		if (row < n)
			attemptToConnect(num, row+1, col);
		else
			uf.union(num, size+1); // means it perlates
		if (col < n)
			attemptToConnect(num, row, col+1); 
		
    }
    
    private void attemptToConnect(int num, int row2, int col2) {
    	
    		int num2 = convertTo1D(row2, col2);
    		if (open[num2]) {
    			uf.union(num, num2);
    		}
    }
    
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
	   checkBound(row, col);
	   int num = convertTo1D(row, col);
	   return uf.connected(num, size);
   }
}
```
### Gains  
I really appreciate the high quality of the course including its lectures, ppt and assignments. Every time I can gain a lot after finishing it.  
Through the submission, I realized the errors I have made. In the following, I listed the errors and my solution:  
- Code style: for the variables that will be assigned just once, declare them with final, for constant used more than once, declare with static final (strictly, static should appear before final);
- Check the legality of the arguments in the methods;
- Coding Inspiration:
- 	- The original Union-Find data structure(UF) just copes with one dimensional data, to avoid modifying the UF, the other way is to convert the 2 dimensional data input into 1 dimension (accoding to the arrangements of each point in the row and column).
- 	- The time and space complexity are always essential in programming. To save time, we should have new ideas instead of using for loop to check the connection between each point in the bottom and each point in the top, how to check just one poin in the top and one point in the bottom? Then we get ideas by adding one virtual top and one virtual bottom.
- - as mentioned before about the backwash problem, how to solve it? 
- - - one way is to use two UFs, which is also what I used. One is with a virtual top and a virtual bottom, the other is with only a virtual top to check the connectivity with the top (isFull?).
- - - the other is to use a flag, the exact implementation is in the [link](https://www.cnblogs.com/anne-vista/p/4841732.html "link").
- 	- Cache the data which can avoid same calculation more than once. So I increased the constructor parameters mean, stddev, and in the methods that use the two params, I check whether the two values are not 0, if they are 0, calculate. Like the following:  
```java
 public double confidenceHi()  {                
	   // high endpoint of 95% confidence interval
	   if (this.mean == 0)
		   this.mean = mean();
	   if (this.stddev == 0)
		   this.stddev = stddev();
	   return this.mean + CONSTANT*this.stddev/(Math.sqrt(this.trials));
   }
```
### Summary
To write more qualified code, the key is to practice and learn from others code, which is what I really need! Keep working!
