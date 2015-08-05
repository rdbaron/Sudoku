public class Sudoku {

		public static void main(String[] args) {
			
			/*int[][] board = {{5,1,0,0,0,0,0,8,3},{8,0,0,4,1,6,0,0,5},{0,0,0,0,0,0,0,0,0},
								{0,9,8,5,0,4,6,1,0},{0,0,0,9,0,1,0,0,0},{0,6,4,2,0,3,5,7,0},
								{0,0,0,0,0,0,0,0,0},{6,0,0,1,5,7,0,0,4},{7,8,0,0,0,0,0,9,6}};
			*/
			
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
		


