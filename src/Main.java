package src;

import src.GameBoard.SQUARE;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GameBoard gb = new GameBoard();
        gb.drawBoardLines();

        int turn = 1;
        while (true) {
            gb.drawSubBoards();
            System.out.print("Input Move Here (q to quit): ");
            String move = input.nextLine();
            if (move.equals("q")) {
                input.close();
                System.exit(0);
            }

            // format: "XYZ" where X is the board number (indexed from 1), Y is the square number (indexed from 1)
            int board = Character.getNumericValue(move.charAt(0));
            int square = Character.getNumericValue(move.charAt(1));
            boolean move_result = gb.makeMove(board, square, (turn % 2 == 1) ? SQUARE.PLAYER : SQUARE.COMPUTER);
            if (move_result) {
                turn++;
            }
        }
    }
}
