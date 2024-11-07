package main;

import java.util.Random;

public class Local {
	private int[][] grid;
	private int[][] collisionPairs;

	private int numberofQueens;
	
	public Local(int N) {		//Constructor for creating grid
		this.grid = new int[N][N];
		this.collisionPairs = new int[N][N]; 	//Table containing the set of colliders
		numberofQueens = N;


		for(int i=0; i<numberofQueens; i++) {
		    for(int j=0; j<numberofQueens; j++) {	//Initialize grid to 0
		    	grid[i][j] = 0;
		    }
		}
		
		for(int i=0; i<numberofQueens; i++) {
		    for(int j=0; j<numberofQueens; j++) { //Initialization of the table with the collisions to 0
		    	collisionPairs[i][j] = 0;		//We want to save for all queens her enemies
		    }									
		}
		generateQueens();	//Random position queens
		printGrid(grid);	//Print grid

	}
	
	public int[][] getGrid() {		
		return grid;
	}
	public int getGridval(int x,int y) {
		return grid[x][y];
	}

	public void generateQueens() {
		int id = 1;		// Queen = id 
		Random rand = new Random();	//Random position to a queen
		
		for(int i=0; i<numberofQueens; i++) {
			int col = rand.nextInt(numberofQueens-1); //Random position in column
			int row = rand.nextInt(numberofQueens-1);//Random position in row

	    	while (getGridval(row,col)>0) {	//Check if there is already a queen in place for a new queen entry
				col = rand.nextInt(numberofQueens-1);	//New position
				row = rand.nextInt(numberofQueens-1);
				}
	    	
	    	grid[row][col] = id; //To know that she is a queen, we place the id in the grid and increase it
	    	id++;
		}
	}
	
	public void printGrid(int[][] ggrid) {//Print grid function

    	System.out.println("\nThis is the chess board right now");
    	System.out.println("----------------------------------------\n");
		for(int i=0; i<numberofQueens; i++) {
		    for(int j=0; j<numberofQueens; j++) {	//An exei thetikh timh kapoia thesi anaparistate apo vasilisa me Q
		    	char x =ggrid[i][j]>0 ? 'Q' : '-';
		    	System.out.print("| "+x+" ");
		    }
	    	System.out.println("|");
		}
		
	}
	

