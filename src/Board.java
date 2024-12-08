import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Stack;

public class Board {
    /**
     * Final int that declares a square shaped boards side length
     */
    private static final int SIZE = 8;  //Typical checkers board size

    /**
     * Creates an empty arraylist that is a square with side lengths of SIZE
     */
    private Piece[][] curBoard = new Piece[SIZE][SIZE];

    /**
     * p1Pieces keeps track of how many pieces player/team 1 has on the board
     * p2Pieces keeps track of how many pieces player/team 1 has on the board
     */
    private Stack<Character> p1Pieces;
    private Stack<Character> p2Pieces;

    public Board(){
        p1Pieces = new Stack<>();
        for(int i = 0; i < (SIZE/2)*3; i++){
            p1Pieces.push('x');
        }
        p2Pieces = new Stack<>();
        for(int i = 0; i < (SIZE/2)*3; i++){
            p1Pieces.push('o');
        }
    }

    public Board(Board other){
        this.p1Pieces = new Stack<>();
        this.p2Pieces = new Stack<>();
        for(Character p1: other.p1Pieces){
            this.p1Pieces.push(p1);
        }
        for(Character p2: other.p2Pieces){
            this.p2Pieces.push(p2);
        }

        this.curBoard = new Piece[other.curBoard.length][other.curBoard[0].length];
        for(int i=0; i<other.curBoard.length; i++){
            for(int j=0; j<other.curBoard[i].length; j++){
                if(other.isOccupied(i,j)){
                    this.curBoard[i][j] = other.curBoard[i][j].selfCopy();
                }
                else{
                    this.curBoard[i][j] = null;
                }
            }
        }
    }

    /**
     * Fills the board with pieces objects
     */
    public void fillBoard(){
        //Team 1
        //row 0,2 _X_X_X_X
        //Row 1   X_X_X_X_
        for(int r = 0; r < (SIZE/2)-1; r++){
            if(r%2 == 0){
                //make a new piece with team 1 row r, col c, and visual X
                //Set curb[r][c] = this.piece.getVisual()
                for(int c = 1; c < SIZE; c+=2){
                    Pawn p = new Pawn(1, 'x');
                    curBoard[r][c] = p;
                    p1Pieces.push('x');
                }
            }
            else{
                for(int c = 0; c < SIZE; c+=2){
                    Pawn p = new Pawn(1, 'x');
                    curBoard[r][c] = p;
                    p1Pieces.push('x');
                }
            }
        }

        //Team 2
        //Row 5,7 X_X_X_X_
        //Row 6   _X_X_X_X
        for(int r = 5; r < SIZE; r++){
            if(r%2 == 1){
                //make a new piece with team 1 row r, col c, and visual X
                //Set curb[r][c] = this.piece.getVisual()
                for(int c = 0; c < SIZE; c+=2){
                    Pawn p = new Pawn(1, 'o');
                    curBoard[r][c] = p;
                    p2Pieces.push('o');
                }
            }
            else{
                for(int c = 1; c < SIZE; c+=2){
                    Pawn p = new Pawn(1, 'o');
                    curBoard[r][c] = p;
                    p2Pieces.push('o');
                }
            }
        }
    }
    /**
     * Prints out SIZE by SIZE board where the top 3 and bottom 3 rows are filled with the players pieces
     */
    public void printBoard(){
        System.out.println();
        for(int i=0; i<SIZE; i++){
            for(int j=0; j <SIZE; j++){
                if( j != SIZE-1 && isOccupied(i,j)) {
                    System.out.print(curBoard[i][j].getVisual() + "|");
                }
                else if( j != SIZE - 1){
                    System.out.print(' ' + "|" );
                }
                else if(isOccupied(i,j)){
                    System.out.print(curBoard[i][j].getVisual());
                }
                else{
                    System.out.print(" ");
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
        System.out.println();
    }

    /**
     * @param location
     * @return the column of the checkerboard at which the piece
     * the method is being called on is located
     */
    public int getColumn(int location){
        return location % 8;
    }

    /**
     * @param location
     * @return the row of the checkerboard at which the piece
     * the method is being called on is located
     */
    public int getRow(int location){
        return location / 8;
    }

    /**
     * @param location to be converted to row and column
     * @return true if the space at row, col is occupied
     * otherwise returns false(as the space is empty)
     */
    public boolean isOccupied(int location){
        return curBoard[getRow(location)][getColumn(location)] == null;
    }

    /**
     * @return piece at row, col
     */
    public Piece getPiece(int row, int col){
        return curBoard[row][col];
    }

    public void play(int oldR, int oldC, int newR, int newC, Piece p){
        if(!isOccupied(newR, newC)){
            curBoard[oldR][oldC] = null;
            curBoard[newR][newC] = p;
        }
    }

    public boolean canCap(){
        //TODO make this so it checks all spots on the board and if a capture is available
        return true;
    }

    public void capture(Piece captor, int capR, int capC, Piece captive, int captiveR, int captiveC) throws InputMismatchException {

        if (captor.getVisual() != captive.getVisual()) {
            if(captor.getVisual() == 'x'){
                if(captiveC > capC){
                    play(capR, capC, capR + 2, captiveC + 1, captor);
                    p2Pieces.pop();
                    curBoard[captiveR][captiveC] = null;
                }
                else{
                    play(capR, capC, capR + 2, captiveC - 1, captor);
                    p2Pieces.pop();
                    curBoard[captiveR][captiveC] = null;
                }
            }
            else if(captor.getVisual() == 'o'){
                if(captiveC > capC){
                    play(capR, capC, capR - 2, capC +2, captor);
                    p1Pieces.pop();
                    curBoard[captiveR][captiveC] = null;
                }
                else{
                    play(capR, capC, capR - 2, captiveC - 1, captor);
                    p1Pieces.pop();
                    curBoard[captiveR][captiveC] = null;
                }
            }

        } else {
            throw new InputMismatchException();
        }
    }

    /**
     * @param r row to be checked
     * @param c column to be checked
     * @return true if the space is filled by a piece object
     */
    public boolean isOccupied(int r, int c){
        if(c == 8 || c == -1){
            return false;
        }
        return curBoard[r][c] != null;
    }

    public Board newCopy(){
        return new Board(this);
    }

    /**
     * @return True and will end gameLoop if one of the teams is out of pieces
     */
    public boolean gameOver(){
        return p1Pieces.isEmpty() || p2Pieces.isEmpty();
    }

    /**
     * @returns true if player 2 wins and false is player 1 won
     */
    public boolean getWinner(){
        if(p1Pieces.isEmpty()){
            return true;
        }
        return false;
    }
}
