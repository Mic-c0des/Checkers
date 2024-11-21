import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board b = new Board();
        b.printBoard();
        System.out.println("\n");
        System.out.println("Playing 20 to 29");


        //temp test stuff
        Scanner in = new Scanner(System.in);
        int strt;
        int goal;
        System.out.println("Please enter start position");
        strt = in.nextInt();
        System.out.println("Please enter goal position");
        goal = in.nextInt();
        b.play(strt,goal);
        b.printBoard();
    }
}