	public void findCollisionPairs(int x, int y, int[][] tempGrid) {//Finds the collision for a queen with a given grid
		int queen = tempGrid[x][y];
		int col = 1;	
		
		for (int i=0; i<numberofQueens; i++) {	//find collisions in same row
			if (tempGrid[x][i]>0 && i!=y) {
				collisionPairs[queen-1][col] = tempGrid[x][i];//The correlations are stored in the table ( 1 hit 2,3,4)
				col++;}}			//1 is in the 1st line in the 1st column and the others in the following positions in the 1st line
		for (int i=0; i<numberofQueens; i++) {			//find collisions in same column
			if (tempGrid[i][y]>0 && i!=x) {
				collisionPairs[queen-1][col] = tempGrid[i][y];//Similarly, associations concerning enemies are stored in the same column
				col++;
			}
		}
		
		int i,j;
		i=x+1;
		j=y+1;		//Diagonally - South east
		while (i<numberofQueens && j<numberofQueens) { //Similarly, associations concerning enemies diagonally southeast
			if (tempGrid[i][j]>0) {
				collisionPairs[queen-1][col] = tempGrid[i][j];
				col++;}
			i++;
			j++;}

		
		i=x-1;
		j=y+1;//Diagonally - North east	
		while (i>=0 && j<numberofQueens) {
			if (tempGrid[i][j]>0) {
				collisionPairs[queen-1][col] = tempGrid[i][j];  //Similarly, associations concerning enemies diagonally northeast
				col++;}
			i--;
			j++;}
		
		i=x-1;
		j=y-1;//Diagonally - North west
		while (i>=0 && j>=0) {
			if (tempGrid[i][j]>0) {//Similarly, associations concerning enemies diagonally northwest
				collisionPairs[queen-1][col] = tempGrid[i][j];
				col++;}
			i--;
			j--;}
		
		i=x+1;
		j=y-1;//Diagonally - South west
		while (i<numberofQueens && j>=0) {//Similarly, associations concerning enemies diagonally south west
			if (tempGrid[i][j]>0) {
				collisionPairs[queen-1][col] = tempGrid[i][j];
				col++;}
			i++;
			j--;}
		}
	
	
	public int totalCollisionsPaired(int[][] tempGrid) {//Finds the number of colliders from the collision pairs table
		for(int i=0;i<numberofQueens;i++) {
			for (int j=0;j<numberofQueens;j++){
				collisionPairs[i][j]=0;
			}
		}
		
		for(int i=0; i<numberofQueens; i++) {		
		    for(int j=0; j<numberofQueens; j++) {
		    	if (tempGrid[i][j] > 0) {//For queen
		    		collisionPairs[  tempGrid[i][j] - 1   ][0] = tempGrid[i][j];//Places a queen in the 1st column in each row

		    		findCollisionPairs(i, j, tempGrid);	}}}//Call func that finds enemies for a queen
		for(int i=0; i<numberofQueens; i++) {
		    for(int j=1; j<numberofQueens; j++) {
		    	if (collisionPairs[i][j] > 0) {	//The relationship 1-5 5-1 we want to exist once
		    									//So we remove it from collisionpairs
		    		int row = collisionPairs[i][j] - 1;	//-1 first queen has id 1
		    		for (int k = 1; k < numberofQueens; k++ ) {	
		    			if (collisionPairs[row][k] == collisionPairs[i][0]) {//Check if the correlation exists 2 times
		    				collisionPairs[row][k] = 0;}}}}}		//Remove the 2nd time it appears. In the last queens no conflicts remain
		
		int h = 0;	
		
		for(int i=0; i<numberofQueens; i++) {
		    for(int j=1; j<numberofQueens; j++) {// Calculation of the pairs that collide
		    	if (collisionPairs[i][j] > 0)	//Check table collisionpair with no zero values
		    		h++;
		    }
		}

		return h;
	}
	
	public void printCollisionPairs() {

    	System.out.println("\n");
		for(int i=0; i<numberofQueens; i++) {		//Print collision pair table
		    for(int j=0; j<numberofQueens; j++) {
		    	System.out.print("| "+collisionPairs[i][j]+" ");
		    }
	    	System.out.println("|");
		}
		
	}
	
	

