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
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			*/
	}
		
	private static void run() {
		boolean keepRunning = true;
		
		while(keepRunning){
			int choice = showMenu();
			
			switch (choice) {
				case 1:	Actions.solveFile();
						break;
				case 2: Actions.createSudoku();
						break;
				case 3: keepRunning = false;
						System.out.println("Thanks for playing!");
						break;
			}
		}
		
	}
	
	private static int showMenu() throws NumberFormatException {
		
	    Message.displayMenu();
    	
    	return Message.getChoice();	
	}
			
}
		


