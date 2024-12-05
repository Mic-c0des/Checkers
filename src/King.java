public class King extends Piece{

    /**
     * @param team represent the team number as an int
     * @param visual represent the letter to be used on the board X or O
     */
    public King(int team, char visual){
        super(team,visual);
    }

    /**
     * implement and override necessary methods form the class piece to work specifically for a king
     */
    @Override
    public King selfCopy(){
        return new King(this.getTeam(), this.getVisual());
    }

}
