/**
 * @author PAYNEMD24@gcc.edu
 * @author SATCHELLMR23@gcc.edu
 */
import java.util.*;

public class Game {
    Queue<Integer> whoTurn;
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

        //boardHistory.push(curB.newCopy());

        boolean wantCapture = true;

        while(gameGo){
            int newR;
            int newC;
            curB.printBoard();

            if(whoTurn.peek()%2 == 0){
                println(p2Name + "'s turn");
            }
            else {
                println(p1Name + "'s turn");
            }

            try{
                int r;
                int c;
                println("What row");
                r = in.nextInt()-1;

                if( r>7 || r<0){
                    throw new InputMismatchException();
                }
                println("What column");
                c = in.nextInt()-1;
                if(c>7 || c<0){
                    throw new InputMismatchException();
                }
                in.nextLine();

                if(curB.canCap(r,c) && wantCapture){
                    println("please type capture if you would like to capture otherwise type anything else");
                    String input = in.nextLine();
                    if(input.toLowerCase().equals("capture")){

                        //TODO find a way for the game to figure out where the captive is and set these vars to that
                        println("captive row");
                        int captiveRow = in.nextInt()-1;
                        println("captive column");
                        int captiveCol = in.nextInt()-1;
                        //TODO

                        in.nextLine();
                        curB.capture(curB.getPiece(r, c), curB.getPiece(captiveRow, captiveCol));
                        curB.printBoard();
                        whoTurn.add(whoTurn.remove());
                    }
                    else{
                        wantCapture = false;
                        continue;
                    }
                }
                else if(!curB.canCap(r,c) || !wantCapture){

                    //Determine if piece is going up or down
                    if(curB.getPiece(r,c) instanceof King){
                        println("Up or down");
                        String UD = in.nextLine().toLowerCase();
                        if(curB.getPiece(r,c).getRow() == 7){
                            newR = r - 1;
                        }
                        else if(curB.getPiece(r,c).getRow() == 0){
                            newR = r + 1;
                        }
                        else if(UD.equals("up")){
                            newR = r - 1;
                        }
                        else if(UD.equals("down")){
                            newR = r + 1;
                        }
                        else{
                            throw new IllegalArgumentException();
                        }
                    } else if(whoTurn.peek() == 1 && curB.getPiece(r,c).getTeam() == 1){
                        newR = r + 1;
                    } else if(whoTurn.peek() == 2 && curB.getPiece(r,c).getTeam() == 2){
                        newR = r - 1;
                    }
                    else{
                        throw new InputMismatchException();
                    }

                    //Determine if piece is going left or right
                    if(c == 7){
                        newC = c - 1;
                    } else if(c == 0){
                        newC = c + 1;
                    } else{
                        println("Left or right");
                        String LR = in.nextLine().toLowerCase();
                        if(LR.equals("left")){
                            newC = c - 1;
                        } else if(LR.equals("right")){
                            newC = c + 1;
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }

                    curB.play(curB.getPiece(r,c), newR, newC);
                    curB.printBoard();
                    whoTurn.add(whoTurn.remove());
                }

                curB.switchType();

            } catch (InputMismatchException e){
                println("Illegal input detected try again");
                in.nextLine();

            } catch (NullPointerException e){
                println("Empty space try again");
            } catch (IllegalArgumentException e){
                println("Illegal input detected");
            }
            if(curB.gameOver()){
                gameGo = false;
            }
            wantCapture = true;
        }

        String player;
        if(curB.getWinner()){
            player = p2Name;
        }
        else{
            player = p1Name;
        }
        println("Congratulations " + player + " you win!");
    }

    /**
     * @param s to be printed
     *          shortcut for
     *          System.out.println();
     */
    public void println(Object s){
        System.out.println(s);
    }
}
