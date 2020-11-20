package texnhthfinal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	//MENU
	public static void main(String[] args)throws IOException, ClassNotFoundException {
		int answer=-1;
		int nq;
		double ttime=0;
		double k=0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean flag=false;
		while(!flag){
			try{
				do{
					System.out.println("\nEpiloges\n1)Ektelesh methodwn");//Answer 1 . o user thelei na ektelesthoun oi algorithmoi
					System.out.println("2)Exodos programmatos");//Answer 2 exodos
					System.out.print("Dialexte apantisi:");
					String input = br.readLine();
					answer= Integer.parseInt(input);
				}while(answer<0&&answer>2);
			}catch(NumberFormatException e){//Theloume apantish arithmo
				System.out.println("Your answer does not correspond to a number.Try again");
			} 
			
			
	        if(answer==1){
	        	do{System.out.print("Dwse to aritmo vasiliswn megalytero tou 3:");//Gia 3 vasilies kai katw dn yparxei lish sto provlhma kathos h kathemia tha xtypaei anagkastika kapoia
	        	String input = br.readLine();
	        	nq=Integer.parseInt(input);
	        	}while(nq<=3);
	        		        	SolutionMethods sol=new SolutionMethods(nq);//Ekteleite prwta to hill 
	        	for (int i=0;i<10;i++) {
	        		System.out.print("\n----Loop----=="+i);
	        		sol.hillclimb();
	        		
	       	}
	        	k=sol.getInits();/////////////
	    		k=k/10;
	    		ttime=sol.getTime();/////////////
	    		ttime=ttime/10;
	    		System.out.println("-->Results LocalBeam:");
	    		System.out.println("------------------->Average Number of Init grids:"+k);/////////////
	    		System.out.println("------------------->Average Number of time:"+ttime);
	        	for (int i=0;i<10;i++) {////////////////
	        		System.out.print("\n----Loop----=="+i);
	        		sol.constraintSAT();//Meta to constraint
	        	}
	        	System.out.println("-->Results Constraint Satisfaction:");
	    		ttime=sol.getTttime();////////////////////
	    		ttime=ttime/10;
	    		k=sol.getLoads();/////////
	    		k=k/10;
	    		
	    		System.out.println("------------------->Average Number of Loads:"+k);///////////
	    		System.out.println("------------------->Average Number of time:"+ttime);
	        	flag=false;
	        }if(answer==2)
	        	flag=true;//Exodos
		}
		System.out.println("\n\nTelos Programmatos\nGOODBYE\n");
	}
	
	

}
