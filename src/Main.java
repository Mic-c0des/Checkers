public class Main {
    public static void main(String[] args) {
        Board b = new Board();
        b.printBoard();
        System.out.println("\n");
        System.out.println("Playing 20 to 29");
        b.play(1,8);
        b.printBoard();
    }
}