	public int[] findAvailableMoves(int queen) {//Func that finds for a queen the available positions on the grid in every direction
		
		int x;
		int y;
		int thesia = 0,thesib = 0;
        
        		for (int i=0;i<numberofQueens;i++){
			for(int j=0;j<numberofQueens;j++){
				if(grid[i][j]==queen){
				 thesia=i;	//
				 thesib=j;
				 break;}}}
		x=thesia;
		y=thesib;
		int minpositionx=0;		//Variable for the range of available moves for the queen in line
		int minpositiony = 0;	//Variable for the range of available moves for the queen in column
		int maxpositiony=0;
		int maxpositionx=0;
		
		 //lines
		while(x>=0)//Finds the shortest distance that the queen can take on the same line
		{
			x--;
			if (x<0) {minpositionx=0;break;}
			if (getGridval(x,y)==0)  minpositionx=x;
			else {minpositionx=x+1;	break;}//If there is a queen that prevents passage, the smallest position is one position before that queen
		}//We add 1 to take that position
		//Return the pawn to its position to find the largest position it can take
		x=thesia;
		while(x<=numberofQueens-1)//Finds the largest position that the queen can take on the same line
		{		
			x++;
			if (x==numberofQueens) {maxpositionx=numberofQueens-1;break;}
			if (getGridval(x,y)==0)  maxpositionx=x;
			else {maxpositionx=x-1;	break;}	//If there is a queen that prevents passage, the highest position is one position before that queen
		}//Afairoume 1 gia na paroume ekeinh th thesi
		x=thesia;//Remove one to take that position

		//Columns
		while(y>=0)//Similarly we find the minimum point for the column where the queen is located
		{
			y--;
			if (y<0) {minpositiony=0;break;}
			if (getGridval(x,y)==0)  minpositiony=y;
			else {minpositiony=y+1;	break;}		
		}
		y=thesib;
		
		while(y<=numberofQueens-1)//Similarly we find the maximum point for the column where the queen is located
		{ 
			y++;
			if (y==numberofQueens) {maxpositiony=numberofQueens-1;break;}
			if (getGridval(x,y)==0)  maxpositiony=y;
			else {maxpositiony=y-1;	break;}		    
		}
		y=thesib;
		
		//For the diagonal elements we use a variable to know how many steps we need to take the column and line markers to find the location
		int southeast = 0;	//South east
		while(x<numberofQueens-1 && y<numberofQueens-1) {
			y++;x++;
			if (getGridval(x,y)==0)  
				southeast++;
			else			
				break;}
	
		//Variables: south east,northeast,south west,northwest
		x=thesia;y=thesib;int northeast = 0;
		while(x>0 && y<numberofQueens-1) {//Northeast
			y++;x--;
			if (getGridval(x,y)==0)  
				northeast++;
			else			
				break;}

		
		x=thesia;y=thesib;int northwest = 0;
		while(x>0 && y>0) {//Northwest
			y--;x--;
			if (getGridval(x,y)==0)  
				northwest++;
			else			
				break;
		   	}
		
		
		x=thesia;y=thesib;int southwest = 0;
		while(x<numberofQueens-1 && y>0) {//South west
			y--;x++;
			if (getGridval(x,y)==0)  
				southwest++;
			else			
				break;
		}
		
		int[] availableSpace = {minpositionx, maxpositionx, minpositiony, maxpositiony, thesia, thesib, northeast, northwest, southeast, southwest};
		return availableSpace;
	
	}
	
	
	public int successorFunc(int queen) {//Func that calculates the heuristic after the best move
		
		int[][] tempGrid = new int[numberofQueens][numberofQueens];//We use a redundant grid to change position in the pioni and check for the position in the new h.
		for (int i=0; i<numberofQueens; i++) {
			for (int j=0; j<numberofQueens; j++) {
				tempGrid[i][j] = grid[i][j];}}
		
		for(int i=0; i<numberofQueens; i++) {
		    for(int o=0; o<numberofQueens; o++) {   	//Initialize to collision pair after grid updates
		    		collisionPairs[i][o] = 0;	
		    }
		}
		
			
			
		int[] space = new int[10];//Variables with the information we need about the ranges of available seats
		space = findAvailableMoves(queen);
		int minpositionx = space[0];
		int maxpositionx = space[1];
		int minpositiony = space[2];
		int maxpositiony = space[3];
		int queenX = space[4]; //The line where the pawn is located
		int queenY = space[5];//The column where the pawn is located
		int northEastMoves = space[6];
		int northWestMoves = space[7];
		int southEastMoves = space[8];
		int southWestMoves = space[9];
		int minH = numberofQueens*numberofQueens + numberofQueens;//We use boundaries to find the best place for a queen
		int bestPositionX = numberofQueens;
		int bestPositionY = numberofQueens;
		
		
		//For all available positions that a queen can take, the one with the smallest h is searched for and the grid is updated
		for (int j=minpositiony; j<=maxpositiony; j++) { //Change for all available positions on the line
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Rebuild the grid in temp to make edits
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX][queenY] = 0;	//The old position becomes 0. So the queen does not exist in the old position.
			tempGrid[queenX][j] = queen;	//Change position to queen 
			int h = totalCollisionsPaired(tempGrid); //Calculate h for the redundant grid after change

			
			if (h < minH) {//If it gives smaller h. We keep the position of the field and the heuristic
				minH = h;
				bestPositionX = queenX;
				bestPositionY = j;
			}
		}
		
