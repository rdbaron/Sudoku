
public class Solver {
	
	public static boolean solve(int[][] board) {
		int[][] testBoard = board;
		if (validateUnique(testBoard, 0, 0, 0) == 1)
			return solve(board, 0, 0);
		else
			return false;
	}
	
	public static int validateUnique(int[][] board) {
		return validateUnique(board, 0, 0, 0);
	}
	
	private static boolean solve(int[][] board, int row, int col) {
		if (row == 9) {
			return true;
		} else if (col == 9) {
			return solve(board, row+1, 0);
		} else if (board[row][col] != 0) {
			return solve(board, row, col+1);
		}
		
		for(int i = 1; i <= 9; i++) {
			if (numFits(board, row, col, i)) {
				
				board[row][col] = i;
				
				if (solve(board, row, col+1)) {
					return true;
				}
				
				board[row][col] = 0;
			}
			
		}
		return false;
	}
	
	public static boolean numFits(int[][] board, int row, int col, int num) {
		// check validity in row
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == num)
				return false;
		}
		// check validity in col
		for (int j = 0; j < 9; j++) {
			if (board[j][col] == num)
				return false;
		}
		// check validity in square
		int startRow = row - row%3;
		int startCol = col - col%3;
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (board[startRow+x][startCol+y] == num)
					return false;
			}
		}
		//num fits, return true
		return true;
	}
	
	private static int validateUnique(int[][] board, int row, int col, int count) {
		if (row == 9) {
			return count+1;
		} else if (col == 9) {
			return validateUnique(board, row+1, 0, count);
		} else if (board[row][col] != 0) {
			return validateUnique(board, row, col+1, count);
		}
		
		for(int i = 1; i <= 9 && count < 2; i++) {
			if (numFits(board, row, col, i)) {
				
				board[row][col] = i;
				count = validateUnique(board, row, col+1, count);		
			}
			
		}
		board[row][col] = 0;
		return count;
	}
}


