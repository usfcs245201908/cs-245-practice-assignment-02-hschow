import java.util.ArrayList;

public class NQueens {

    int n;
    int boardSize;
    int[][] board;
    int[][][] spots;

    public NQueens(int n){
        this.n = n;
        boardSize = n;
        board= new int[n][n];
        spots = new int[n][n][2];
    }

    boolean placeNQueens(){
        if (n<=0){
            throw new ArithmeticException();
        }
        else if (n==2 || n==3){
            return false;
        }
        else {
            int placed = 0;
            while (placed<n){
                for (int i = 0;i<n;i++){
                    for (int j = 0;j<n;j++){
                        if (board[i][j]==0) {
                            board[i][j] = 1;
                            spots[placed][placed][0] = i;
                            spots[placed][placed][1] = j;
                            placed++;
                            attackZone(i,j);
                        }
                        else{
                            if (i+1==n && j+1==n && placed != n){
                                i=spots[placed][placed][0];
                                j=spots[placed][placed][1];
                                placed = undo(spots[placed][placed][0],spots[placed][placed][1],placed);
                                for (int h =0;h<placed;h++){
                                    attackZone(spots[h][h][0], spots[h][h][1]);
                                }
                            }
                        }
                    }
                }
            }
            if (placed==n){
                printToConsole();
            }
            else {
                System.out.println(n+" queens were not abe to be placed!");
            }
            return true;
        }
    }
    void printToConsole(){
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                boolean checked = false;
                for (int t = 0; t<spots.length;t++){
                    int tempX = spots[t][t][0];
                    int tempY = (spots[t][t][1]);
                    if (i==tempX & j==tempY){
                        System.out.print(" Q  ");
                        checked = true;
                    }
                }
                if (checked==false){
                    System.out.print(" _  ");
                }

            }
            System.out.print("\n");
        }
    }
    void attackZone(int i, int j){
        for (int k = 0; k < n; k++) {
            board[i][k] = 2;
            board[k][j] = 2;
        }
        int row = i;
        int col = j;
        while (row < n && col < n) {
            if (board[row][col] == 0) {
                board[row][col] = 2;
            }
            row++;
            col++;
        }
        row = i;
        col = j;
        while (row >= 0 && col >= 0) {
            if (board[row][col] == 0) {
                board[row][col] = 2;
            }
            row--;
            col--;
        }
        row = i;
        col = j;
        while (row >= 0 && col < n) {
            if (board[row][col] == 0) {
                board[row][col] = 2;
            }
            row--;
            col++;
        }
        row=i;
        col=j;
        while (row<n&&col>=0){
            if (board[row][col]==0){
                board[row][col]=2;
            }
            row++;
            col--;
        }
        board[i][j]=1;
    }
    int undo(int i, int j, int placed){
        int lastX=i;
        int lastY=j;
        board[lastX][lastY]=2;
        placed--;
        for (int a = 0;a<n;a++){
            board[lastX][a]=0;
            board[a][lastY]=0;
        }
        int row=i;
        int col=j;
        while (row<n&&col<n){
            board[row][col]=0;
            row++;
            col++;
        }
        row=lastX;
        col=lastY;
        while (row>=0&&col>=0){
            board[row][col]=0;
            row--;
            col--;
        }
        row=lastX;
        col=lastY;
        while (row>=0&&col<n){
            board[row][col]=0;
            row--;
            col++;
        }
        row=lastX;
        col=lastY;
        while (row<n&&col>=0){
            board[row][col]=0;
            row++;
            col--;
        }
        return placed;
    }

}

