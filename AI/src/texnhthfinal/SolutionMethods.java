package texnhthfinal;

public class SolutionMethods {
	private int N;
	private int[][] ggrid;
	protected double inits;
	protected double time;
	protected double tttime;
	protected int loads;
	public double getInits() {
		return inits;
	}


	public void setInits(double inits) {
		this.inits = this.inits+inits;
	}


	public SolutionMethods(int NQ) {
		N=NQ;
		ggrid = new int[N][N];

		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	ggrid[i][j] = 0;
		    }
		}
	}
	
	
	public  void hillclimb() {
		long startTime = System.nanoTime();//Variables gia timer
		long estimatedTime = System.nanoTime() - startTime;
		Local grid = new Local(N);//Dhmiourgoume grid
		double numofinits=1;
		double tt =0;
		
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {//Apothikeuoume to grid wste na einai diathesimo kai gia ton 2o algorithmo
		    	ggrid[i][j] = grid.getGridval(i, j);
		    }
		}
		
		System.out.println("\n\n\n\n");
		int h,h2;
		int[][] initgrid=new int[N][N];
		int[][] bestgrid=new int[N][N];
		
		for (int k=0;k<N;k++) {
			for(int l=0;l<N;l++) {//Apothikeuetai to grid
				initgrid[k][l]=grid.getGridval(k, l);
			}
		}
		
		h=grid.totalCollisionsPaired(grid.getGrid()); //h tou arxikou grid

    	System.out.println("\nThis is the collision pairs for the board right now");
    	System.out.println("------------------------------------------");
		grid.printCollisionPairs();
		
		System.out.println("Total collision:"+h);
		System.out.println("<---Chessboard Processing:--->");
		System.out.println("<----------------->");
		while(h!=0) {//Theoroume oti h kalyterh lysh einai auth pou kamia vasilisa dn apilei kamia
			h2=grid.chooseQueen();//Call to func p epilegei vasilisa kai dhmiourgei grid me meiwmeno h(call to successorfunc)
			//An katalhgoume se epanalhpseis tou idiou h gia oles tis vasilises dn yparxei kalyterh lysh
			if(h2<h) {//An to h dn exei mikrinei kathos ypologizontai ta h seiriaka gia kathe vasilisa tote ftasame se topiko elaxisto
				h=h2; //
				for (int k=0;k<N;k++) {
					for(int l=0;l<N;l++) {
						bestgrid[k][l]=grid.getGridval(k, l);//Oso mikrenei to h apothikeuetai h kalyterh lysh. 
					}
				}
			
				
			}
			grid.setGridval(initgrid);//To grid epanerxetai sthn arxikh katastash kai o algorithmos synexizei apo thn arxh
			numofinits++;
		}
		//Aliws vrethike lysh
		setInits(numofinits);
		
		grid.printGrid(bestgrid); //Print to grid me thn kalyterh lysh

		estimatedTime = System.nanoTime() - startTime;
		
		
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
		int k=1;
		Constraint grid = new Constraint(N,ggrid);
		grid.printGrid();
		long startTime = System.nanoTime();
		grid.singleColumnTransformation();
		double estimatedTime = System.nanoTime() - startTime;

		System.out.println("It took " + estimatedTime / Math.pow(10, 9) + " seconds to initialize the board.");
		grid.printGrid();
		
		startTime = System.nanoTime();
		
		int[][] forceQueens = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if (i!=0)
					forceQueens[i][j] = 0;
				else
					forceQueens[i][j] = i+1;
			}
		}
		

		int[] result = new int[2];
		
		while (result[0]>=0) {
			grid.saveState();
			result = grid.nQueensProblem(forceQueens);
			
		
			if (result[0] == -1)
				System.out.println("Win.");
			if (result[0] == -2)
				System.out.println("Lose.");
		
			if (result[0] >= 0) {

				for(int i=0; i<N; i++) {
					forceQueens[i][result[0]] = 0;
				}
				
				//Move queen.
				forceQueens[result[1]][result[0]] = result[0]+1;
				
				//Disable this line for performance.
				//System.out.println("Move queen "+result[0]+" to row "+result[1]);
			}

			
			grid.loadState();
			k++;
			
		}
		setLoads(k);
		estimatedTime = (System.nanoTime() - startTime)/Math.pow(10, 9);
		setTttime(estimatedTime);
		System.out.println("It took " + estimatedTime   + " seconds to finish.");
	}


	public double getTttime() {
		return tttime;
	}


	public void setTttime(double tttime) {
		this.tttime =this.tttime+ tttime;
	}


	public int getLoads() {
		return loads;
	}


	public void setLoads(int loads) {
		this.loads =this.loads+ loads;
	}
		
}
