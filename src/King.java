/**
 * @author PAYNEMD24@gcc.edu
 * @author SATCHELLMR23@gcc.edu
 */
public class King extends Piece{

    /**
     * @param team represent the team number as an int
     * @param visual represent the letter to be used on the board X or O
     */
    public King(int team, int row, int col, char visual){
        super(team,row, col, visual);
    }

    public King(King other){
        super(other);
    }

    /**
     * implement and override necessary methods form the class piece to work specifically for a king
     */
    @Override
    public King selfCopy(){
        return new King(this);
    }
}
