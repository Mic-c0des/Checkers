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
    private final int team;
    private int row, col;
    /**
     * char that represents the piece on the printed board
     */
    private char visual;

    /**
     * @param team 1 will indicate the team at the top of the board, 2 will indicate the team at the bottom
     * @param row
     * @param col
     * @param visual
     */
    public Piece(int team,int row,int col, char visual){
        this.team = team;
        this.row = row;
        this.col = col;
        this.visual = visual;
    }

    /**
     * Attempts to move a players piece from position x to y
     * @throws IndexOutOfBoundsException of location is invalid or already occupied
     */
    public abstract void play(Piece p, int x, int y) throws IndexOutOfBoundsException;

    /**
     * try to capture the piece and move diagonal two spaces
     * catch IndexOutOfBounds error and instead do a sideways V (this is when you are capturing on the edges of the board)
     *if the piece CAN capture then it should move two spaces, the captured piece should disappear and that players piece count should decrease by 1
     */
    public abstract void capture(Piece captor, Piece captive, int nRow, int nCol) throws IndexOutOfBoundsException;

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
     * @return the row of this piece
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row is the new row for this piece
     */
    public void setRow(int row){
        this.row = row;
    }

    /**
     * @return the column of this piece
     */
    public int getCol() {
        return col;
    }

    /**
     * @param col is the new column for this piece
     */
    public void setCol(int col){
        this.col = col;
    }

    /**
     * Piece is to be deep copied
     * requires deep copy because piece
     * is a mutable object
     */
    public abstract Piece selfCopy();
}
