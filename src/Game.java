import java.util.*;

public class Game {
    Queue<Integer> whoTurn;

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
        whoTurn = new LinkedList<>();
        whoTurn.add(1);
        whoTurn.add(2);
    }

    /**
     * gameLoop that will run for the entire duration of the game and will
     * end when a player loses or if a player forfeits
     */
    public void gameLoop(){
        Board curB = new Board();
        Scanner in = new Scanner(System.in);
        println("Filling board");
        curB.fillBoard();
        curB.printBoard();
        boolean gameGo = false;

        println(whoTurn);

        /**
         * while gameGO is true
         * Player 1 will be the top of the board player 2 will be the bottom(or go by visual X can only go down O can only go up)
         * ask for the row and col of the piece the player wants to move or if they want to forfeit
         * TRY
         *
         * peak the whoTurn queue to see who is allowed to play
         *
         * when a play is completed remove that index of the queue and add that same number to the end
         *Essentially the Queue will rotate [1,2] [2,1] until the game is over
         *
         * if row is 0,1,2 we are moving row + 1
         * if row is 5,6,7 we are moving row - 1
         *
         * for both
         *
         * if column-1<0 Must move right
         * else if column+1>7 Must move left
         * else ask for left or right
         * if left then go left if right then go right
         * if something other that L/left or R/right OR the spot the player is trying to move to is occupied ask the user to try again
         *
         */

    }


    public void println(Object s){
        System.out.println(s);
    }

    public void print(Object s){
        System.out.print(s);
    }
}
