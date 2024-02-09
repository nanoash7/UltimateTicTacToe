package src;

public class GameBoard {

    private SubBoard[][] game_board;

    public enum SQUARE {
        PLAYER,
        COMPUTER,
        NONE;
    }

    private class SubBoard {
        SQUARE[][] sub_board;
        public SubBoard() {
            sub_board = new SQUARE[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sub_board[i][j] = SQUARE.NONE;
                }
            }
        }

        public SQUARE getWinner() {
            if (sub_board[0][0] == SQUARE.PLAYER && sub_board[0][0] == sub_board[0][1] && sub_board[0][0] == sub_board[0][2] ||
                sub_board[1][0] == SQUARE.PLAYER && sub_board[1][0] == sub_board[1][1] && sub_board[1][0] == sub_board[1][2] ||
                sub_board[2][0] == SQUARE.PLAYER && sub_board[2][0] == sub_board[2][1] && sub_board[2][0] == sub_board[2][2] ||
                sub_board[0][0] == SQUARE.PLAYER && sub_board[0][0] == sub_board[1][0] && sub_board[0][0] == sub_board[2][0] ||
                sub_board[0][1] == SQUARE.PLAYER && sub_board[0][1] == sub_board[1][1] && sub_board[0][1] == sub_board[2][1] ||
                sub_board[0][2] == SQUARE.PLAYER && sub_board[0][2] == sub_board[1][2] && sub_board[0][2] == sub_board[2][2] ||
                sub_board[0][0] == SQUARE.PLAYER && sub_board[0][0] == sub_board[1][1] && sub_board[0][0] == sub_board[2][2] ||
                sub_board[2][0] == SQUARE.PLAYER && sub_board[2][0] == sub_board[1][1] && sub_board[2][0] == sub_board[0][2]) {
                return SQUARE.PLAYER;
            } else if (sub_board[0][0] == SQUARE.COMPUTER && sub_board[0][0] == sub_board[0][1] && sub_board[0][0] == sub_board[0][2] ||
                sub_board[1][0] == SQUARE.COMPUTER && sub_board[1][0] == sub_board[1][1] && sub_board[1][0] == sub_board[1][2] ||
                sub_board[2][0] == SQUARE.COMPUTER && sub_board[2][0] == sub_board[2][1] && sub_board[2][0] == sub_board[2][2] ||
                sub_board[0][0] == SQUARE.COMPUTER && sub_board[0][0] == sub_board[1][0] && sub_board[0][0] == sub_board[2][0] ||
                sub_board[0][1] == SQUARE.COMPUTER && sub_board[0][1] == sub_board[1][1] && sub_board[0][1] == sub_board[2][1] ||
                sub_board[0][2] == SQUARE.COMPUTER && sub_board[0][2] == sub_board[1][2] && sub_board[0][2] == sub_board[2][2] ||
                sub_board[0][0] == SQUARE.COMPUTER && sub_board[0][0] == sub_board[1][1] && sub_board[0][0] == sub_board[2][2] ||
                sub_board[2][0] == SQUARE.COMPUTER && sub_board[2][0] == sub_board[1][1] && sub_board[2][0] == sub_board[0][2]) {
                return SQUARE.COMPUTER;
            } else {
                return SQUARE.NONE;
            }
        }
    }

    public GameBoard() {
        game_board = new SubBoard[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game_board[i][j] = new SubBoard();
            }
        }
    }

    // board AND square are ints between 1 and 9
    public boolean makeMove(int board, int square, SQUARE token) {
        int boardX = (board - 1) / 3;
        int boardY = (board - 1) % 3;
        int squareX = (square - 1) / 3;
        int squareY = (square - 1) % 3;

        if (game_board[boardX][boardY].getWinner() != SQUARE.NONE) {
            return false;
        }

        if (game_board[boardX][boardY].sub_board[squareX][squareY] != SQUARE.NONE) {
            return false;
        }

        game_board[boardX][boardY].sub_board[squareX][squareY] = token;

        return true;
    }

    public void drawBoardLines() {
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);

        StdDraw.line(0.333, 0, 0.333, 1);
        StdDraw.line(0.666, 0, 0.666, 1);

        StdDraw.line(0, 0.333, 1, 0.333);
        StdDraw.line(0, 0.666, 1, 0.666);
    }

    public void drawSubBoards() {
        /* Subboard gridline formula
         * (row, col) -> (topLeftDrawX, topLeftDrawY)
         * (0, 0) -> (0, 1)
         * (0, 1) -> (0.333, 1)
         * (0, 2) -> (0.666, 1)
         * 
         * (1, 0) -> (0, 0.666)
         * (1, 1) -> (0.333, 0.666)
         * (1, 2) -> (0.666, 0.666)
         * 
         * (2, 0) -> (0, 0.333)
         * (2, 1) -> (0.333, 0.333)
         * (2, 2) -> (0.666, 0.333)
         * 
         */

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                SQUARE winner = game_board[row][col].getWinner();
                double topLeftX = 0.333 * col;
                double topLeftY = 1 - 0.333 * row;

                if (winner == SQUARE.NONE) {
                    // Draw the gridlines for the subboard
                    StdDraw.setPenRadius(0.01);
                    StdDraw.setPenColor(StdDraw.GRAY);

                    StdDraw.line(topLeftX + 0.111, topLeftY, topLeftX + 0.111, topLeftY - 0.333);
                    StdDraw.line(topLeftX + 0.222, topLeftY, topLeftX + 0.222, topLeftY - 0.333);

                    StdDraw.line(topLeftX, topLeftY - 0.111, topLeftX + 0.333, topLeftY - 0.111);
                    StdDraw.line(topLeftX, topLeftY - 0.222, topLeftX + 0.333, topLeftY - 0.222);

                    // Draw the player tokens for the subboard
                    SubBoard sb = game_board[row][col];
                    for (int subRow = 0; subRow < 3; subRow++) {
                        for (int subCol = 0; subCol < 3; subCol++) {
                            SQUARE token = sb.sub_board[subRow][subCol];
                            if (token == SQUARE.PLAYER) {
                                StdDraw.setPenColor(StdDraw.BLUE);
                                StdDraw.filledSquare(topLeftX + 0.0555 + (0.111 * subCol), topLeftY - 0.0555 - (0.111 * subRow), 0.03);
                            } else if (token == SQUARE.COMPUTER) {
                                StdDraw.setPenColor(StdDraw.RED);
                                StdDraw.filledCircle(topLeftX + 0.0555 + (0.111 * subCol), topLeftY - 0.0555 - (0.111 * subRow), 0.03);
                            }
                        }
                    }

                } else if (winner == SQUARE.PLAYER){ 
                    StdDraw.setPenColor(StdDraw.BLUE);
                    StdDraw.filledSquare(topLeftX + 0.1665, topLeftY - 0.1665, 0.1665);
                } else {
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.filledSquare(topLeftX + 0.1665, topLeftY - 0.1665, 0.1665);
                }
            }
        }
    }
}
