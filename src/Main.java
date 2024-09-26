import java.util.Scanner;

public class Main {
    private char[][] board;
    private char currentPlayer;
    private Scanner scanner;

    public Main() {
        board = new char[3][3];
        currentPlayer = 'X';
        scanner = new Scanner(System.in);
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void playGame() {
        boolean isRunning = true;

        while (isRunning) {
            printBoard();
            playerMove();
            isRunning = !checkForWin() && !isBoardFull();

            if (!isRunning) {
                printBoard();
                if (checkForWin()) {
                    System.out.println("Player " + currentPlayer + " wins!");
                } else {
                    System.out.println("The game is a draw!");
                }
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
        }

        askToRestart();
    }

    private void printBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void playerMove() {
        int row, col;
        while (true) {
            System.out.print("Player " + currentPlayer + ", enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("This move is not valid. Try again.");
            }
        }
    }

    private boolean checkForWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private void askToRestart() {
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next();
        if (response.equalsIgnoreCase("yes")) {
            initializeBoard();
            currentPlayer = 'X';
            playGame();
        } else {
            System.out.println("Thank you for playing!");
            scanner.close();
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.playGame();
    }
}
