/**
 * Abstract class to make a piece and allow for different pieces with different rules exist
 */
public abstract class Piece {

    /**
     * String that represents what team the piece is on.
     * In this case it will be 1 or 2
     * X will record the X position of the game piece for the board
     * Y will record the Y position of the game piece for the board
     */
    private int team, x, y ;
    /**
     * char that represents the piece on the printed board
     */
    private char visual;

    public Piece(int team,int x,int y, char visual){
        this.team = team;
        this.visual = visual;
        this.x = x;
        this.y = y;
    }


    /**
     * Attempts to move a players piece from position x to y
     * @throws IndexOutOfBoundsException of location is invalid or already occupied
     */
    public void play(int x, int y){
        //TODO: make it work
    }

    /**
     * try to capture the piece and move diagonal two spaces
     * catch IndexOutOfBounds error and instead do a sideways V (this is when you are capturing on the edges of the board)
     *if the piece CAN capture then it should move two spaces, the captured piece should disappear and that players piece count should decrease by 1
     */
    public void capture(int captor, int captive){
        //TODO: make it work
    }

    /**
     * @returns what team this piece is on
     */
    public int getTeam(){
        return team;
    }

    /**
     * @returns what character represents this piece takes eg. X or O
     */
    public char getVisual(){
        return visual;
    }

    /**
     * @return the row of the piece
     */
    public int getX() {
        return x;
    }

    /**
     * @param x is the new x for this piece
     */
    public void setX(int x){

    }

    public int getY() {
        return y;
    }

    /**
     * @param other
     */
    public void copy(Piece other){

    }
}
