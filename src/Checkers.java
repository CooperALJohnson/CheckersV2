import java.util.Scanner;

public class Checkers {
    // define the board
    static char[][] board = new char[8][8];
    // define the players
    static final char PLAYER1 = 'X';
    static final char PLAYER2 = 'O';

    public static void main(String[] args) {
        // initialize the board
        initializeBoard();
        // create a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        // loop until the game is over
        while (true) {
            // print the board
            printBoard();
            // prompt player 1 for a move
            System.out.println("Player 1, enter your move (e.g. A1 B2): ");
            String player1Move = scanner.nextLine();
            // validate and make the move for player 1
            if (!makeMove(player1Move, PLAYER1)) {
                System.out.println("Invalid move, try again!");
                continue;
            }
            // check if player 1 has won
            if (hasWon(PLAYER1)) {
                System.out.println("Player 1 has won!");
                break;
            }
            // check if the game is a tie
            if (isTie()) {
                System.out.println("The game is a tie!");
                break;
            }
            // print the board
            printBoard();
            // prompt player 2 for a move
            System.out.println("Player 2, enter your move (e.g. A1 B2): ");
            String player2Move = scanner.nextLine();
            // validate and make the move for player 2
            if (!makeMove(player2Move, PLAYER2)) {
                System.out.println("Invalid move, try again!");
                continue;
            }
            // check if player 2 has won
            if (hasWon(PLAYER2)) {
                System.out.println("Player 2 has won!");
                break;
            }
            // check if the game is a tie
            if (isTie()) {
                System.out.println("The game is a tie!");
                break;
            }
        }
    }// initialize the board with empty spaces

    // check if the game is a tie (i.e. no more moves left)
    public static boolean isTie() {
        // check if there are any empty spaces on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        // if we reach this point, there are no empty spaces left
        return true;
    }

    public static void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // print the current state of the board
    public static void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // parse the move and make it on the board
    public static boolean makeMove(String move, char player) {
        // parse the move string (e.g. "A1 B2")
        String[] tokens = move.split(" ");
        if (tokens.length != 2) {
            return false;
        }
        // get the starting and ending positions
        String startPos = tokens[0];
        String endPos = tokens[1];
        // validate the positions
        if (!isValidPosition(startPos) || !isValidPosition(endPos)) {
            return false;
        }
        // convert the positions to row and column indices
        int startRow = 8 - Integer.parseInt(startPos.substring(1));
        int startCol = startPos.charAt(0) - 'A';
        int endRow = 8 - Integer.parseInt(endPos.substring(1));
        int endCol = endPos.charAt(0) - 'A';
        // check if the starting position is empty
        if (board[startRow][startCol] == ' ') {
            return false;
        }
        // check if the ending position is empty
        if (board[endRow][endCol] != ' ') {
            return false;
        }
        // check if the move is valid (TODO: implement this)
        if (!isValidMove(startRow, startCol, endRow, endCol)) {
            return false;
        }
        // make the move on the board
        board[endRow][endCol] = player;
        board[startRow][startCol] = ' ';
        return true;
    }

    static boolean isValidMove(int startRow, int startCol, int endRow, int endCol) {
        // Check if the start and end positions are on the board
        if (startRow < 0 || startRow > 7 || startCol < 0 || startCol > 7 ||
                endRow < 0 || endRow > 7 || endCol < 0 || endCol > 7) {
            return false;
        }

        // Check if the start and end positions are diagonal to each other

        // Check if the start position contains a piece belonging to the current player
        // TODO: check for the current player's pieces

        // Check if the end position is empty
        // TODO: check if the end position is empty

        // Check if the move is a capture
        // TODO: check if the move is a capture

        // If all the above checks pass, the move is valid
        return true;
    }

    // check if the given position is valid (i.e. within the bounds of the board)
    public static boolean isValidPosition(String pos) {
        if (pos.length() != 2) {
            return false;
        }
        char col = pos.charAt(0);
        if (col < 'A' || col > 'H') {
            return false;
        }
        char row = pos.charAt(1);
        if (row < '1' || row > '8') {
            return false;
        }
        return true;
    }

    // check if the given player has won the game
    public static boolean hasWon(char player) {
        // check all rows
        for (int i = 0; i < 8; i++) {
            boolean won = true;
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != player) {
                    won = false;
                    break;
                }
            }
            if (won) {
                return true;
            }
        }
        // check all columns
        for (int i = 0; i < 8; i++) {
            boolean won = true;
            for (int j = 0; j < 8; j++) {
                if (board[j][i] != player) {
                    won = false;
                    break;
                }
            }
            if (won) {
                return true;
            }
        }
        // check both diagonals
        boolean won = true;
        for (int i = 0; i < 8; i++) {
            if (board[i][i] != player) {
                won = false;
                break;
            }
        }
        if (won) {
            return true;
        }
        won = true;
        for (int i = 0; i < 8; i++) {
            if (board[i][7 - i] != player) {
                won = false;
                break;


            }

        }
        return won;
    }
}