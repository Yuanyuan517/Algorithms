import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONSTANT = 1.96;
    private final int n;
	private final int trials;
	private double[] x;
	private double mean;	
	private double stddev;
	
	
	public PercolationStats(int n, int trials) {   
	   // perform trials independent experiments on an n-by-n grid
	   checkBound(n, trials);
	   this.n = n;
	   this.trials = trials;
	   this.x = new double[this.trials];
	   run();
	}
	
	private void run() {
		
		int size = n*n;
		
		for (int i = 0; i < trials; i++) {
			
			int xN = runPercolationTest();
			x[i] = (double) xN/size;
		}
		// this.mean = mean();
		// this.stddev = stddev();
	}
	
	private int runPercolationTest() {
		int xN = 0;
		
		Percolation per = new Percolation(this.n);
		while (!per.percolates()) {
			// randomly choose one site
			// System.out.println("It doesn't percolate");
			int row;
			int col;
			do {
			row = StdRandom.uniform(n) + 1;
			col = StdRandom.uniform(n) + 1;
			} while (per.isOpen(row, col));
			// System.out.println("Opened "+row+" "+col);
			per.open(row, col);
			xN++;
		}
		// System.out.println("xN is "+xN);
		return xN;
	}
	
	public double mean()  {                        
	   // sample mean of percolation threshold
		this.mean = StdStats.mean(x);
		return mean;
	}
   public double stddev()  {                      
	   // sample standard deviation of percolation threshold
	   this.stddev = StdStats.stddev(x);
	   return stddev;
   }
   public double confidenceLo()  {    
	   
	   // low  endpoint of 95% confidence interval
	   if (this.mean == 0)
		   this.mean = mean();
	   if (this.stddev == 0)
		   this.stddev = stddev();
	  return this.mean - CONSTANT*this.stddev/(Math.sqrt(this.trials));
   }
   public double confidenceHi()  {                
	   // high endpoint of 95% confidence interval
	   if (this.mean == 0)
		   this.mean = mean();
	   if (this.stddev == 0)
		   this.stddev = stddev();
	   return this.mean + CONSTANT*this.stddev/(Math.sqrt(this.trials));
   }
   private void checkBound(int n1, int trials1) {
	   if (n1 <= 0 || trials1 <= 0)
		   throw new java.lang.IllegalArgumentException();
   }
   public static void main(String[] args)    {    
	   // test client (described below)
	   int n = 4;
	   int trials = 100000;
	   PercolationStats perStats = new PercolationStats(n, trials);
	  
	   // double mean = perStats.mean(); 
	   // double stddev = perStats.stddev();
	   double confidenceLo = perStats.confidenceLo();
	   double confidenceHi = perStats.confidenceHi(); 
	   System.out.println("mean =" +perStats.mean);
	   System.out.println("stddev =" +perStats.stddev);
	   System.out.println("confidenceLo =" +confidenceLo);
	   System.out.println("confidenceHi =" +confidenceHi);
   }
}