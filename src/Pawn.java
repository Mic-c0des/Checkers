/**
 * @author PAYNEMD24@gcc.edu
 * @author SATCHELLMR23@gcc.edu
 */
public class Pawn extends Piece{
    /**
     * @param team represent the team number as an int
     * @param visual represent the letter to be used on the board x or o
     */
    public Pawn(int team, int row, int col, char visual){
        super(team,row, col, visual);
    }

    public Pawn(Pawn other){
        super(other);
    }

    //MAKE getters and setters for row and col  in piece, pawn and king classes

    /**
     * implement and override necessary methods form the class piece to work specifically for a pawn
     */
    @Override
    public Pawn selfCopy(){
        return new Pawn(this);
    }
}
