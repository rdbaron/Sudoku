public class Sudoku {

		public static void main(String[] args) {
			
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
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			}
			
		}
		