		for (int i=minpositionx; i<=maxpositionx; i++) {//Changing the position of a pawn in the column positions
			
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Rebuild the grid in temp to make edits
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX][queenY] = 0;
			tempGrid[i][queenY] = queen;//Repeat process for heuristic
			int h = totalCollisionsPaired(tempGrid);

			//Best position is stored
			
			if (h < minH) {
				minH = h;
				bestPositionX = i;
				bestPositionY = queenY;
			}
		}
		//Similarly for all diagonal positions the same procedure
		//Every time, redundant grid is updated
		//Change the old position of the queen
		//Place queen to a new position
		//Calculate heuristic with the updated grid
		//For (lower - better ) h, best solution is stored
		for (int i=1; i<=northEastMoves; i++) {
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Rebuild the grid in temp to make edits
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX][queenY] = 0;
			tempGrid[queenX - i][queenY + i] = queen;
			int h = totalCollisionsPaired(tempGrid);

			if (h <= minH) {
				minH = h;
				bestPositionX = queenX - i;
				bestPositionY = queenY + i;
			}
		}
		for (int i=1; i<=northWestMoves; i++) {
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Rebuild the grid in temp to make edits
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX][queenY] = 0;
			tempGrid[queenX - i][queenY - i] = queen;
			int h = totalCollisionsPaired(tempGrid);

			if (h <= minH) {
				minH = h;
				bestPositionX = queenX - i;
				bestPositionY = queenY - i;
			}
		}
		for (int i=1; i<=southEastMoves; i++) {
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Rebuild the grid in temp to make edits
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX + i][queenY + i] = queen;
			tempGrid[queenX][queenY] = 0;
			int h = totalCollisionsPaired(tempGrid);

			if (h <= minH) {
				minH = h;
				bestPositionX = queenX + i;
				bestPositionY = queenY + i;
			}
		}
		for (int i=1; i<=southWestMoves; i++) {
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Rebuild the grid in temp to make edits
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX + i][queenY - i] = queen;
			tempGrid[queenX][queenY] = 0;
			int h = totalCollisionsPaired(tempGrid);

			if (h <= minH) {
				
				minH = h;
				bestPositionX = queenX + i;
				bestPositionY = queenY - i;
			}
		}
		grid[queenX][queenY] = 0;
		grid[bestPositionX][bestPositionY] = queen;// Change to the best grid
		int h = totalCollisionsPaired(grid);//Return h of the new grid

		return h;		
		
		
		
	}
	
	public void setGridval(int [][] ggrid) {//Setter grid initialization
		for (int k=0;k<numberofQueens;k++) {
			for(int l=0;l<numberofQueens;l++) {
				grid[k][l]=ggrid[k][l];
			}
		}
	}

	public int chooseQueen() {	//Func that selects the queen who will make a move
		Random rn=new Random();
		int h1=totalCollisionsPaired(grid);	//Calculate starting h 
		int queen=0;
		int limit=-1;//We believe that when we get close to a solution. We may be at local extremes and we want to see if there is a better solution
		boolean flag=false;
		if (numberofQueens>30) {
			limit=5;
		}//Two modifications of the Limit for the above purpose
		else limit=3;
		while (h1!=0) {//Solution for h=0
			if(h1>=limit){
				queen=rn.nextInt(numberofQueens)+1; //For large we choose randomly
				h1=successorFunc(queen);
			}
			else {
				int h=totalCollisionsPaired(grid);
				for (int i=0;i<numberofQueens;i++) {
					queen=i;//When we reach the limit we check the queens serially to see if we have reached the real limit.
					h1=successorFunc(queen+1); //Call successorfunc that finds best solution (small h) for a queen and places on the grid			
					if (h1<h ){ //If it's getting smaller, don't look at the others
						flag=true;
						break;}
					else
						flag=false;//Check all so we don't have better traffic
				}
				if (flag==false)//Local minimum if it has checked all the queens and none gives a better solution
					break;
			}		
		}
		return h1;
		
	}
}
