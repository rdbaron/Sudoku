import java.io.File;
import java.util.Scanner;

public class Message {

	public static void displayMenu() {
		
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
    	
	}
	
	public static int getChoice() {
		int choice = 0;
	    String strChoice = "";
	    
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
	
	public static String getFileName() {
		String strChoice = "";
	    
	    Scanner userInput = new Scanner(System.in);
    	
	    //Keep asking until a valid file is given.
	    while(!new File(strChoice).isFile()) {
	    	System.out.print("Enter the path to the file you would like to solve: ");
	    		
	    	strChoice = userInput.next();
	    		       		
	    }
	    
	    return strChoice;
	}
	
	public static void printBoard(int[][] board) {
		
		System.out.println("RESULT:");
		System.out.println();
		System.out.println();
		
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (board[row][col] == 0)
					System.out.print(".");
				else
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
		
		System.out.println();
	}
}
