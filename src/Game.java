import java.util.*;

public class Game {
    Queue<Integer> whoTurn;
    Stack<Board> boardHistory;
    String p1Name;
    String p2Name;

    /**
     * @param args runs main code to create and allow the
     *             user to play a game of checkers with
     *             another person
     */
    public static void main(String[] args) {
        Game g = new Game();
        g.gameLoop();
    }

    /**
     * Constructs a Game object
     */
    public Game(){
        boardHistory = new Stack<>();
        whoTurn = new LinkedList<>();
        whoTurn.add(1);
        whoTurn.add(2);
    }

    /**
     * gameLoop that will run for the entire duration of the game and will
     * end when a player loses or if a player forfeits
     */
    public void gameLoop(){
        //Game State initialization
        Board curB = new Board();
        Scanner in = new Scanner(System.in);
        println("Filling board");
        curB.fillBoard();
        boolean gameGo = true;

        //Intro and player name assignments
        println("Hello welcome to checkers\nPlease enter player 1 name");
        p1Name = in.nextLine();
        println("Please enter player 2 name");
        p2Name = in.nextLine();

        //Game play/state loop
        while(gameGo){
            String CapOrMov = "move";
            try{
                boardHistory.push(curB.newCopy());
                boardHistory.peek().printBoard();
                int tempR;
                int tempC;

                if(whoTurn.peek()%2 == 0){
                    println(p2Name + "'s turn");
                }
                else {
                    println(p1Name + "'s turn");
                }

                if(curB.canCap()){
                    println("please type capture if you would like to capture otherwise type anything else");
                    String input = in.nextLine();
                    if(input.toLowerCase().equals("capture")){
                        CapOrMov = "capture";
                    }
                }

                if(CapOrMov.equals("move")){
                    println("What row");
                    int r = in.nextInt()-1;
                    if( r>7 || r<0){
                        throw new InputMismatchException();
                    }
                    println("What column");
                    int c = in.nextInt()-1;
                    if(c>7 || c<0){
                        throw new InputMismatchException();
                    }
                    if(whoTurn.peek() == 1 && curB.getPiece(r,c).getVisual() == 'x'){
                        if(curB.isOccupied(r+1,c+1) && curB.isOccupied(r+1, c-1)){
                            throw new IllegalArgumentException();
                        }
                        curB.getPiece(r,c).setVisual('@');
                        curB.printBoard();
                    }
                    else if(whoTurn.peek() == 2 && curB.getPiece(r,c).getVisual() == 'o'){
                        if(curB.isOccupied(r-1,c+1) && curB.isOccupied(r-1, c-1)){
                            throw new IllegalArgumentException();
                        }
                        curB.getPiece(r,c).setVisual('@');
                        curB.printBoard();
                    }
                    else{
                        throw new InputMismatchException();
                    }
                    in.nextLine(); //Gets a fresh line to allow for left/right input

                    if(c-1<=0 || (whoTurn.peek() == 1 && curB.isOccupied(r+1, c-1)) || (whoTurn.peek() == 2 && curB.isOccupied(r-1,c-1))){
                        //goes right because that's the only option
                        if(whoTurn.peek() == 1){
                            curB.play(r,c,r+1,c+1, curB.getPiece(r,c));
                            tempR = r+1;
                            tempC = c+1;
                        }
                        else{
                            curB.play(r,c,r-1,c+1, curB.getPiece(r,c));
                            tempR = r-1;
                            tempC = c+1;
                        }
                    }
                    else if(c+1>=8 || (whoTurn.peek() == 1 && curB.isOccupied(r+1, c+1)) || (whoTurn.peek() == 2 && curB.isOccupied(r-1,c+1))){
                        //goes left because that's the only option
                        if(whoTurn.peek() == 1){
                            curB.play(r,c,r+1,c-1, curB.getPiece(r,c));
                            tempR = r+1;
                            tempC = c-1;
                        }
                        else{
                            curB.play(r,c,r-1,c-1, curB.getPiece(r,c));
                            tempR = r-1;
                            tempC = c-1;
                        }

                    }
                    else{
                        println("Left or Right");
                        String lor = in.nextLine();
                        if(whoTurn.peek() == 1){
                            if(lor.toLowerCase().equals("left")){
                                //go left
                                curB.play(r,c,r+1,c-1, curB.getPiece(r,c));
                                tempR = r+1;
                                tempC = c-1;
                            }
                            else if(lor.toLowerCase().equals("right")){
                                //go right
                                curB.play(r,c,r+1,c+1, curB.getPiece(r,c));
                                tempR = r+1;
                                tempC = c+1;
                            }
                            else {
                                throw new InputMismatchException();
                            }
                        }
                        else{
                            if(lor.toLowerCase().equals("left")){
                                //go left
                                curB.play(r,c,r-1,c-1, curB.getPiece(r,c));
                                tempR = r-1;
                                tempC = c-1;
                            }
                            else if(lor.toLowerCase().equals("right")){
                                //go right
                                curB.play(r,c,r-1,c+1, curB.getPiece(r,c));
                                tempR = r-1;
                                tempC = c+1;
                            }
                            else {
                                throw new InputMismatchException();
                            }
                        }
                    }
                    curB.printBoard();
                    println("Type yes to confirm no to cancel");
                    String yn = in.nextLine();
                    if(yn.toLowerCase().equals("no")){
                        curB = boardHistory.peek();
                        curB.printBoard();
                    }
                    else if (yn.toLowerCase().equals("yes")){
                        if(whoTurn.peek() == 1){
                            curB.getPiece(tempR, tempC).setVisual('x');
                            boardHistory.push(curB.newCopy());
                            boardHistory.peek().printBoard();
                        }
                        else{
                            curB.getPiece(tempR, tempC).setVisual('o');
                            boardHistory.push(curB.newCopy());
                            boardHistory.peek().printBoard();
                        }
                        whoTurn.add(whoTurn.remove());
                    }
                    else{
                        throw new InputMismatchException();
                    }
                }
                else{
                    int captorRow;
                    int captorCol;
                    int captiveRow;
                    int captiveCol;

                    println("what row is your captor");
                    captorRow = in.nextInt() - 1;
                    if( captorRow>7 || captorRow<0){
                        throw new InputMismatchException();
                    }
                    println("what column");
                    captorCol = in.nextInt() - 1;
                    if( captorCol>7 || captorCol<0){
                        throw new InputMismatchException();
                    }
                    println("(find a way to remove me BUT what row is the captive");
                    captiveRow = in.nextInt() - 1;
                    if( captiveRow>7 || captiveRow<0){
                        throw new InputMismatchException();
                    }
                    println("and what column");
                    captiveCol = in.nextInt() - 1;
                    if( captiveCol>7 || captiveCol<0){
                        throw new InputMismatchException();
                    }
                    in.nextLine();
                    curB.capture(curB.getPiece(captorRow, captorCol), captorRow, captorCol, curB.getPiece(captiveRow, captiveCol), captiveRow, captiveCol);
                    whoTurn.add(whoTurn.remove());
                }

            } catch (InputMismatchException e){
                println("Illegal input detected try again");
                  in.nextLine();
            } catch (NullPointerException e){
                println("Empty space try again");
                in.nextLine();
            } catch (IllegalArgumentException e){
                println("This piece has no valid places to move to");
            }
            if(curB.gameOver()){
                gameGo = false;
            }

        }
        String player;
        if(curB.getWinner()){
            player = p2Name;
        }
        else{
            player = p1Name;
        }
        println("Congratulations " + player + " you win!");

        /**
         * while gameGO is true
         * Player 1 will be the top of the board player 2 will be the bottom(or go by visual x can only go down o can only go up)
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
         * Need to confirm curB as the possible play positions r+(-) 1 and r+-1 is empty
         * Need to confirm that the selected piece belongs to that player
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
}
