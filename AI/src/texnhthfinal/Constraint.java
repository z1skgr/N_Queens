package texnhthfinal;

import java.util.Random;

public class Constraint {

	private int[][] grid;
	private int[][] initialGrid;
	private int[][] collisionPairs;
	private int N;
	public static final boolean DEBUG_MODE = false;
	
	public Constraint(int N,int [][] ggrid) {
		this.grid = new int[N][N];
		this.initialGrid = new int[N][N];
		this.collisionPairs = new int[N][N];
		this.N = N;

		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	grid[i][j] = 0;
		    	initialGrid[i][j] = 0;
		    }
		}
		
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	collisionPairs[i][j] = 0;
		    }
		}
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	grid[i][j] = ggrid[i][j];
		    }
		}
	}
	
	public int get(int x, int y) {
		return grid[x][y];
	}

	public void printGrid() {

    	System.out.println("\n\n\nThis is the chess board right now");
    	System.out.println("----------------------------------------\n");
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	char x = (grid[i][j]>0) ? 'Q' : '-';
		    	System.out.print("| "+x+" ");
		    }
	    	System.out.println("|");
		}
		
	}
	
	public void printCollisionPairs() {

    	System.out.println("\n\n\n");
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	System.out.print("| "+collisionPairs[i][j]+" ");
		    }
	    	System.out.println("|");
		}
		
	}
	
	
	
	public int getCollisions(int x, int y) {
		int collisions = 0;

		for (int i=0; i<N; i++) {
			if (isQueen(x,i) && i!=y) {
				collisions++;
			}
		}
		
		for (int i=0; i<N; i++) {
			if (isQueen(i,y) && i!=x) {
				collisions++;
			}
		}
		
		int i,j;

		//Diagwnia - Southeast
		i=x+1;
		j=y+1;
		while (i<N && j<N) {
			if (isQueen(i,j))
				collisions++;
			i++;
			j++;
		}

		//Diagwnia - Northeast
		i=x-1;
		j=y+1;
		while (i>=0 && j<N) {
			if (isQueen(i,j))
				collisions++;
			i--;
			j++;
		}
		
		//Diagwnia - Northwest
		i=x-1;
		j=y-1;
		while (i>=0 && j>=0) {
			if (isQueen(i,j))
				collisions++;
			i--;
			j--;
		}
		
		//Diagwnia - Southwest
		i=x+1;
		j=y-1;
		while (i<N && j>=0) {
			if (isQueen(i,j))
				collisions++;
			i++;
			j--;
		}
		
		return collisions;
	}
	
	public boolean isQueen (int x, int y) {
		return grid[x][y] > 0 ;
	}

	public int totalCollisions() {

		int collisions = 0;

		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	if (isQueen(i,j)) {
		    		collisions += getCollisions(i,j);
		    	}
		    }
		}
		
		return collisions;
	}
	

	public void findCollisionPairs(int x, int y) {
		
		int queen = grid[x][y];
		int col = 1;

		for (int i=0; i<N; i++) {
			if (isQueen(x,i) && i!=y) {
				collisionPairs[queen-1][col] = grid[x][i];
				col++;
			}
		}
		
		for (int i=0; i<N; i++) {
			if (isQueen(i,y) && i!=x) {
				collisionPairs[queen-1][col] = grid[i][y];
				col++;
			}
		}
		
		int i,j;

		//Diagwnia - Southeast
		i=x+1;
		j=y+1;
		while (i<N && j<N) {
			if (isQueen(i,j)) {
				collisionPairs[queen-1][col] = grid[i][j];
				col++;
			}
			i++;
			j++;
		}

		//Diagwnia - Northeast
		i=x-1;
		j=y+1;
		while (i>=0 && j<N) {
			if (isQueen(i,j)) {
				collisionPairs[queen-1][col] = grid[i][j];
				col++;
			}
			i--;
			j++;
		}
		
		//Diagwnia - Northwest
		i=x-1;
		j=y-1;
		while (i>=0 && j>=0) {
			if (isQueen(i,j)) {
				collisionPairs[queen-1][col] = grid[i][j];
				col++;
			}
			i--;
			j--;
		}
		
		//Diagwnia - Southwest
		i=x+1;
		j=y-1;
		while (i<N && j>=0) {
			if (isQueen(i,j)) {
				collisionPairs[queen-1][col] = grid[i][j];
				col++;
			}
			i++;
			j--;
		}
		
	}
	
	
	public int totalCollisionsPaired() {


		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	if (isQueen(i,j)) {
		    		collisionPairs[  grid[i][j] - 1   ][0] = grid[i][j];

		    		findCollisionPairs(i, j);
		    	}
		    }
		}
		
		//printCollisionPairs();
		
		for(int i=0; i<N; i++) {
		    for(int j=1; j<N; j++) {
		    	if (collisionPairs[i][j] > 0) {
		    		
		    		int row = collisionPairs[i][j] - 1;
		    		for (int k = 1; k < N; k++ ) {
		    			if (collisionPairs[row][k] == collisionPairs[i][0]) {
		    				collisionPairs[row][k] = 0;
		    			}
		    		}
		    		
		    		
		    	}
		    }
		}
		
		//printCollisionPairs();

		
		int h = 0;
		
		for(int i=0; i<N; i++) {
		    for(int j=1; j<N; j++) {
		    	if (collisionPairs[i][j] > 0)
		    		h++;
		    }
		}
		
		
		
		return h;
	}
	
	public boolean allQueensSingle() {
	    for(int j=0; j<N; j++) {
	    	if (!isQueen(0,j))
	    		return false;
	    }
	    return true;
	}
	
	public void singleColumnTransformation() {
		int moves = 0;
		
		while (!allQueensSingle()) {
			
			int[] queensVisited = new int[N];
			for (int i=0; i<N; i++)
				queensVisited[i] = 0;
			
			
			int column = 0;
			for(int i=0; i<N; i++) {
			    for(int j=0; j<N; j++) {
			    	if (isQueen(i,j) && queensVisited[grid[i][j]-1]==0) {
			    		
			    		//visited.
			    		queensVisited[grid[i][j]-1] = 1;
			    		
			    		//transfer from <j> to <column>
			    		
			    		if (j==column) {
			    			if (i==0) {
				    			//Do nothing.
			    			}
			    			else {
				    			if (i!=0) {
					    			grid[0][column] = grid[i][column];
					    			grid[i][column] = 0;
					    			moves++;
				    			}
			    			}
			    			column++;
			    		}
			    		
			    		else if (j>column) {
			    			
				    		//check if the queen can move horizontally
				    		boolean can = true;
				    		for (int k=j-1; k>=column; k--) {
				    			if (isQueen(i,k))
				    				can = false;
				    		}
				    		
				    		if (can) {
				    			grid[i][column] = grid[i][j];
				    			grid[i][j] = 0;
				    			moves++;
				    			
				    			if (DEBUG_MODE)
				    				printGrid();
				    			
				    			if (i!=0) {
					    			grid[0][column] = grid[i][column];
					    			grid[i][column] = 0;
					    			moves++;
				    			}
				    		}
				    		else {
				    			//Do nothing.
				    		}
			    			column++;
	
		    			}
			    		else {
	
				    		//check if the queen can move horizontally
				    		boolean can = true;
				    		for (int k=j+1; k<=column; k++) {
				    			if (isQueen(i,k))
				    				can = false;
				    		}
				    		
				    		if (can) {
				    			grid[i][column] = grid[i][j];
				    			grid[i][j] = 0;
				    			moves++;

				    			if (DEBUG_MODE)
				    				printGrid();
	
				    			if (i!=0) {
					    			grid[0][column] = grid[i][column];
					    			grid[i][column] = 0;
					    			moves++;
				    			}
				    		}
				    		else {
				    			//Do nothing.
				    		}
			    			column++;
			    		}
			    		
			    		
			    		

		    			if (DEBUG_MODE)
		    				printGrid();
			    		
			    		
			    		
			    	}
			    }
			}
			
			
		}
	
		System.out.println("It took "+moves+" moves to initialize the chess board.");
	}

	public void saveState() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				initialGrid[i][j] = grid[i][j];
			}
		}
	}
	
	public void loadState() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				grid[i][j] = initialGrid[i][j];
			}
		}
	}
	
	
	public int[] nQueensProblem(int[][] forceQueens) {
		
		int constraints[][] = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				constraints[i][j] = 0;
			}
		}


		if (DEBUG_MODE)
			printConstraints(constraints);

		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				grid[i][j] = forceQueens[i][j];
			}
		}
		
		for (int j=0; j<N; j++) {

			
			int queenRow = 0;
			while (!isQueen(queenRow,j))
				queenRow++;
			//We found our queen.
			
			
			if (constraints[queenRow][j]<0) {
				//This means we are threatened.

				boolean isThreatened = constraints[queenRow][j]<0;
				//We will now a find a safe spot for our queen.
				int i = -1;
				while (isThreatened && i<N-1) {
					i++;
					isThreatened = constraints[i][j]<0;
				}
				
				if (isThreatened) {
					//This queen has no available moves.
					//Go the previous queen. 
					//If she has more than 1 available moves, then we choose her to proceed.
					//Else, we go the queen before the previous one.
					//IF we are out of queens to move, GAME OVER.
					int[] res = new int[2];
					int availableMoves = 0;
					int firstMove = 0;
					for (int k=j-1; k>=0; k--) {
						
						availableMoves = 0;
						firstMove = 0;
						
						//Find the queen.
						int q = 0;
						while (!isQueen(q,k))
							q++;
						
						//Calculate how many moves she can do.
						for (int z=q+1; z<N; z++) {
							if (constraints[z][k]==0) {
								availableMoves++;
								if (availableMoves == 1)
									firstMove = z;
							}
						}
						
						int limit = (N > 25) ? 2 : 1;
						if (N > 50) limit = 3;
						
						//If she has at least two available moves, we use her.
						if (availableMoves > limit) {
							//Return which queen to move.
							res[0] = k;
							//Return where you want to move her.
							res[1] = firstMove;
							return res;
						}
						//System.out.println(availableMoves);
					}
					
					int[] lose = new int[2];
					lose[0] = -2;
					lose[1] = -2;
					return lose;
				}
				else {
					//This means we found a safe spot.
					
					if (i == queenRow) {
						//We stay in our safe spot.
		    			if (DEBUG_MODE)
		    				System.out.println("The queen is safe here.");
					}
					else {
						//We move to the safe spot.
		    			if (DEBUG_MODE)
		    				System.out.println("The queen will move.");
						grid[i][j] = grid[queenRow][j];
						grid[queenRow][j] = 0;
						queenRow = i;
					}
				}
			}
			
			//We now update the constraints.

			if (j == N-1) {
				//We won.
				printConstraints(constraints);
				int[] win = new int[2];
				win[0] = -1;
				win[1] = -1;
				return win;
			}
			
			//Horizontal
			for (int k=j+1; k<N; k++) {
				constraints[queenRow][k] = -1;
			}

			if (DEBUG_MODE)
				printConstraints(constraints);

			//Northeast
			for (int i=queenRow-1, k = j+1; i>=0 && k<N; i--,k++) {
				constraints[i][k] = -1;
			}

			if (DEBUG_MODE)
				printConstraints(constraints);
			
			
			//Southeast
			for (int i=queenRow+1, k = j+1; i<N && k<N; i++,k++) {
				constraints[i][k] = -1;
			}
			
			
			
			
			
			

			if (DEBUG_MODE)
				printConstraints(constraints);
			
			
		}

		int[] error = new int[2];
		error[0] = -2;
		error[1] = -2;
		return error;
		
		
	}
	

	public void printConstraints(int[][] constraints) {

    	System.out.println("\n\n\nThis is the chess board right now");
    	System.out.println("----------------------------------------\n");
		for(int i=0; i<N; i++) {
		    for(int j=0; j<N; j++) {
		    	char x;
		    	if (grid[i][j]>0 && constraints[i][j]<0) {
		    		x = '+';
		    	}
		    	else if (grid[i][j]>0) {
		    		x = 'Q';
		    	}
		    	else if (constraints[i][j]==0) {
		    		x = '-';
		    	}
		    	else {
		    		x = '*';
		    	}
		    	
		    	System.out.print("| "+x+" ");
		    }
	    	System.out.println("|");
		}
		
	}
}
