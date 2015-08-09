
public class Actions {

	public static void solveFile() {
		
		try {
			int[][] board = Creator.createFromFile(Message.getFileName());
			
			if (Solver.solve(board)) {
				Message.printBoard(board);
			}
			else {
				System.out.println("Board does not have a single unique solution!");
				System.out.println();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
		
		
	}
	
	public static void createSudoku() {
		
		int [][] board = Creator.createFromScratch(new int[9][9]);
		
		Message.printBoard(board);
		
	}
	
	public static void run() {
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
	
	public static int showMenu() throws NumberFormatException {
		
	    Message.displayMenu();
    	
    	return Message.getChoice();	
	}
}
