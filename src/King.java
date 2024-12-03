public class King extends Piece{

    /**
     * @param team represent the team number as an int
     * @param row represent the row of the piece on the board
     * @param col represent the column of the piece on the board
     * @param visual represent the letter to be used on the board
     */
    public King(int team,int row,int col, char visual){
        super(team,row,col,visual);
    }

    /**
     * @param nRow
     * @param nCol
     */
    @Override
    public void play(Piece p, int nRow, int nCol) throws IndexOutOfBoundsException {
        int tempR = p.getRow();
        int tempC = p.getCol();

        //if team 1 then nRow is p.getRow + 1 and nCol is nCol = p.getCol() + or - 1
        //Check if the new space is occupied in the board or gameLoop method before allowing play to go

        if(((tempR + 1) == nRow || (tempR - 1) == nRow) && ((tempC + 1) == nCol || (tempC - 1) == nCol)) {
            p.setRow(nRow);
            p.setCol(nCol);
        }
        else{
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * @param captor
     * @param captive
     */
    @Override
    public void capture(Piece captor, Piece captive, int nRow, int nCol) throws IndexOutOfBoundsException {

    }

    /**
     * implement and override necessary methods form the class piece to work specifically for a king
     */
    @Override
    public King selfCopy(){
        return new King(this.getTeam(), this.getRow(), this.getCol(), this.getVisual());
    }

}
