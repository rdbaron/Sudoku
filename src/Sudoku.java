import java.util.Scanner;

public class Sudoku {

	public static void main(String[] args) {
		
		run();
			/*
			try {
				//int[][] board = Creator.createFromFile("C:\\sudoku\\easy2.txt"); 
				int[][] board = new int[9][9];
				Creator.createFromScratch(board);
			
				System.out.println("RESULT:");
				System.out.println();
				System.out.println();
		
				for (int row = 0; row < 9; row++) {
					for (int col = 0; col < 9; col++) {
						System.out.print(board[row][col]);
						if (col == 2 || col == 5)
							System.out.print(" | ");
						if (col == 8) {
							System.out.println();
							if (row == 2 || row == 5)
								System.out.println("--- + --- + ---");
						}
					}
				}
				*/
				/*
				if (Solver.solve(board)) {
			
					for (int row = 0; row < 9; row++) {
						for (int col = 0; col < 9; col++) {
							System.out.print(board[row][col]);
							if (col == 2 || col == 5)
								System.out.print(" | ");
							if (col == 8) {
								System.out.println();
								if (row == 2 || row == 5)
									System.out.println("--- + --- + ---");
							}
						}
					}
				}
				else
					System.out.println("Board does not have a single unique solution!");
				*/
			/*	
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			*/
	}
		
	private static void run() {
		boolean keepRunning = true;
		
		while(keepRunning){
			int choice = showMenu();
			
		}
		
	}
	
	private static int showMenu() throws NumberFormatException {
		int choice = 0;
	    String strChoice = "";
	    
	    System.out.println("\n\n-------------------------------------");
    	System.out.println("|        Welcome to Sudoku!         |");
    	System.out.println("|-----------------------------------|");
    	System.out.println("|                                   |");
    	System.out.println("| Main Menu:                        |");
    	System.out.println("| ----------                        |");
    	System.out.println("|                                   |");
    	System.out.println("| 1.) Solve a Sudoko                |");
    	System.out.println("| 2.) Create a new Sudoko           |");
    	System.out.println("| 3.) Exit                          |");
    	System.out.println("-------------------------------------");
    	
    	Scanner userInput = new Scanner(System.in);
    	
	    //Keep asking until a valid choice is selected.
	    while((choice < 1)||(choice > 3)) {
	    	System.out.print("Please choose a valid option: ");
	    		
	    	strChoice = userInput.next();
	    		    
	    	try {
	    		choice = Integer.parseInt(strChoice);
	    	}
	    	catch (NumberFormatException e) {
	    		choice = -1;
	    	}    		
	    }
	   
	    return choice;	
	}
			
}
		


