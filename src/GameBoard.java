package src;

public class GameBoard {

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
            return SQUARE.NONE;
        }
    }

    private SubBoard[][] game_board;

    public GameBoard() {
        game_board = new SubBoard[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game_board[i][j] = new SubBoard();
            }
        }
    }

    /**
     * 
     * 1 -> 0, 0
     * 2 -> 0, 1
     * 3 -> 0, 2
     * 4 -> 1, 0
     * 5 -> 1, 1
     * 6 -> 1, 2
     * 7 -> 2, 0
     * 8 -> 2, 1
     * 9 -> 2, 2
     * 
     */

    // board AND square are ints between 1 and 9
    public boolean makeMove(int board, int square, SQUARE token) {
        int boardX = (board - 1) / 3;
        int boardY = (board - 1) % 3;
        int squareX = (square - 1) / 3;
        int squareY = (square - 1) % 3;

        if (game_board[boardX][boardY].sub_board[squareX][squareY] != SQUARE.NONE) {
            return false;
        }

        game_board[boardX][boardY].sub_board[squareX][squareY] = token;

        return true;
    }

    public void setArtificialBoardState() {
        game_board[0][0].sub_board[0][0] = SQUARE.PLAYER;
        game_board[0][0].sub_board[0][2] = SQUARE.PLAYER;
        game_board[0][0].sub_board[2][1] = SQUARE.PLAYER;
        game_board[0][0].sub_board[1][0] = SQUARE.COMPUTER;
        game_board[0][0].sub_board[1][1] = SQUARE.COMPUTER;
        game_board[0][0].sub_board[1][2] = SQUARE.COMPUTER;

        game_board[1][1].sub_board[0][0] = SQUARE.PLAYER;
        game_board[1][1].sub_board[0][2] = SQUARE.PLAYER;
        game_board[1][1].sub_board[2][1] = SQUARE.PLAYER;
        game_board[1][1].sub_board[1][0] = SQUARE.COMPUTER;
        game_board[1][1].sub_board[1][1] = SQUARE.COMPUTER;
        game_board[1][1].sub_board[1][2] = SQUARE.COMPUTER;

        game_board[2][2].sub_board[0][0] = SQUARE.PLAYER;
        game_board[2][2].sub_board[0][2] = SQUARE.PLAYER;
        game_board[2][2].sub_board[2][1] = SQUARE.PLAYER;
        game_board[2][2].sub_board[1][0] = SQUARE.COMPUTER;
        game_board[2][2].sub_board[1][1] = SQUARE.COMPUTER;
        game_board[2][2].sub_board[1][2] = SQUARE.COMPUTER;
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
                if (winner == SQUARE.NONE) {
                    // Draw the gridlines for the subboard
                    StdDraw.setPenRadius(0.01);
                    StdDraw.setPenColor(StdDraw.GRAY);

                    double topLeftX = 0.333 * col;
                    double topLeftY = 1 - 0.333 * row;

                    StdDraw.line(topLeftX + 0.111, topLeftY, topLeftX + 0.111, topLeftY - 0.333);
                    StdDraw.line(topLeftX + 0.222, topLeftY, topLeftX + 0.222, topLeftY - 0.333);

                    StdDraw.line(topLeftX, topLeftY - 0.111, topLeftX + 0.333, topLeftY - 0.111);
                    StdDraw.line(topLeftX, topLeftY - 0.222, topLeftX + 0.333, topLeftY - 0.222);

                    // Draw the player tokens for the subboard
                    SubBoard sb = game_board[row][col];
                    for (int subRow = 0; subRow < 3; subRow++) {
                        for (int subCol = 0; subCol < 3; subCol++) {
                            SQUARE token = sb.sub_board[subRow][subCol];
                            /*
                             * 
                             * 
                             */
                            if (token == SQUARE.PLAYER) {
                                StdDraw.setPenColor(StdDraw.BLUE);
                                StdDraw.filledSquare(topLeftX + 0.0555 + (0.111 * subCol), topLeftY - 0.0555 - (0.111 * subRow), 0.03);
                            } else if (token == SQUARE.COMPUTER) {
                                StdDraw.setPenColor(StdDraw.RED);
                                StdDraw.filledCircle(topLeftX + 0.0555 + (0.111 * subCol), topLeftY - 0.0555 - (0.111 * subRow), 0.03);
                            }
                        }
                    }

                } else { // Fill with the correct color

                }
            }
        }
    }
}
