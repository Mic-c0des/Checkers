public class Pawn extends Piece{
    /**
     * @param team represent the team number as an int
     * @param row represent the row of the piece on the board
     * @param col represent the column of the piece on the board
     * @param visual represent the letter to be used on the board
     */
    public Pawn(int team,int row,int col, char visual){
        super(team,row,col,visual);
    }

    /**
     * Attempts to move a players piece from position x to y
     * @param nRow
     * @param nCol
     * @throws IndexOutOfBoundsException of location is invalid or already occupied
     */

    @Override
    public void play(Piece p, int nRow, int nCol) throws IndexOutOfBoundsException {
        int tempR = p.getRow();
        int tempC = p.getCol();

        //if team 1 then nRow is p.getRow + 1 and nCol is nCol = p.getCol() + or - 1

        if(p.getTeam() == 1 && (tempR + 1) == nRow || ((tempC + 1) == nCol || (tempC - 1) == nCol)) {
            p.setRow(nRow);
            this.setRow(nRow);
            p.setCol(nCol);
            this.setCol(nCol);
        }
        else if(p.getTeam() == 2 && (tempR - 1) == nRow || ((tempC + 1) == nCol || (tempC - 1) == nCol)) {
            p.setRow(nRow);
            this.setRow(nRow);
            p.setCol(nCol);
            this.setCol(nCol);
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * try to capture the piece and move diagonal two spaces
     * catch IndexOutOfBounds error and instead do a sideways V (this is when you are capturing on the edges of the board)
     * if the piece CAN capture then it should move two spaces, the captured piece should disappear and that players piece count should decrease by 1
     *
     * @param captor
     * @param captive
     */
    @Override
    public void capture(Piece captor, Piece captive, int nRow, int nCol) throws IndexOutOfBoundsException {
        int tempR = captor.getRow();
        int tempC = captor.getCol();

        //if team 1 then nRow is p.getRow + 1 and nCol is nCol = p.getCol() + or - 1

        if(captor.getTeam() == 1 && (tempR + 1) == nRow || ((tempC +1) == nCol || (tempC - 1) == nCol)) {
            captor.setRow(nRow);
            captor.setCol(nCol);
        }
//        else if(p.getTeam() == 2 && (tempR - 1) == nRow || ((tempC +1) == nCol || (tempC - 1) == nCol)) {
//            p.setRow(nRow);
//            p.setCol(nCol);
//        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * implement and override necessary methods form the class piece to work specifically for a pawn
     */
    @Override
    public Pawn selfCopy(){
        return new Pawn(this.getTeam(), this.getRow(), this.getCol(), this.getVisual());
    }
}
