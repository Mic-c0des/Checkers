public class Board {

    private static final int SIZE = 8;  //Typical checkers board size
    private char[][] curBoard = new char[SIZE][SIZE];

    public Board(){
        for(int r=0; r<2; r++){
            for(int c=0; c<SIZE; c++){
                curBoard[r][c] = 'O';
            }
        }

        for(int r=2; r<6; r++){
            for(int c=0; c<SIZE; c++){
                curBoard[r][c] = ' ';
            }
        }

        for(int r=6; r<SIZE; r++){
            for(int c=0; c<SIZE; c++){
                curBoard[r][c] = 'X';
            }
        }
    }

    public void printBoard(){

        for(int i=0; i<SIZE; i++){
            for(int j=0; j <SIZE; j++){
                if( j != SIZE-1){
                    System.out.print(curBoard[i][j] + "|");
                }
                else{
                    System.out.print(curBoard[i][j]);
                }
            }

            if(i != SIZE-1){
                System.out.println();
                for(int k=0; k<SIZE+SIZE-1; k++){
                    System.out.print("-");
                }
                System.out.println();
            }
            else{
                System.out.println();
            }
        }
    }

    //getColumn

    //getRow

    //Play


}
