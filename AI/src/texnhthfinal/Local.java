package texnhthfinal;

import java.util.Random;

public class Local {
	private int[][] grid;
	private int[][] collisionPairs;

	private int numberofQueens;
	
	public Local(int N) {		//Constructor for creating grid
		this.grid = new int[N][N];
		this.collisionPairs = new int[N][N]; 	//Table poy periexei ta sets ton sygrouwmenon
		numberofQueens = N;


		for(int i=0; i<numberofQueens; i++) {
		    for(int j=0; j<numberofQueens; j++) {	//Arxikopoihsh tou grid sto 0
		    	grid[i][j] = 0;
		    }
		}
		
		for(int i=0; i<numberofQueens; i++) {
		    for(int j=0; j<numberofQueens; j++) { //Arxikopoihsh tou table me ta collisions sto 0
		    	collisionPairs[i][j] = 0;		//Theloume na apothikeuoume gia oles tis vasilises tous exthrous ths
		    }									
		}
		generateQueens();	//Klhsh gia topothetisi vasiliswn se tyxaies thesis
		printGrid(grid);	//Ektypwsh tou grid

	}
	
	public int[][] getGrid() {		
		return grid;
	}
	public int getGridval(int x,int y) {
		return grid[x][y];
	}

	public void generateQueens() {
		int id = 1;		// H vasilisa anaparistate apo ena id auksanomeno
		Random rand = new Random();	//H topothetisi vasilisas ginetai tyxaia
		
		for(int i=0; i<numberofQueens; i++) {
			int col = rand.nextInt(numberofQueens-1); //Tyxaia thesi se sthlh
			int row = rand.nextInt(numberofQueens-1);//Tyxaia thesi se grammh

	    	while (getGridval(row,col)>0) {	//Elegxos an yparxei hdh vasilisa sthn thesi p pros nea kataxwrhsh vasilisas
				col = rand.nextInt(numberofQueens-1);	//Nea tyxaia thesi
				row = rand.nextInt(numberofQueens-1);
				}
	    	
	    	grid[row][col] = id; //Gia na 3eroume oti einai vasilisa topothetoume sth thesi sto grid to id kai to auksanoume
	    	id++;
		}
	}
	
