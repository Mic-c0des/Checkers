public class Pawn extends Piece{
    /**
     * @param team represent the team number as an int
     * @param visual represent the letter to be used on the board x or o
     */
    public Pawn(int team, char visual){
        super(team,visual);
    }

    /**
     * implement and override necessary methods form the class piece to work specifically for a pawn
     */
    @Override
    public Pawn selfCopy(){
        return new Pawn(this.getTeam(), this.getVisual());
    }
}
