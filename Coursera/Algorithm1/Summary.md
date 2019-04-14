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
