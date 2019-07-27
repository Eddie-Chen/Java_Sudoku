package Sudoku_001;

public class Sudoku_001 {
	public static void main(String args[]) {
		//設定的棋盤中數字
		new Sudoku_001(new int[][] { 
			{ 3, 0, 6, 5, 0, 8, 4, 0, 0 }, 
			{ 5, 2, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 8, 7, 0, 0, 0, 0, 3, 1 }, 
			{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, 
			{ 9, 0, 0, 8, 6, 3, 0, 0, 5 },
			{ 0, 5, 0, 0, 9, 0, 6, 0, 0 }, 
			{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, 
			{ 0, 0, 0, 0, 0, 0, 0, 7, 4 },
			{ 0, 0, 5, 2, 0, 6, 3, 0, 0 } 
		}).solve();
	}

	private int sudoku[][];
	private int n = 9;

	public Sudoku_001(int sudoku[][]) {
		this.sudoku = sudoku;
	}
	//印出結果
	public void solve() {
		//如果backSovle回傳false，則這個棋盤無解
		if (!backSolve()) {
			System.out.println("This sudoku can't be solved.");
		}
		//參考99乘法表
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				//from left to right
				System.out.print(sudoku[i][j] + " ");
			}
			//next line
			System.out.println();
		}
	}

	public boolean backSolve() {
		int i = 0, j = 0;
		//if have cell is 0 equal false
		boolean isThereEmptyCell = false;
		//ii小於9，且(!flase); true && true
		for (int ii = 0; ii < n && !isThereEmptyCell; ii++) {
			//jj小於9，且(!flase); true && true
			for (int jj = 0; jj < n && !isThereEmptyCell; jj++) {
				//如果[ii][jj]等於0，
				if (sudoku[ii][jj] == 0) {
					//該陣列為true，空值
					isThereEmptyCell = true;
					//帶入 i 與 j 進行solve
					i = ii;
					j = jj;
				}
			}
		}
		// (!false)we have done here.
		if (!isThereEmptyCell) {
			return true;
		}
		//x數字 1~9
		for (int x = 1; x < 10; x++) {
			//這裡要用到ToPutX方法
			if (ToPutX(i, j, x)) {
				sudoku[i][j] = x;
				if (backSolve()) {
					return true;
				}
				// if 0 then failed.
				sudoku[i][j] = 0; 
			}
		}
		return false; // Backtracking
	}
	
	public boolean ToPutX(int i, int j, int x) {
		// Is 'x' used in row 橫列
		for (int jj = 0; jj < n; jj++) {
			if (sudoku[i][jj] == x) {
				return false;
			}
		}
		// Is 'x' used in column 直行
		for (int ii = 0; ii < n; ii++) {
			if (sudoku[ii][j] == x) {
				return false;
			}
		}
		// Is 'x' used in sudoku 3x3 box.
		int boxRow = i - i % 3;
		int boxColumn = j - j % 3;
		for (int ii = 0; ii < 3; ii++) {
			for (int jj = 0; jj < 3; jj++) {
				if (sudoku[boxRow + ii][boxColumn + jj] == x) {
					return false;
				}
			}
		}
		// If no problem then return true
		return true;
	}
}
