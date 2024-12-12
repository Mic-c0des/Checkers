/**
 * @author PAYNEMD24@gcc.edu
 * @author SATCHELLMR23@gcc.edu
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    /**
     * ArrayList of each player's teams
     */
    public Board(){
        p1Pieces = new ArrayList<>();
        p2Pieces = new ArrayList<>();

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
        try{
            return curBoard[row][col];
        } catch (ArrayIndexOutOfBoundsException e){
            throw new InputMismatchException();
        }
    }

    /**
     * @param p piece to be moved
     * @param newRow row to be moved to
     * @param newCol column to be moved to
     */
    public void play(Piece p, int newRow, int newCol){
        Piece oldP = p.selfCopy();
        int oldR = oldP.getRow();
        int oldC = oldP.getCol();
        if(!isOccupied(newRow, newCol)){
            curBoard[oldR][oldC] = null;
            curBoard[newRow][newCol] = p;
            p.setRow(newRow);
            p.setCol(newCol);
        } else {
            throw new InputMismatchException();
        }
    }

    /**
     * @param r row of selected piece
     * @param c column of selected piece
     * @return true if the piece at row and column
     */
    public boolean canCap(int r,int c){
        if(isOccupied(r,c)){
            int capT = curBoard[r][c].getTeam();

            boolean topLeft = false;
            boolean behindTL = false;
            boolean topRight = false;
            boolean behindTR = false;
            boolean bottomLeft = false;
            boolean behindBL = false;
            boolean bottomRight = false;
            boolean behindBR = false;
            try{
                topLeft = isOccupied(r-1,c-1);
            } catch (ArrayIndexOutOfBoundsException e){}
            try{
                behindTL = !isOccupied(r-2,c-2);
            }catch (ArrayIndexOutOfBoundsException e){}
            try{
                topRight = isOccupied(r-1,c+1);
            }catch (ArrayIndexOutOfBoundsException e){}
            try{
                behindTR = !isOccupied(r-2,c+2);
            }catch (ArrayIndexOutOfBoundsException e){}
            try{
                bottomLeft = isOccupied(r+1,c-1);
            }catch (ArrayIndexOutOfBoundsException e){}
            try{
                behindBL = !isOccupied(r+2,c-2);
            }catch (ArrayIndexOutOfBoundsException e){}
            try{
                bottomRight = isOccupied(r+1,c+1);
            }catch (ArrayIndexOutOfBoundsException e){}
            try{
                behindBR = !isOccupied(r+2,c+2);
            }catch (ArrayIndexOutOfBoundsException e){}

            if(curBoard[r][c] instanceof King){
                try{
                    if((behindTL && topLeft && capT != curBoard[r-1][c-1].getTeam()))
                        return true;
                } catch (ArrayIndexOutOfBoundsException ignored){
                }
                try{
                    if (behindTR && topRight && capT != curBoard[r-1][c+1].getTeam())
                        return true;
                } catch (ArrayIndexOutOfBoundsException ignored){
                }
                try{
                    if (behindBL && bottomLeft && capT != curBoard[r+1][c-1].getTeam())
                        return true;
                } catch (ArrayIndexOutOfBoundsException ignored){
                }
                try{
                    if (behindBR && bottomRight && capT != curBoard[r+1][c+1].getTeam())
                        return true;
                } catch (ArrayIndexOutOfBoundsException ignored){
                }
            }
            else if(curBoard[r][c].getTeam() == 1){
                try{
                    if (behindBL && bottomLeft && capT != curBoard[r+1][c-1].getTeam())
                        return true;
                } catch (ArrayIndexOutOfBoundsException ignored){}
                try{
                    if (behindBR && bottomRight && capT != curBoard[r+1][c+1].getTeam())
                        return true;
                } catch (ArrayIndexOutOfBoundsException ignored){}
            }
            else if(curBoard[r][c].getTeam() == 2){
                try{
                    if((behindTL && topLeft && capT != curBoard[r-1][c-1].getTeam()))
                        return true;
                } catch (ArrayIndexOutOfBoundsException ignored){
                }
                try{
                    if (behindTR && topRight && capT != curBoard[r-1][c+1].getTeam())
                        return true;
                } catch (ArrayIndexOutOfBoundsException ignored){}
            }
        }
    return false;
}

    /**
     * @param captor piece capturing
     * @param captive piece to be captured
     *                attempts to move captor over captive
     *                and remove captive from its teams list
     *                of pieces
     */
    public void capture(Piece captor, Piece captive){
        Scanner in = new Scanner(System.in);
        String UD;
        int capC = captor.getCol();
        int captiveR = captive.getRow();
        int captiveC = captive.getCol();

        int moveToR;
        int moveToC;

        if (captor.getTeam() != captive.getTeam()) {
            if(captor instanceof King){
                System.out.println("Up or down");
                UD = in.nextLine();
                if(captor.getRow() == 7){
                    moveToR = captiveR - 1;
                }
                else if(captor.getRow() == 0){
                    moveToR = captiveR + 1;
                }
                else if(UD.equals("up")){
                    moveToR = captiveR - 1;
                }
                else if(UD.equals("down")){
                    moveToR = captiveR + 1;
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
            else if(captor.getTeam() == 1){
                moveToR = captiveR + 1;
            }
            else if(captor.getTeam() == 2){
                moveToR = captiveR - 1;
            }
            else{
                throw new InputMismatchException();
            }

            if(capC > captiveC){
                moveToC = captiveC - 1;
            }
            else{
                moveToC = captiveC + 1;
            }
            play(captor, moveToR, moveToC);

            if(captor.getTeam() == 1){
                p2Pieces.remove(captive);
            }
            else if(captor.getTeam() == 2){
                p1Pieces.remove(captive);
            }
            curBoard[captiveR][captiveC] = null;

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

    /**
     * Searches through the arraylist of pieces and converts any pawn on the opposite end of the board to a king
     */
    public void switchType(){
        for(int i = 0; i < p1Pieces.size(); i++){
            if(p1Pieces.get(i).getRow() == 7 && p1Pieces.get(i) instanceof Pawn){
                King pK = new King(p1Pieces.get(i).getTeam(), p1Pieces.get(i).getRow(), p1Pieces.get(i).getCol(), 'X');
                p1Pieces.add(pK);
                p1Pieces.remove(p1Pieces.get(i));
                curBoard[pK.getRow()][pK.getCol()] = pK;
            }
        }
        for(int i = 0; i < p2Pieces.size(); i++){
            if(p2Pieces.get(i).getRow() == 0 && p2Pieces.get(i) instanceof Pawn){
                King pK = new King(p2Pieces.get(i).getTeam(), p2Pieces.get(i).getRow(), p2Pieces.get(i).getCol(), 'O');
                p1Pieces.add(pK);
                p1Pieces.remove(p2Pieces.get(i));
                curBoard[pK.getRow()][pK.getCol()] = pK;
            }
        }
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
