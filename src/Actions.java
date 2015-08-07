
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
}
