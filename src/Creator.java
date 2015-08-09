import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Creator {

	public static int[][] createFromScratch(int[][] board) {
		/*
		 * Simply building a unique board and then removing values from it is likely the most efficient path here.
		 * I am leaving in a secondary commented out method that involves building in the opposite direction first, then removing values.
		 * The reason for this is better discussed in person
		 */
		buildBoard(board, 0, 0);
		return removeValues(board);
		
		
		//return removeValues(fillBoard(board));
		
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
	/*
	 * This method is not currently being used.  I am leaving it in because I am not convinced the method I've chosen to go with instead
	 * is necessarily more effective.  Note this method would also need some cleaning.  It works, but I've left in some structure that I was 
	 * using to debug which could be re-writing in a more elegant way. 
	 */
	/*
	private static int[][] fillBoard(int[][] board) {
		int[][] testBoard = new int[9][9];
		int [][] puzzle = new int[9][9];
		Random rn = new Random();
		
		//Create an index of cells
		List<Integer> cells= new ArrayList<Integer>();
				
		for (int i = 0; i < 81; i++)
			cells.add(i);
			
		int solutions = 2;
		
		while (solutions != 1 && cells.size() > 0) {
			
			solutions = Solver.validateUnique(testBoard);
			
			int cell = rn.nextInt(cells.size());
			int cellValue = cells.get(cell);
				
			int row = (int) Math.floor(cellValue / 9);
			int col = cellValue % 9;
				
			puzzle[row][col] = board[row][col];
			puzzle[8-row][8-col] = board[8-row][8-col];
			testBoard = puzzle;
				
			if (Solver.validateUnique(testBoard) == 0) {
				//An invalid puzzle was produced, reset these values
				puzzle[row][col] = 0;
				puzzle[8-row][8-col] = 0;
			}

			//Either way, remove these cells from the list of possibilities
			cells.remove(cells.indexOf(cellValue));
			
			//This is here so we don't try to remove the middle cell twice
			if (cellValue != 40) 
				cells.remove(cells.indexOf(80-cellValue));
			
			testBoard = puzzle;
				
		}
		
		//At this point, check to make sure the puzzle we've filled only has one valid solution, if not try again
		testBoard = puzzle;
		if (Solver.validateUnique(testBoard) != 1) {
			System.out.println("Oops, we generated an invalid puzzle");
			return fillBoard(new int[9][9]);	
		}
		else
			return puzzle;
		
	}
	*/
	
	private static int[][] removeValues(int[][] board) {
		int[][] testBoard;
		Random rn = new Random();
		
		//Create an index of cells
		List<Integer> cells= new ArrayList<Integer>();
				
		for (int i = 0; i < 81; i++)
			cells.add(i); 
		
		//Loop through the cells and randomly try removing a matching pair (or the middle cell by itself)
		while (cells.size() > 0) {
			int cell = rn.nextInt(cells.size());
			int cellValue = cells.get(cell);
			
			int row = (int) Math.floor(cellValue / 9);
			int col = cellValue % 9;
			
			if(board[row][col] != 0){
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
			}
			//Either way, remove these cells from the list of possibilities
			cells.remove(cells.indexOf(cellValue));
			
			//This is here so we don't try to remove the middle cell twice
			if (cellValue != 40) 
				cells.remove(cells.indexOf(80-cellValue));
		}
		
		return board;		
	}
	
}


