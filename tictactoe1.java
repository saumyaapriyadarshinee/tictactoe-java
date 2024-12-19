import java.util.Scanner;

public class tictactoe1
{
    // 3x3 board initialization, filled with spaces representing empty spots
    private static char[][] board = 
    {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in); // Scanner to read user input
        char currentPlayer = 'X'; // 'X' starts the game
        boolean gameWon = false; // Flag to check if the game is won

        // Main game loop runs until there's a winner or the board is full
        while (!gameWon && !isBoardFull()) 
        {
            printBoard(); // Print current state of the board
            System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] and column [1-3]): ");
            
            int row = scanner.nextInt() - 1; // Get row input and adjust to 0-index
            int col = scanner.nextInt() - 1; // Get column input and adjust to 0-index

            // Check if the move is within bounds and the cell is empty
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') 
            {
                board[row][col] = currentPlayer; // Make the move

                // Check if the current player has won
                if (checkWin(currentPlayer)) 
                {
                    printBoard(); // Print the final board
                    System.out.println("Player " + currentPlayer + " wins!"); // Declare the winner
                    gameWon = true; // Set the flag to end the game
                } else 
                {
                    // Switch player (X to O, O to X)
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                // If the move is invalid, prompt the user to try again
                System.out.println("This move is not valid. Try again.");
            }
        }

        // If the board is full and no player has won, declare a draw
        if (!gameWon) {
            printBoard(); // Print the final board
            System.out.println("It's a draw!"); // Declare draw
        }

        scanner.close(); // Close the scanner object
    }

    // Method to print the current state of the board
    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | "); // Print each cell of the board
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Method to check if the current player has won
    private static boolean checkWin(char player) {
        // Check each row and column for a win
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) || 
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true; // Return true if any row or column has all same player's symbols
            }
        }
        // Check both diagonals for a win
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    // Method to check if the board is full (i.e., all spots are filled)
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Return false if any spot is empty
                }
            }
        }
        return true; // Return true if no empty spots are found
    }
}
