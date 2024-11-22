public class Board {
    /**
     * Final int that declares a square shaped boards side length
     */
    private static final int SIZE = 8;  //Typical checkers board size

    /**
     * Creates an empty arraylist that is a square with side lengths of SIZE
     */
    private char[][] curBoard = new char[SIZE][SIZE];

    /**
     * p1Pieces keeps track of how many pieces player/team 1 has on the board
     * p2Pieces keeps track of how many pieces player/team 1 has on the board
     */
    private int p1Pieces;
    private int p2Pieces;

    public Board(){
    }

    /**
     * Prints out SIZE by SIZE board where the top 2 and bottom 2 rows are filled with the players pieces
     */
    public void printBoard(){

    }

    /**
     * @param location
     * @returns the column of the checker board at which the piece
     * the method is being called on is located
     */
    public int getColumn(int location){
        return 0;
    }

    /**
     * @param location
     * @returns the row of the checker board at which the piece
     * the method is being called on is located
     */
    public int getRow(int location){
        return 0;
    }

    /**
     * @param row
     * @param col
     * @returns true if the space at row, col is occupied
     * otherwise returns false(as the space is empty)
     */
    public boolean isOccupied(int row, int col){
        return false;
    }

    /**
     * @returns True and will end gameLoop if one of the teams is out of pieces
     */
    public boolean gameOver(){
        return p1Pieces == 0 || p2Pieces == 0;
    }
}
