import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Random;

public class Creator {

	public static int[][] createFromScratch(int[][] board) {
		//In order to produce a new game board, start by creating a fully filled out board, then remove values until you can't remove more.
		buildBoard(board, 0, 0);
	
		return removeValues(board);
	}
	
	public static boolean buildBoard(int[][] board) {
		return buildBoard(board, 0, 0);
	}
	
	public static int[][] createFromFile(String path) throws Exception {
		
		int[][] board = new int[9][9];
		
		try(FileReader fr = new FileReader(path)) {
			BufferedReader br = new BufferedReader(fr);
			String str;
			
			for (int row = 0; row < 9; row++) {
				if ((str = br.readLine()) != null) {
					char[] line = str.toCharArray();
				
					if (line.length == 9) {
						for (int col = 0; col < 9; col++) {
							int value = Character.getNumericValue(line[col]);
							
							//Assign value if between 1 and 9, otherwise assign 0
							if (value > 0 && value < 10)
								board[row][col] = value;
							else
								board[row][col] = 0;
						}
					}
					else
						throw new Exception("Wrong number of columns!");
				}
					
				else
					throw new Exception("Not enough rows!");
					
			}
			
			if ((str = br.readLine()) != null)
				throw new Exception ("Too many rows!");
			else
				return board;
		}			
	}
		
	private static boolean fillCell(int[][] board, int row, int col) {
		HashSet<Integer> tried = new HashSet<Integer>();
		
		while (tried.size() < 9 && board[row][col] == 0) {
			Random rn = new Random();
			int value = rn.nextInt(9) + 1;
			
			if (Solver.numFits(board, row, col, value)) {
				board[row][col] = value;
				return true;
			}
			
			tried.add(value);
		}
		
		return false;
	}
	
	private static boolean buildBoard(int[][] board, int row, int col) {
		if (row == 9) 
			return true;
		else if (col == 9) 
			return buildBoard(board, row+1, 0);
		
		for(int i = 1; i <= 9; i++) {
			if (fillCell(board, row, col)) {
				
				if (buildBoard(board, row, col+1)) {
					return true;
				}
				
				board[row][col] = 0;
			}	
		}
		return false;
	}

	private static int[][] removeValues(int[][] board) {
		int[][] testBoard;
		Random rn = new Random();
		
		//Create an index of cells
		HashSet<Integer> cells= new HashSet<Integer>();
				
		for (int i = 0; i < 81; i++)
			cells.add(i); 
		
		//Loop through the cells and randomly try removing a matching pair (or the middle cell by itself
		//Increment by two because two cells will be removed each time
		for (int i = 0; i < 81; i += 2) {
			int cell = rn.nextInt(cells.size());
			
			int row = (int) Math.floor(cell/9);
			int col = cell % 9;
			//Store existing values in case we need to revert
			int[] curValues = {board[row][col], board[8-row][8-col]};
			
			board[row][col] = 0;
			board[8-row][8-col] = 0;
			testBoard = board;
			
			if (Solver.validateUnique(testBoard) != 1) {
				//An invalid puzzle was produced, reset these values
				board[row][col] = curValues[0];
				board[8-row][8-col] = curValues[1];
			}

			//Either way, remove these cells from the list of possibilities
			cells.remove(cell);
			cells.remove(80-cell);
			
		}
		
		return board;		
	}
	
}


