package main;

public class SolutionMethods {
	private int N;  // Number of queens or size of the grid
	private int[][] g_grid; // 2D array representing the grid for the queens
	protected double inits; // Variable to store the number of initializations
	protected double time; // Variable to store the time taken for operations
	protected double tttime; // Variable to store total time taken for operations
	protected int loads; // Variable to store the number of loads
	public double getInits() {
		return inits;
	}


	public void setInits(double inits) {
		this.inits = this.inits+inits;
	}


	public SolutionMethods(int NQ) {
		N=NQ; // Assigns the input size to the class variable N
		g_grid = new int[N][N]; // Initializes the grid with the specified size
		// Initializes all elements of the grid to zero
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	g_grid[i][j] = 0;// Sets each cell in the grid to zero
		    } 
		}
	}
	
	
	public  void hillclimb() {
		long startTime = System.nanoTime();//// Records the start time for performance measurement
		long estimatedTime = System.nanoTime() - startTime; // Initializes estimated time
		Local grid = new Local(N);// // Creates a new Local grid instance
		double n_inits=1; // Initializes the number of initializations
		double tt =0; // Variable to store time taken for the algorithm
		
		// Populates the grid with values from the Local grid
		for(int i=0; i<N; i++) {// Retrieves grid values so grid is available for the second algorithm
		    for(int j=0; j<N; j++) {
		    	g_grid[i][j] = grid.getGridval(i, j);
		    }
		}
		
		System.out.println("\n\n\n\n");
		int h,h2;
		int[][] init_grid=new int[N][N]; // Array to store the initial grid state
		int[][] best_grid=new int[N][N]; // Array to store the best grid state
		
		for (int k=0;k<N;k++) {
			for(int l=0;l<N;l++) {//Store grid
				init_grid[k][l]=grid.getGridval(k, l);
			}
		}
		
		h=grid.totalCollisionsPaired(grid.getGrid()); //// Calculates initial collisions
		// Prints the current collision pairs
    	System.out.println("\nThis is the collision pairs for the board right now");
    	System.out.println("------------------------------------------");
		grid.printCollisionPairs(); // Displays collision pairs
		
		System.out.println("Total collision:"+h); // Displays total collisions
		System.out.println("<---Chessboard Processing:--->");
		System.out.println("<----------------->");
		// Continues until there are no collisions
		while(h!=0) {//We believe the best solution is one that no queen threatens
			h2=grid.chooseQueen();// Chooses a queen and updates the grid (lower h)(call successorfunc)
			//If we end up repeating the same thing for all the queens, there is no solution.
			// Checks if the new collision count is less than the previous
			if(h2<h) {//If h has not decreased as the h series for each queen then we have reached a local minimum
				h=h2; //
				// Stores the best grid state found so far
				for (int k=0;k<N;k++) {
					for(int l=0;l<N;l++) {
						best_grid[k][l]=grid.getGridval(k, l);//Αs h is getting lower,  best grid is updated. 
					}
				}
			
				
			}// Resets the grid to the initial state
			grid.setGridval(init_grid);//The grid returns to the initial state and the algorithm continues from the beginning
			n_inits++;
		}
		//Solution found
		setInits(n_inits);
		
		grid.printGrid(best_grid); //Print best grid

		estimatedTime = System.nanoTime() - startTime;
		
		//Print results
		tt=estimatedTime / Math.pow(10, 9);
		setTime(tt);
		System.out.println("It took " + tt + " seconds to finish.");
		System.out.println("-------------------->Heuristic Best---->:"+h);
		System.out.println("------/(Chessboard is cleared)/-----------");
		System.out.println("------------------------------------------");
		System.out.println("----------------See Ya--------------------\n");
	}
	
	

	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = this.time+time;
	}


	public void constraintSAT() {
		int k=1; // Counter for loads
		Constraint grid = new Constraint(N,g_grid); // Creates a new Constraint grid instance
		grid.printGrid();
		long startTime = System.nanoTime();
		grid.singleColumnTransformation(); // Transforms the grid for processing
		double estimatedTime = System.nanoTime() - startTime;

		System.out.println("It took " + estimatedTime / Math.pow(10, 9) + " seconds to initialize the board.");
		grid.printGrid();
		
		startTime = System.nanoTime();
		// Array to force queen placements
		int[][] forceQueens = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if (i!=0)
					forceQueens[i][j] = 0;
				else
					forceQueens[i][j] = i+1;
			}
		}
		
		// Array to store results of the n-queens problem
		int[] result = new int[2];
		
		while (result[0]>=0) {
			grid.saveState(); // Saves the current state of the grid
			result = grid.nQueensProblem(forceQueens); // Solves the n-queens problem
			
			// Checks the result of the n-queens problem
			if (result[0] == -1)
				System.out.println("Win.");
			if (result[0] == -2)
				System.out.println("Lose.");
		
			if (result[0] >= 0) {
				// Updates the forceQueens array based on the result
				for(int i=0; i<N; i++) {
					forceQueens[i][result[0]] = 0; // Resets the column for the queen
				}
				
				//Move queen.
				forceQueens[result[1]][result[0]] = result[0]+1;
				
				//Disable this line for performance.
				//System.out.println("Move queen "+result[0]+" to row "+result[1]);
			}

			
			grid.loadState(); // Restores the previous state of the grid
			k++;
			
		}
		//Results
		setLoads(k); // Updates the number of loads
		estimatedTime = (System.nanoTime() - startTime)/Math.pow(10, 9);
		setTttime(estimatedTime);
		System.out.println("It took " + estimatedTime   + " seconds to finish.");
	}

    //Get total time
	public double getTttime() {
		return tttime;
	}

	//Set total time
	public void setTttime(double tttime) {
		this.tttime =this.tttime+ tttime;
	}

	//Get loads
	public int getLoads() {
		return loads;
	}

	//Set loads
	public void setLoads(int loads) {
		this.loads =this.loads+ loads;
	}
		
}
