/* Lexi Katramados
   ackatra
   11/6/2022
   CPSC 1061-004 */

import java.util.Scanner;
import java.util.InputMismatchException;

    public class TicTacToe {    
       
        // Declares a method to print out everything before the game begins (welcome, example, blank board)
        public static void printWelcome() {
            // Print Welcome and example input and board
            System.out.println("Let's play Tic-Tac-Toe!");
            System.out.println("When prompted, enter desired row and column numbers");
            System.out.println("Example: 1 3");
            System.out.println();
            System.out.println("| _ | _ | X |");
            System.out.println("| _ | _ | _ |");
            System.out.println("| _ | _ | _ |");
            System.out.println();
            System.out.println();

            // Print starting statement and print out blank board
            System.out.println("Let's play!");
            System.out.println("Player X starts!");
            System.out.println();
            System.out.println("| _ | _ | _ |");
            System.out.println("| _ | _ | _ |");
            System.out.println("| _ | _ | _ |");
            System.out.println();
            System.out.println();
        }
        
        public static void validateUserInput (Scanner scnr, char[][] board, char player) {
            // Try statement that will test if input from the scanner as an integer
            try {
                // Gets the row and column number from the user input
                int row = scnr.nextInt();
                int column = scnr.nextInt();

                // If input is greater than 3 or less than 1, prompt user to enter new inputs and restart method
                if (row > 3 || column > 3 || row < 1 || column < 1) {
                    System.out.println("Please enter valid row and col numbers from 1 to 3:");
                    validateUserInput(scnr, board, player);
                }
                // If user chooses a box that is full, tell them, prompt for new input and restart method
                else if (board[row - 1][column - 1] == 'X' || board[row - 1][column - 1] == 'O') {
                    System.out.println("That spot is full!");
                    System.out.println();
                    System.out.println("Enter row and column for player " + player);
                    validateUserInput(scnr, board, player);
                }
                // If user input is valid, set the indicated spot to the player's character (X or O)
                else {
                    assignUserMove(row, column, board, player);
                }
            }

            // Catch statement will catch any other bad input and prompt the user to enter valid input and will run the userInput method again until valid input is entered
            catch (InputMismatchException e) {
                scnr.nextLine();
                System.out.println("Please enter valid row and col numbers from 1 to 3:");
                validateUserInput(scnr, board, player);
            }
        }

        // Declares a method to put valid user input into correct position in array
        public static void assignUserMove(int row, int column, char[][]board, char player) {
            
            // Subtract 1 from user row and column so it aligns with array
            row--; 
            column--;

            // Use a nested for loop to scan for position in the array and replace with variable
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (row == i && column ==j) {
                        board[i][j] = player;
                    }
                }
            }
        }

        // Declares a method to print board with user input in correct spot
        public static void printBoard (char[][]board) {
            System.out.println();
            
            // Run through the array and print every spot
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print("| " + board[i][j] + " ");
                }
                System.out.println("|");
            }
            System.out.println();
        }

        // Declares a method to check for a horizontal win
        public static boolean checkHoriz (char[][]board) {

            // For loop to scan through the three rows and check if all three spots in any row have all X's or all 0's. If yes, print winner and return true.
            for (int i = 0; i < 3; i++) {
                if (board [i][0] != '_' && board [i][1] != '_' && board [i][2] != '_') {
                    if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][1] == board[i][2]) {
                        System.out.println("Player " + board[i][0] + " WINS!");
                        System.out.println();
                        System.out.println();
                        return true;
                    }
                }
            }
            return false;
        }

       // Declares a method to check for a veritcal win
        public static boolean checkVert (char[][]board) {

            // For loop to scan through the three columns and check if all three spots in any column have all X's or all 0's. If yes, print winner and return true.
            for (int i = 0; i < 3; i++) {
                if (board [0][i] != '_' && board [1][i] != '_' && board [2][i] != '_') {
                    if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[1][i] == board[2][i]) {
                        System.out.println("Player " + board[0][i] + " WINS!");
                        System.out.println();
                        System.out.println();
                        return true;
                    }
                }
            }
            return false;
        }

       // Declares a method to check for a diagonal win
       public static boolean checkDiag (char[][]board) {

            // For loop to scan through the diagonal and see if they have all X's or all 0's. If yes, print winner and return true.
            if (board [0][0] != '_' && board [1][1] != '_' && board [2][2] != '_') {
                if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[1][1] == board[2][2]) {
                    System.out.println("Player " + board[0][0] + " WINS!");
                    System.out.println();
                    System.out.println();
                    return true;
                }
            }

            // Check second diagonal
            if (board [0][2] != '_' && board [1][1] != '_' && board [2][0] != '_') {
                if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[1][1] == board[2][0]) {
                    System.out.println("Player " + board[0][2] + " WINS!");
                    System.out.println();
                    System.out.println();
                    return true;
                }
            }
            return false;
        
        }

        // Declares a method to check for a tie
        public static boolean checkTie (char[][]board) {

            // For loop to check if board is full and if yes with no win case, then print tie and return false.
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        return false;
                    }
                }
            }

            System.out.println("It's a TIE!");
            System.out.println();
            return true;
        }

        // Declare a method to prompt the user if they want to play again
        public static boolean playAgain (Scanner scnr) {

            // Once game is done, ask if they want to play again and get their input
            System.out.println("Do you want to play again? Y or N");
            String wantPlayAgain = scnr.next();

            // Create a while loop and as long as input is not a Y, y, N or n, keep prompting them
            while (!wantPlayAgain.equals("Y") && !wantPlayAgain.equals("y") && !wantPlayAgain.equals("N") && !wantPlayAgain.equals("n")) {
                System.out.println("Please enter valid input: Y or N");
                System.out.println();
                System.out.println("Do you want to play again? Y or N");
                wantPlayAgain = scnr.next();
            }
            
            // Once a valid input is received, if yes, then return false to the isDone loop and continue the game, if no, then end the game
            if (wantPlayAgain.equals("Y") || wantPlayAgain.equals("y")) {
                return true;
            }
            else {
                return false;
            }
        }
        public static void main(String[] args) {

            Scanner scnr = new Scanner(System.in);
            
            // Call printWelcome method
            printWelcome();

            // Declare 2-D array for board and initialize all values to an underscore
            char[][] board = new char[3][3];

            // Declare different variables we'll use in the while loop
            char player;
            int row;
            int column;
            
            // Continue all commands as long as game is not done
            boolean isDone = false;
            boolean newGame = true;
            
            while (!isDone) {

                // Clear board to start
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        board[i][j] = '_';
                    }
                }

                while (newGame) {

                    // Prompt user to make move, use input and put in variables row and column for player X
                    player = 'X';
                    System.out.println("Enter row and column for player " + player);
            
                    // Call validateUserInput method to get user move and validate; from there, once valid, call assignUserMove
                    validateUserInput(scnr, board, player);

                    // Call printBoard method
                    printBoard(board);
                    
                    // Call checkHoriz, checkVert, and checkDiag and if X has a win, call playAgain method and run that. 
                    // If they want to play again, reset board and repeat loop, if not, break.
                    if (checkHoriz(board) || checkVert(board) || checkDiag(board) || checkTie(board)) {
                        newGame=false;
                        break;
                    }

                    // Repeat process for player O
                    player = 'O';
                    System.out.println("Enter row and column for player " + player);
            
                    // Call validateUserInput method using user input; from there, once valid, call assignUserMove
                    validateUserInput(scnr, board, player);

                    // Call printBoard method
                    printBoard(board);

                    // Call checkHoriz, checkVert, and checkDiag and if X has a win, call playAgain method and run that. 
                    // If they want to play again, reset board and repeat loop, if not, break.
                    if (checkHoriz(board) || checkVert(board) || checkDiag(board) || checkTie(board)) {
                        newGame = false;
                        break;
                    }
                } // end of newGame

                // If play again is true, newGame is now true, but if the player answers no, the game is over
                if (playAgain(scnr)) {
                    isDone = false;
                    newGame = true;
                    System.out.println();
                    printWelcome();
                }
                else {
                    isDone = true;
                }
            } // end of isDone
        }
    }