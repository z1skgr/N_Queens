package main;
import java.io.BufferedReader; // Importing BufferedReader for reading input
import java.io.IOException; // Importing IOException for handling input/output exceptions
import java.io.InputStreamReader; // Importing InputStreamReader for reading input from the console

public class Main {
	//MENU
	public static void main(String[] args)throws IOException, ClassNotFoundException {
		int answer=-1; // Variable to store user’s menu choice
		int nq;
		double ttime=0; // Variable to store total time taken for operations
		double ld=0; //Variable to store average loads

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean flag=false;
		while(!flag){ // Flag to control the loop for menu options
			try{
				do{
					System.out.println("\nChoices\n1)Method Execution");//// Option 1: Execute methods
					System.out.println("2)Exit program");// Option 2: Exit program
					System.out.print("Pick a choice (1 or 2):");
					String input = br.readLine();
					answer= Integer.parseInt(input);
				}while(answer<0&&answer>2); // Loop until a valid option is selected
			}catch(NumberFormatException e){//Catching exception for invalid number format
				System.out.println("Your answer does not correspond to a number.Try again");
			} 
			
			
	        if(answer==1){
	        	// Prompting user to enter the number of bases greater than 3
	        	do{System.out.print("Give a queen number greater than 3:");//Each one is bound to hit some
	        	String input = br.readLine(); 
	        	nq=Integer.parseInt(input); // Parsing input to an integer
	        	}while(nq<=3);
	        		        	SolutionMethods sol=new SolutionMethods(nq);//Hill climb first
	        	for (int i=0;i<10;i++) {
	        		System.out.print("\n----Loop----=="+i);
	        		sol.hillclimb(); // Executing the hill climbing method
	        		
	       	} // Retrieving the results
	        	ld=sol.getInits();/////////////
	    		ld=ld/10;
	    		ttime=sol.getTime();/////////////
	    		ttime=ttime/10;
	    		System.out.println("-->Results LocalBeam:");
	    		System.out.println("------------------->Average Number of Init grids:"+ld);///////////// // Displaying average initial grids
	    		System.out.println("------------------->Average Number of time:"+ttime); // Displaying average time taken
	        	for (int i=0;i<10;i++) {////////////////
	        		System.out.print("\n----Loop----=="+i);
	        		sol.constraintSAT();//// Executing the constraint satisfaction method
	        	}
	        	System.out.println("-->Results Constraint Satisfaction:");
	        	// Retrieving the results
	    		ttime=sol.getTttime();////////////////////
	    		ttime=ttime/10;
	    		ld=sol.getLoads();/////////
	    		ld=ld/10;
	    		
	    		System.out.println("------------------->Average Number of Loads:"+ld);/////////// Displaying average initial grids
	    		System.out.println("------------------->Average Number of time:"+ttime);// Displaying average time taken
	        	flag=false;
	        }if(answer==2)
	        	flag=true;//E
		}
		System.out.println("\n\nΕnd Program\nGOODBYE\n");
	}
	
	

}
