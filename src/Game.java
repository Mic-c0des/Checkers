import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    /**
     * @param args runs main code to create and allow the
     *             user to play a game of checkers with
     *             another person
     */
    public static void main(String[] args) {
        System.out.println("Hello initiating test run");
        Game g = new Game();
        g.gameLoop();
    }

    /**
     * Constructs a Game object
     */
    public Game(){

    }

    /**
     * gameLoop that will run for the entire duration of the game and will
     * end when a player loses or if a player forfeits
     */
    public void gameLoop(){
        Board curB = new Board();
        Scanner in = new Scanner(System.in);
        System.out.println("Filling board");
        curB.fillBoard();
        curB.printBoard();

        //print where would you like to play
        //check if that spots occupied
        //run play method
        //Need to find a way to get the piece at said location that way play can be called on it

        println("Welcome to checkers please format input for moving pieces as current row\ncurrent column\ndesired row\ndesired column");
        println("Enter desired pieces current row");
        int curR = in.nextInt();
        println("Enter desired pieces current col");
        int curC = in.nextInt();

        if (curB.isOccupied(curR, curC)) {
            System.out.println("true");
            Piece thisthing = curB.getPiece(curR,curC);
            thisthing.printer();
            curB.getPiece(curR,curC).setVisual('@');
            curB.printBoard();
            System.out.println("");
            //curB.getPiece(curR,curC)
            curB.getPiece(curR,curC).setVisual('X');
            thisthing.play(curB.getPiece(curR,curC),curR+1,curC+1);

            curB.moveSetPiece(thisthing,curR,curC);
            //curB.Clearspot(curR,curC);

            //curB.getPiece(curR,curC).setVisual('X');

            curB.printBoard();

            /*
            ArrayList<Piece> thinghere = curB.getCurARBoardAtSpot(curR, curC);
            if (thinghere.getFirst() instanceof Pawn) {
                Pawn currentthing = (Pawn) thinghere.getFirst();
                currentthing.play(thisthing,3,1);
            }
             //*/
        }

        println("Enter desired move row");
        int moveR = in.nextInt();
        println("Enter desired move col");
        int moveC = in.nextInt();


        if(curB.isOccupied(curR, curC) && !curB.isOccupied(moveR, moveC)){
            curB.getPiece(curR, curC).play(curB.getPiece(curR, curC),moveR, moveC);
        }



        curB.printBoard();
    }


    public void println(String s){
        System.out.println(s);
    }

    public void print(String s){
        System.out.print(s);
    }
}