	public void printGrid(int[][] ggrid) {//Ektypwsh enos grid

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
	

	public void findCollisionPairs(int x, int y, int[][] tempGrid) {//Vriskei ta collision gia mia vasilisa gia dosmeno grid 
		int queen = tempGrid[x][y];
		int col = 1;	
		
		for (int i=0; i<numberofQueens; i++) {	//find ta collisions p vriskontai sthn idia grammh
			if (tempGrid[x][i]>0 && i!=y) {
				collisionPairs[queen-1][col] = tempGrid[x][i];//Apothikeuontai ston pinaka oi sysxetiseis (Estw o 1 xtyphse tous 2,3,4. 
				col++;}}			//O 1 vrisketai sthn 1h grammh sthn 1h sthlh kai oi ypoloipoi stis epomenes thesis ths 1hs grammhs, klp
		
		for (int i=0; i<numberofQueens; i++) {			//find ta collisions p vriskontai sthn idia sthlh
			if (tempGrid[i][y]>0 && i!=x) {
				collisionPairs[queen-1][col] = tempGrid[i][y];//Omoiws apothikeuontai oi sysxetiseis pou aforoun exthrous sthn idia sthlh
				col++;
			}
		}
		
		int i,j;
		i=x+1;
		j=y+1;		//Diagwnia - Southeast
		while (i<numberofQueens && j<numberofQueens) { //Omoiws gia tous olous tous diagonious exthrous
			if (tempGrid[i][j]>0) {
				collisionPairs[queen-1][col] = tempGrid[i][j];
				col++;}
			i++;
			j++;}

		
		i=x-1;
		j=y+1;//Diagwnia - Northeast	
		while (i>=0 && j<numberofQueens) {
			if (tempGrid[i][j]>0) {
				collisionPairs[queen-1][col] = tempGrid[i][j];
				col++;}
			i--;
			j++;}
		
		i=x-1;
		j=y-1;//DIAGWNIA - NORTHWEST
		while (i>=0 && j>=0) {
			if (tempGrid[i][j]>0) {
				collisionPairs[queen-1][col] = tempGrid[i][j];
				col++;}
			i--;
			j--;}
		
		i=x+1;
		j=y-1;//Diagwnia - Southwest
		while (i<numberofQueens && j>=0) {
			if (tempGrid[i][j]>0) {
				collisionPairs[queen-1][col] = tempGrid[i][j];
				col++;}
			i++;
			j--;}
		}
	
	
	public int totalCollisionsPaired(int[][] tempGrid) {//Vriskei ton arithmo ton sygkrousewn apo ton pinaka collisionpairs
		for(int i=0;i<numberofQueens;i++) {
			for (int j=0;j<numberofQueens;j++){
				collisionPairs[i][j]=0;
			}
		}
		
		for(int i=0; i<numberofQueens; i++) {		
		    for(int j=0; j<numberofQueens; j++) {
		    	if (tempGrid[i][j] > 0) {//An einai vasilisa
		    		collisionPairs[  tempGrid[i][j] - 1   ][0] = tempGrid[i][j];//Topothetei sthn 1h sthlh se kathe grammh mia vasillises

		    		findCollisionPairs(i, j, tempGrid);	}}}//Call to function pou vriskei tous exthrous gia kathe vasilisa
		for(int i=0; i<numberofQueens; i++) {
		    for(int j=1; j<numberofQueens; j++) {
		    	if (collisionPairs[i][j] > 0) {	//H sxesh 1-5 , 5-1 theloume na yparxei mia fora
		    									//Opote mia fora to afairoume apo to collisionpairs
		    		int row = collisionPairs[i][j] - 1;	//-1 gt h vasilisa 3ekinaei apo id=1
		    		for (int k = 1; k < numberofQueens; k++ ) {	
		    			if (collisionPairs[row][k] == collisionPairs[i][0]) {//Elegxos an h sxesh yparxei 2 fores 
		    				collisionPairs[row][k] = 0;}}}}}		//Afairoume thn 2h fora p emfanizetai ( Stis teleutaies vasilises dn menoun sygkrouseis)
		
		int h = 0;	
		
		for(int i=0; i<numberofQueens; i++) {
		    for(int j=1; j<numberofQueens; j++) {//Ypologismos twn zeugariwn sygkrousewn
		    	if (collisionPairs[i][j] > 0)	//Checkaroume ston pinaka collisionpair tis times pou dn einai 0
		    		h++;
		    }
		}

		return h;
	}
	
	public void printCollisionPairs() {

    	System.out.println("\n");
		for(int i=0; i<numberofQueens; i++) {		//Print ton pinaka collisionpairs .Tha to yparxei periexomeno sthn anafora
		    for(int j=0; j<numberofQueens; j++) {
		    	System.out.print("| "+collisionPairs[i][j]+" ");
		    }
	    	System.out.println("|");
		}
		
	}
	
	

	public int[] findAvailableMoves(int queen) {//Func pou vriskei gia mia vasilisa tis diathesimes thesis sto grid se kathe kateuthinsi
		
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
		int minpositionx=0;		//Variable gia to euros diathesimwn thesewn gia thn vasilisa se grammh
		int minpositiony = 0;	//Variable gia to euros diathesimwn thesewn gia thn vasilisa se sthlh
		int maxpositiony=0;
		int maxpositionx=0;
		
		 //gia grammes
		while(x>=0)//Vriskei th mikroterh thesh pou borei n parei h vasilisa sthn idiag grammh
		{
			x--;
			if (x<0) {minpositionx=0;break;}
			if (getGridval(x,y)==0)  minpositionx=x;
			else {minpositionx=x+1;	break;}//An yparxei kapoia vasilisa pou ebodizei to perasma h mikroterh thesi vrisketai mia thesi prin ekeinh th vasilisa
		}//Prosthetoume 1 gia na paroume ekeinh ti thesi
		//Epanaferoume to pionh sth thesi t gia n vroume th megalyterh thesi pou borei n lavei
		x=thesia;
		while(x<=numberofQueens-1)//Vriskei th megalhterh thesh pou borei n parei h vasilisa sthn idiag grammh
		{		
			x++;
			if (x==numberofQueens) {maxpositionx=numberofQueens-1;break;}
			if (getGridval(x,y)==0)  maxpositionx=x;
			else {maxpositionx=x-1;	break;}	//An yparxei kapoia vasilisa pou ebodizei to perasma h megalyterh thesi vrisketai mia thesi prin ekeinh th vasilisa
		}//Afairoume 1 gia na paroume ekeinh th thesi
		x=thesia;//Epanaferoume thn thesi

		//gia sthles 
		while(y>=0)//Omoiws vriskoume to elaxisto shmeio gia thn sthlh p vriskete h vasilisa 
		{
			y--;
			if (y<0) {minpositiony=0;break;}
			if (getGridval(x,y)==0)  minpositiony=y;
			else {minpositiony=y+1;	break;}		
		}
		y=thesib;
		
		while(y<=numberofQueens-1)//To megisto shmeio p borei n parei h vasilisa
		{ 
			y++;
			if (y==numberofQueens) {maxpositiony=numberofQueens-1;break;}
			if (getGridval(x,y)==0)  maxpositiony=y;
			else {maxpositiony=y-1;	break;}		    
		}
		y=thesib;
		
		//Gia ta diagonia stoixeia xrhsimopioume metavlth wste na gnorizoume posa vhmata xriazete na pame tous deiktes sthlhs kai grammhs wste na vroume thn topothesia
		int southeast = 0;	//Southeast
		while(x<numberofQueens-1 && y<numberofQueens-1) {
			y++;x++;
			if (getGridval(x,y)==0)  
				southeast++;
			else			
				break;}
	
		//Oi metavlhtes einai southeast,northeast,southwest,northwest
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
		while(x<numberofQueens-1 && y>0) {//Southwest
			y--;x++;
			if (getGridval(x,y)==0)  
				southwest++;
			else			
				break;
		}
		
		int[] availableSpace = {minpositionx, maxpositionx, minpositiony, maxpositiony, thesia, thesib, northeast, northwest, southeast, southwest};
		return availableSpace;
	
	}
	
	
	public int successorFunc(int queen) {//Func pou ypologizei to heuristic meta apo kalyterh kinhsh
		
		int[][] tempGrid = new int[numberofQueens][numberofQueens];//Xrhsimopoume ena efedriko grid gia na alazoume thesi sto pioni kai na elegxoume gia ti thesi to neo h.
		for (int i=0; i<numberofQueens; i++) {
			for (int j=0; j<numberofQueens; j++) {
				tempGrid[i][j] = grid[i][j];}}
		
		for(int i=0; i<numberofQueens; i++) {
		    for(int o=0; o<numberofQueens; o++) {   	//Initialize to collision pair afou alazei to grid
		    		collisionPairs[i][o] = 0;	
		    }
		}
		
			
			
		int[] space = new int[10];//Variables me th plhroforia pou xriazomaste gia ta eurh ton diathesimwn thesewn
		space = findAvailableMoves(queen);
		int minpositionx = space[0];
		int maxpositionx = space[1];
		int minpositiony = space[2];
		int maxpositiony = space[3];
		int queenX = space[4]; //H grammh p vrisketai to pionh
		int queenY = space[5];//H sthlh p vrisketai to pionh
		int northEastMoves = space[6];
		int northWestMoves = space[7];
		int southEastMoves = space[8];
		int southWestMoves = space[9];
		int minH = numberofQueens*numberofQueens + numberofQueens;//Xrhsimopoioume oria wste na vroume thn kalyterh thesi kai h gia mia vasilisa
		int bestPositionX = numberofQueens;
		int bestPositionY = numberofQueens;
		
		
		//Gia oles tis diathesimes thesis p borei na lavei mia vasilisa anazhteitai auth me to mikrotero h kai to grid enhmerwnetai
		for (int j=minpositiony; j<=maxpositiony; j++) { //Allagh gia oles tis diathesimes theshs ths grammhs
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Ksanaftiaxnoume to grid se temp gia na kanoume edits
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX][queenY] = 0;	//H palia thesi ginete 0. Ara h vasilisa dn yparxei sthn palia ths thesi
			tempGrid[queenX][j] = queen;	//Alazoume thesi sth vasilisa 
			int h = totalCollisionsPaired(tempGrid); //Ypologizetai to h gia to efedriko grid me thn allgh

			
			if (h < minH) {//An dinei mikroterh h .Kratame th thesio tou pediou kai to heuristic 
				minH = h;
				bestPositionX = queenX;
				bestPositionY = j;
			}
		}
		
		for (int i=minpositionx; i<=maxpositionx; i++) {//Allagh thesis pioniou stis thesis ths sthlhs
			
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Ksanaftiaxnoume to grid se temp gia na kanoume edits
					tempGrid[k][l] = grid[k][l];}
			}
			tempGrid[queenX][queenY] = 0;
			tempGrid[i][queenY] = queen;//Epanalamvanetai h diadikasia gia to heuristic
			int h = totalCollisionsPaired(tempGrid);

