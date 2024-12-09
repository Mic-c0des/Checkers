/**
 * @author PAYNEMD24@gcc.edu
 * @author SATCHELLMR23@gcc.edu
 */
import java.util.ArrayList;
import java.util.InputMismatchException;

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
    private ArrayList<Piece> p1Pieces;
    private ArrayList<Piece> p2Pieces;

    public Board(){
        p1Pieces = new ArrayList<>();
        p2Pieces = new ArrayList<>();

    }

    public Board(Board other){
        this.p1Pieces = new ArrayList<>();
        this.p2Pieces = new ArrayList<>();
        for(Piece p1: other.p1Pieces){
            this.p1Pieces.add(p1.selfCopy());
        }
        for(Piece p2: other.p2Pieces){
            this.p2Pieces.add(p2.selfCopy());
        }

        this.curBoard = new Piece[SIZE][SIZE];
        for(int r=0; r<SIZE; r++){
            for(int c=0; c<SIZE; c++){
                if(other.isOccupied(r,c)){
                    this.curBoard[r][c] = other.curBoard[r][c].selfCopy();
                }
                else{
                    this.curBoard[r][c] = null;
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
                    Pawn p = new Pawn(1,r, c, 'x');
                    curBoard[r][c] = p;
                    p1Pieces.add(p);
                }
            }
            else{
                for(int c = 0; c < SIZE; c+=2){
                    Pawn p = new Pawn(1,r, c, 'x');
                    curBoard[r][c] = p;
                    p1Pieces.add(p);
                }
            }
        }

        //Team 2
        //Row 5,7 X_X_X_X_
        //Row 6   _X_X_X_X
        for(int r = 5; r < SIZE; r++){
            if(r%2 == 1){
                //make a new piece with team 2 row r, col c, and visual X
                //Set curb[r][c] = this.piece.getVisual()
                for(int c = 0; c < SIZE; c+=2){
                    Pawn p = new Pawn(2,r, c, 'o');
                    curBoard[r][c] = p;
                    p2Pieces.add(p);
                }
            }
            else{
                for(int c = 1; c < SIZE; c+=2){
                    Pawn p = new Pawn(2,r, c, 'o');
                    curBoard[r][c] = p;
                    p2Pieces.add(p);
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
     * @return piece at row, col
     */
    public Piece getPiece(int row, int col){
        return curBoard[row][col];
    }

    public void play(Piece p, int newRow, int newCol){
        Piece oldP = p.selfCopy();
        int oldR = oldP.getRow();
        int oldC = oldP.getCol();
        if(!isOccupied(newRow, newCol)){
            curBoard[oldR][oldC] = null;
            curBoard[newRow][newCol] = p.selfCopy();
        } else {
            throw new InputMismatchException();
        }
    }

    public boolean canCap(){
        for(int r=0; r<SIZE; r++){
            for(int c=0; c<SIZE; c++){
                try{
                    if(isOccupied(r,c)){
                        char capV = curBoard[r][c].getVisual();

                        boolean topLeft = isOccupied(r-1,c-1);
                        boolean behindTL = !isOccupied(r-2,c-2);
                        boolean topRight = isOccupied(r-1,c+1);
                        boolean behindTR = !isOccupied(r-2,c+2);
                        boolean bottomLeft = isOccupied(r+1,c-1);
                        boolean behindBL = !isOccupied(r+2,c-2);
                        boolean bottomRight = isOccupied(r+1,c+1);
                        boolean behindBR = !isOccupied(r+2,c+2);

                        if(curBoard[r][c] instanceof King){
                            if((behindTL && topLeft && capV != curBoard[r-1][c-1].getVisual())||(behindTR && topRight && capV != curBoard[r-1][c+1].getVisual())||(behindBL && bottomLeft && capV != curBoard[r+1][c-1].getVisual())||(behindBR && bottomRight && capV != curBoard[r+1][c+1].getVisual())){
                                return true;
                            }
                        }
                        else if(curBoard[r][c].getTeam() == 1){
                            if((behindBL && bottomLeft && capV != curBoard[r+1][c-1].getVisual())||(behindBR && bottomRight && capV != curBoard[r+1][c+1].getVisual())){
                                return true;
                            }
                        }
                        else if(curBoard[r][c].getTeam() == 2){
                            if((behindTL && topLeft && capV != curBoard[r-1][c-1].getVisual())||(behindTR && topRight && capV != curBoard[r-1][c+1].getVisual())){
                                return true;
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }
            }
        }
        return false;
    }

    public void capture(Piece captor, Piece captive){

        int capC = captor.getCol();
        int captiveR = captive.getRow();
        int captiveC = captive.getCol();

        if (captor.getTeam() != captive.getTeam()) {
            if(captor.getVisual() == 'x'){
                if(captiveC > capC){
                    p2Pieces.remove(captive);
                    curBoard[captiveR][captiveC] = null;
                    play(captor, captiveR + 1, captiveC + 1);
                }
                else{
                    p2Pieces.remove(captive);
                    curBoard[captiveR][captiveC] = null;
                    play(captor, captiveR + 1, captiveC - 1);
                }
            }
            else if(captor.getVisual() == 'o'){
                if(captiveC > capC){
                    p1Pieces.remove(captive);
                    curBoard[captiveR][captiveC] = null;
                    play(captor, captiveR + 1, captiveC + 1);
                }
                else{
                    p1Pieces.remove(captive);
                    curBoard[captiveR][captiveC] = null;
                    play(captor, captiveR + 1, captiveC - 1);
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
