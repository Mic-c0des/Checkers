/**
 * Abstract class to make a piece and allow for different pieces with different rules exist
 */
public abstract class Piece {

    /**
     * Int that represents what team the piece is on.
     * In this case it will be 1 or 2
     */
    private int team;
    /**
     * char that represents the piece on the printed board
     */
    private char visual;

    /**
     * @param team 1 will indicate the team at the top of the board, 2 will indicate the team at the bottom
     * @param visual char that represents the piece on the board
     */
    public Piece(int team, char visual){
        this.team = team;
        this.visual = visual;
    }

    /**
     * @returns what team this piece is on
     */
    public int getTeam(){
        return team;
    }

    /**
     * @param t new team for this piece to join
     */
    public void setTeam(int t){
        team = t;
    }

    /**
     * @returns what character represents this piece takes eg. X or O
     */
    public char getVisual(){
        return visual;
    }

    /**
     * @param c new character for this piece to take x,o for pawn X,O for kings
     */
    public void setVisual(char c){
        visual = c;
    }

    /**
     * Piece is to be deep copied
     * requires deep copy because piece
     * is a mutable object
     */
    public abstract Piece selfCopy();
}
