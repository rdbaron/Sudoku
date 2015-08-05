import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Random;

public class Creator {

	public static boolean createFromScratch(int[][] board) {
		buildBoard(board, 0, 0);
		return removeValues(board, 25);
		//return createFromScratch2(board);
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
	
	/*public static boolean buildBoard(int[][] board) {
		Random rn = new Random();
		
		if (Solver.validateUnique(board) == 1) 
			return true;
		else {
			int row = rn.nextInt(10);
			int col = rn.nextInt(10);
			
			if (board[row][col] == 0) {
				//If you can fill board[row][col], attempt to fill board[col][row] for symmetry
				if (fillCell(board, row, col)) {
					if (!fillCell(board, col, row))
						//Reset board[row][col] if board[col][row] fails
						board[row][col] = 0;
				}
				
			}
			
			if (buildBoard(board))
				return true;
			
			board[row][col] = 0;
			board[col][row] = 0;
			
		}
		return false;
	}*/
	
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
	
	private static boolean removeValues(int[][] board, int numRemoves) {
		//Create an index of cells
		HashSet<Integer> cells= new HashSet<Integer>();
		
		for (int i = 0; i < 81; i++)
			cells.add(i); 
		
		return removeValues(board, numRemoves, cells);
		
	}

	private static boolean removeValues(int[][] board, int numRemoves, HashSet<Integer> cells) {
		System.out.println("Number of removes left: " + numRemoves);
		if (numRemoves == 0)
			return true;
		
		int[][] testBoard;
		Random rn = new Random();
		
		while (cells.size() > 0) {
			int cell = rn.nextInt(cells.size());
			
			int row = (int) Math.floor(cell/9);
			int col = cell%9;
			
			int curValue = board[row][col];
			board[row][col] = 0;
			testBoard = board;
			cells.remove(cell);
			
			if (Solver.validateUnique(testBoard) == 1)
				if (removeValues(board, numRemoves-1, cells))
					return true;
			
			board[row][col] = curValue;
			numRemoves += 1;
			return false;
		}
		
		return false;
		
	}
	
	/*private static boolean createFromScratch2(int[][] board) {
		System.out.println("Number of soln: " + Solver.validateUnique(board));
		if (Solver.validateUnique(board) == 1) 
			return true;
		
		//Randomly select a cell to fill
		Random rn = new Random();
		int row = rn.nextInt(9);
		int col = rn.nextInt(9);
		
		System.out.println("Row and Col: " + row + " " + col);
		System.out.println("Current value: " + board[row][col]);
		//If you hit an already filled cell, try again
		if (board[row][col] != 0)
			return createFromScratch2(board);
		
		//Fill the cell, and ensure a valid solution remains
		if (fillCell(board, row, col)) {
			System.out.println("New value: " + board[row][col]);
			System.out.println("Number of soln changes to: " + Solver.validateUnique(board));
			if (Solver.validateUnique(board) > 0) {
				
				if (createFromScratch2(board)) {
					return true;
				}
			}
		}
		
		System.out.println("Switch value back to 0");
		board[row][col] = 0;
		return false;
	}*/
}


