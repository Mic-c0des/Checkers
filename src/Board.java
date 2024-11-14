public class Board {

    private static final int SIZE = 8;  //Typical checkers board size
    private char[][] curBoard = new char[SIZE][SIZE];

    private int p1Pieces = SIZE*2;
    private int p2Pieces = SIZE*2;

    public Board(){
        //Sets up O
        for(int r=0; r<3; r++){
            if(r!=1){
                for(int c=1; c<SIZE; c+=2){
                    curBoard[r][c] = 'O';
                }
            }
            else{
                for(int c=0; c<SIZE; c+=2){
                    curBoard[r][c] = 'O';
                }
            }
        }

        //Sets up X
        for(int r=5; r<SIZE; r++){
            if(r!=6){
                for(int c=1; c<SIZE; c+=2){
                    curBoard[r][c] = 'X';
                }
            }
            else{
                for(int c=0; c<SIZE; c+=2){
                    curBoard[r][c] = 'X';
                }
            }
        }

        for(int r=0; r<SIZE; r++){
            for(int c=0; c<SIZE; c++){
                if(isOccupied(r, c)){
                   continue;
                }
                else{
                    curBoard[r][c] = ' ';
                }
            }
        }
    }

    /**
     * Prints out SIZE by SIZE board where the top 2 and bottom 2 rows are filled with the players pieces
     */
    public void printBoard(){

        for(int i=0; i<SIZE; i++){
            for(int j=0; j <SIZE; j++){
                if( j != SIZE-1){
                    System.out.print(curBoard[i][j] + "|");
                }
                else{
                    System.out.print(curBoard[i][j]);
                }
            }

            if(i != SIZE-1){
                System.out.println();
                for(int k=0; k<SIZE+SIZE-1; k++){
                    System.out.print("-");
                }
                System.out.println();
            }
            else{
                System.out.println();
            }
        }
    }

    public int getColumn(int location){
        return (location-1) % SIZE;
    }

    public int getRow(int location){
        return (location-1) / SIZE;
    }

    public boolean isOccupied(int row, int col){
        return curBoard[row][col] == 'X' || curBoard[row][col] == 'O';
    }

    /**
     * Moves players piece from position x to y
     * @throws IndexOutOfBoundsException of location is invalid or already occupied
     */
    public void play(int x, int y){
        //Check if the player occupies x
        //If yes then move to optional
        //Y should be r + 1 for bottom and c +- 1 OR should be r-1 for top player and c +- 1
        //if y is empty make x = ' '  and y = X/O
        int oldR = getRow(x);
        int oldC = getColumn(x);

        int newR = getRow(y);
        int newC = getColumn(y);

        if(isOccupied(oldR, oldC)){
            if(curBoard[oldR][oldC] == 'O'){
                if(newR == (oldR+1) && newC == (oldC + 1) || newC == (oldC - 1)){
                    curBoard[oldR][oldC] = ' ';
                    curBoard[newR][newC] = 'O';
                }
            }
            else if(curBoard[oldR][oldC] == 'X'){
                if(newR == (oldR-1) && newC == (oldC + 1) || newC == (oldC - 1)){
                    curBoard[oldR][oldC] = ' ';
                    curBoard[newR][newC] = 'X';
                }
            }
        }
    }
}
