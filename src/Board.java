public class Board {

    private static final int SIZE = 8;  //Typical checkers board size
    private char[][] curBoard = new char[SIZE][SIZE];

    private int p1Pieces = SIZE*2;
    private int p2Pieces = SIZE*2;

    public Board(){


    }

    /**
     * Prints out SIZE by SIZE board where the top 2 and bottom 2 rows are filled with the players pieces
     */
    public void printBoard(){

    }

    public int getColumn(int location){
        return 0;
    }

    public int getRow(int location){
        return 0;
    }

    public boolean isOccupied(int row, int col){
        return false;
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

    }

    /**
     * try to capture the piece and move diagonal two spaces
     * catch IndexOutOfBounds error and instead do a sideways V (this is when you are capturing on the edges of the board)
     *if the piece CAN capture then it should move two spaces, the captured piece should disappear and that players piece count should decrease by 1
     */
    public void capture(int captor, int captive){

    }

    public boolean gameOver(){
        return p1Pieces == 0 || p2Pieces == 0;
    }
}
