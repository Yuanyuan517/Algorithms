import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int size;
	private final int n;
	private int count;
	private boolean[] open;
	private final WeightedQuickUnionUF uf;
	private final WeightedQuickUnionUF uf2; // no connection with virtual bottom
	
	public Percolation(int n)                // create n-by-n grid, with all sites blocked
	{
		if (n <= 0)
			throw new java.lang.IllegalArgumentException();
		this.size = n*n;
		this.n = n;
		open = new boolean[n*n];
		uf = new WeightedQuickUnionUF(n*n+2);
		uf2 = new WeightedQuickUnionUF(n*n+1);
	}
	
	public boolean isOpen(int row, int col)  // is site (row, col) open?
	{
		   checkBound(row, col);
		   int num = convertTo1D(row, col);
		   return open[num];
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
    		uf2.union(num, size);
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
    			uf2.union(num, num2);
    		}
    }
    
    private int convertTo1D(int row, int col) {
    		
    		checkBound(row, col);
    		int num = ((row-1)*n + col) -1; // -1 to start from the 1st site
    		return num; 
    }
    
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
	   checkBound(row, col);
	   int num = convertTo1D(row, col);
	   return uf2.connected(num, size);
   }
   
   public int numberOfOpenSites()       // number of open sites
   {
	   return count;
   }
   
   public boolean percolates()              // does the system percolate?
   {
	   return uf.connected(size, size+1); // clever approach!
   }

   // By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left site
   private void checkBound(int row, int col) {
	   if (row <= 0 || col <= 0 || row > n || col > n)
		   throw new java.lang.IllegalArgumentException(row+" "+col);
   }
   
   public static void main(String[] args)    {    
	   // test client (described below)
	   int n = 200;
	   Percolation per = new Percolation(n);
	   per.open(1, 1);
	   System.out.println("1, 1 =" +per.isFull(1, 1));
   }
}