			//Kai apothikeuetai h kalyterh thesi
			
			if (h < minH) {
				minH = h;
				bestPositionX = i;
				bestPositionY = queenY;
			}
		}
		//Omoiws gia oles tis diagonies theseis h idia diadikasia
		//Kathe fora ananewonetai to efedriko grid
		//Allazei h palia thesi ths vasilisas
		//Topothetite h vasilisa sthn nea thesi
		//Ypologizetai to heuristic me allagmeno to grid
		//An einai mikrotero apothikeuetai h thesi
		for (int i=1; i<=northEastMoves; i++) {
			for (int k=0; k<numberofQueens; k++) {
				for (int l=0; l<numberofQueens; l++) {//Ksanaftiaxnoume to grid se temp gia na kanoume edits
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
				for (int l=0; l<numberofQueens; l++) {//Ksanaftiaxnoume to grid se temp gia na kanoume edits
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
				for (int l=0; l<numberofQueens; l++) {//Ksanaftiaxnoume to grid se temp gia na kanoume edits
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
				for (int l=0; l<numberofQueens; l++) {//Ksanaftiaxnoume to grid se temp gia na kanoume edits
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
		grid[bestPositionX][bestPositionY] = queen;//Telika allazei to grid me thn kalyterh thesi p ypologistike
		int h = totalCollisionsPaired(grid);//Epistrefete to h tou neou grid

		return h;		
		
		
		
	}
	
	public void setGridval(int [][] ggrid) {//Setter gia initialize apo allo grid
		for (int k=0;k<numberofQueens;k++) {
			for(int l=0;l<numberofQueens;l++) {
				grid[k][l]=ggrid[k][l];
			}
		}
	}

	public int chooseQueen() {	//Func pou dialegei th vasilisa gia thn opoia tha kanoume kinhsh
		Random rn=new Random();
		int h1=totalCollisionsPaired(grid);	//Ypologizei to arxiko h 
		int queen=0;
		int limit=-1;//Theoroume oti otan ftasoume konta se lysh borei na eimaste se topika akrotata kai theloume na doume an yparxei  kalyterh lysh
		boolean flag=false;
		if (numberofQueens>30) {
			limit=5;
		}//Dyo tropopoihseis tou limit gia to skopo parapanw
		else limit=3;
		while (h1!=0) {//Lysh exoume otan h=0
			if(h1>=limit){
				queen=rn.nextInt(numberofQueens)+1; //Gia megala h epilegoume tyxaia
				h1=successorFunc(queen);
			}
			else {
				int h=totalCollisionsPaired(grid);
				for (int i=0;i<numberofQueens;i++) {
					queen=i;//Otan ftasoume to limit elegxoume seiriaka tis vasilises wste na doume oti ta ftasame se akrotato
					h1=successorFunc(queen+1); //Call to successorfunc p vriskei thn kalyterh lysh (mikrotero h) gia mia vasilisa kai thn topothetei sto grid
					
					if (h1<h ){ //An mikrinei mhn koitas tis ypoloipes
						flag=true;
						break;}
					else
						flag=false;//Check oles wste na mhn exoun kalyterh kinhsh
				}
				if (flag==false)//Topiko elaxisto an exei checkarei oles tis vasilises kai kamia dn dinei kalyterh lysh
					break;
			}		
		}
		return h1;
		
	}
}
