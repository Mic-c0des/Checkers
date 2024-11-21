/**
 *
 */
public abstract class Piece {


    /**
     * String that represents what team the piece is on.
     * In this case it will be 1 or 2
     * X will record the X position of the game piece for the board
     * Y will record the Y position of the game piece for the board
     */
    private int team, X, Y ;
    /**
     * char that represents the piece on the printed board
     */
    private char visual;

    public Piece(int team,int x,int y, char visual){
        this.team = team;
        this.visual = visual;
        this.X = x;
        this.Y = y;
    }



    public int getTeam(){
        return team;
    }

    public char getVisual(){
        return visual;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void copy(Piece other){

    }
}
