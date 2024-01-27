/**
 * Program that runs a fully functional Checkers Game within the console of the user
 * packages file under 'ui'.
 */

package ui;
import core.Checkers;
import java.util.Scanner; // scanner

/**
 * This is a CheckersTextConsole class within the ui package making use 
 * of Checkers class within the core package.
 * 
 * @author notnatedavis
 * @version Build Jan 21, 2024
 * package ui
 */
public class CheckersTextConsole {
    /**
     * This main method instantiates a Checkers game followed by a while
     * loop that is active until the isGameOver() method from the core package 
     * reads true.
     */
    public static void main(String[] args) {
    	/**
        * instantiates new object of Checkers to game
        * instantiates new object of Scanner to scanner 
        */
        Checkers game = new Checkers();
        Scanner scanner = new Scanner(System.in);
        
        /**
         * This while loop will remain true until the method isGameOver() 
         * returns true from the Checkers class within the ui package.
         */
        while (!game.isGameOver()) {
        	// Displays copy of a set 'Checkers board'
            game.printBoard();

            // Displays corresponding 'current' player's turn followed by 
            // scanner.nextLine of the users input saving it as a string 'move'
            System.out.println("Player " + game.getCurrentPlayer() + " - your turn.");
            System.out.println("Choose a cell position of piece to be moved and the new position. e.g., 3a-4b");
            String move = scanner.nextLine();

            /**
             * This if statement reads, if the user inputed string reads as
             * as valid then decode inputed string 'move'.
             */
            if (isValidMoveFormat(move)) {
            	
            	// Splits string 'move' into 2 separate strings
            	String[] coordinates = move.split("-");
            	
            	// Assigns the integer values to corresponding positions 
            	// within the string
                int sourceRow = 8 - Integer.parseInt(coordinates[0].substring(0, 1));
                int sourceColumn = coordinates[0].charAt(1) - 'a';
                int destinationRow = 8 - Integer.parseInt(coordinates[1].substring(0, 1));
                int destinationColumn = coordinates[1].charAt(1) - 'a';
                
                /** Checks to see if the assigned integer values are a valid 
                 * move on the checkers board (not occupied, within bounds,
                 * correct direction, correct player) through the isValidMove 
                 * method within the Checkers class, ui package.
                 */
                if (game.isValidMove(sourceRow, sourceColumn, destinationRow, destinationColumn)) {
                	/**
                	 * This runs the movePiece method within the Checkers class, 
                	 * ui package. Essentially moving current player's piece on 
                	 * the board to selected position.
                	 */
                    game.movePiece(sourceRow, sourceColumn, destinationRow, destinationColumn);
                    
                    /**
                     * Switches the current player X : O signifying next turn.
                     */
                	game.switchPlayer();
                	
                	/**
                     * Adds spacing
                     */
                	System.out.println();
                	
                	/**
                     * Prints updated board version of the game board
                     */
                	game.printBoard();
                	
                } else {
                	/**
                     * This reads as true when isValidMove returns as false
                     */
                	System.out.println("case1) Invalid move. Please try again.");
                }
            } else {
            	/**
                 * This reads as true when isValidMoveFormat returns as false
                 */
            	System.out.println("case2) Invalid move format");
            }
        }
        /**
         * Prints Final version of the updated board followed by assigning the winner
         * based on which player has 0 pieces remaining, and then displaying appropriate winner.
         */ 
        game.printBoard();
        char winner = (game.countPieces() == 0) ? 'o' : 'x';
        System.out.println("Player " + winner + " Won the Game");
    }
    /**
     * This method checks if the string fits the valid format of a checkers game.
     * 
     * @param user inputed String 'move'
     * @return true ; fits format
     * @returns false ; does not fit format
     */
    private static boolean isValidMoveFormat(String move) {
    	/**
         * conditions user inputed string 'move' needs to meet to return true
         */
    	if(!move.matches("[1-8][a-h]-[1-8][a-h]")) {
    		return false; //will print invalid move
    	}
    	return true;
    }
}
