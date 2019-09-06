import java.util.ArrayList;

public class NQueens {

    int n;
    int boardSize;
    int[][] board;

    public NQueens(int n){
        this.n = n;
        boardSize = n;
        board = new int[n][n];
    }

    boolean placeNQueens() {
        if (n <= 0) {
            throw new ArithmeticException();
        } else if (n == 2 || n == 3) {
            return false;
        } else {
            board = new int[n][n];
            placingQueens(board, 0);
            printToConsole();
            return true;
        }
    }
    private static boolean placingQueens(int board[][], int row){
        if(row>=board.length){
            return true;
        }
        boolean allQueensPlaced = false;
        for (int j = 0; j < board.length; j++) {

            if(isSafe(board, row, j)){
                board[row][j] = 1;
                allQueensPlaced = placingQueens(board, row+1);
            }
            if(allQueensPlaced){
                break;
            }else{
                board[row][j] = 0;
            }
        }
        return allQueensPlaced;
    }
    private static boolean isSafe(int board[][], int row, int col){
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            if(board[i][j] == 1){
                return false;
            }
        }
        for (int i = row-1, j = col+1; i >= 0 && j < board.length; i--, j++) {
            if(board[i][j] == 1){
                return false;
            }
        }
        for (int i = row-1; i >= 0; i--) {
            if(board[i][col] == 1){
                return false;
            }
        }
        return true;
    }
    void printToConsole(){
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if(board[row][col] == 1){
                    System.out.print("Q ");
                }else{
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
        System.out.println("");
    }
}
