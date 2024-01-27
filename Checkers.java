/**
 * Logic for fully functional Checkers Game
 * packages file under 'core'. (still has minor bugs on piece capturing)
 */

package core;

/**
 * This is a Checkers class within the core package making use 
 * of CheckersTextConsole class within the 'ui' package.
 * 
 * @author xxx4am
 * @version Build Jan 21, 2024
 * package core
 */
public class Checkers {
    private char[][] board;
    private char currentPlayer;

    /**
     * This class exists to hold a new version of a blank board.
     */
    public Checkers() {
        // Initialize board + set current player
        board = new char[][] {
            {'_', 'o', '_', 'o', '_', 'o', '_', 'o'},
            {'o', '_', 'o', '_', 'o', '_', 'o', '_'},
            {'_', 'o', '_', 'o', '_', 'o', '_', 'o'},
            {'_', '_', '_', '_', '_', '_', '_', '_'},
            {'_', '_', '_', '_', '_', '_', '_', '_'},
            {'x', '_', 'x', '_', 'x', '_', 'x', '_'},
            {'_', 'x', '_', 'x', '_', 'x', '_', 'x'},
            {'x', '_', 'x', '_', 'x', '_', 'x', '_'}
        };
        currentPlayer = 'o';
    }
    
    /**
     * This method exists to repeatedly print out the board with layout
     */
    public void printBoard() {
        for (int i = 0; i < 8; i++) {
        	System.out.print((8 - i) + "|"); //prints row # here
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h"); //prints column here
    }

    /**
     * This method exists to alternate between players after each turn
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'o') ? 'x' : 'o';
    }

    /**
     * This method returns the character associated to current player 'o' : 'x'
     * 
     * @return char 'o' : 'x'
     */
    public char getCurrentPlayer() {
        return currentPlayer;
    }
    
    /**
     * This method takes in 4 integer values that hold information 
     * for current position and desired position and moves appropriately.
     * 
     * @param sourceRow; selected piece's current row
     * @param sourceColumn; selected piece's current column
     * @param destinationRow; selected piece's destination row
     * @param destinationColumn; selected piece's destination column
     */
    public void movePiece(int sourceRow, int sourceColumn, int destinationRow, int destinationColumn) {
    	/**
         * Sets temporary variable 'piece' to the value of the source, 
         * then sets the value of the source to blank, followed by setting
         * the desired destination to the original value in 'piece'
         */
    	char piece = getPieceAt(sourceRow, sourceColumn);
    	board[sourceRow][sourceColumn] = '_';
    	board[destinationRow][destinationColumn] = piece;
    	
    	/**
         * Asks if piece is able to be captured; if yes run.
         */
    	capturePiece(sourceRow, sourceColumn, destinationRow, destinationColumn);
    }
    
    /**
     * This method checks if the move is valid for the given integer 
     * values passed to it.
     * 
     * @param sourceRow; selected piece's current row
     * @param sourceColumn; selected piece's current column
     * @param destinationRow; selected piece's destination row
     * @param destinationColumn; selected piece's destination column
     * @return true if move is valid
     */
    public boolean isValidMove(int sourceRow, int sourceColumn, int destinationRow, int destinationColumn) {
    	
    	char sourcePiece = getPieceAt(sourceRow, sourceColumn);
    	char destinationPiece = getPieceAt(destinationRow, destinationColumn);
    	
    	/**
         * Checks to see if destination is within bounds.
         */
    	if (destinationRow < 0 || destinationRow > 8 || destinationColumn < 0 || destinationColumn > 8) {
    		return false;
    	}
    	/**
         * Checks to see if destination is not empty.
         */
    	if (destinationPiece != '_') {
    		return false;
    	}
    	/**
         * Checks to see if destination is diagonal.
         */
    	if (Math.abs(destinationRow - sourceRow) != 1 || Math.abs(destinationColumn - sourceColumn) != 1) {
    		/**
             * Checks to see if destination is diagonal with 
             * a distance of 2 when capturing.
             */
    		if (Math.abs(destinationRow - sourceRow) == 2 && Math.abs(destinationColumn - sourceColumn) == 2) {
    			/**
    	         * This saves value of the piece source piece is capturing
    	         */
    	    	int capturedRow = (sourceRow + destinationRow) / 2;
    	    	int capturedColumn = (sourceColumn + destinationColumn) / 2;
    	    	char capturedPiece = getPieceAt(capturedRow, capturedColumn);
    	    	
    	    	/**
    	         * Runs an if check to see if the capturedPiece is not blank 
    	         * and also not current players own piece, if true fills captured
    	         * slot with blank.
    	         * 
    	         * @return true if captured piece not equal to source piece or blank space.
    	         * @return false if otherwise.
    	         */
    	    	if (capturedPiece != '_' && capturedPiece != sourcePiece) {
    	    		/**
    	             * This removes the captured piece
    	             */
    	    		board[capturedRow][capturedColumn] = '_';
    	    		return true;
    	    	}
    		}
    		return false;
    	}
    	/**
         * This checks if the piece the current player is moving 
         * is moving in the correct direction of the board.
         */
    	if ((sourcePiece == 'x' && destinationRow > sourceRow) || (sourcePiece == 'o' && destinationRow < sourceRow)) {
    		return false;
    	}
    	return true;
    }
    
    /**
     * This method returns the current piece on the board.
     * 
     * @param row
     * @param column
     * @return char board[row][column]
     */
    public char getPieceAt(int row, int column) {
    	return board[row][column];
    }
    
    /**
     * This method saves the value of the piece source piece is capturing.
     */
    private void capturePiece(int sourceRow, int sourceColumn, int destinationRow, int destinationColumn) {
    	int capturedRow = (sourceRow + destinationRow) / 2;
    	int capturedColumn = (sourceColumn + destinationColumn) / 2;
    	char capturedPiece = getPieceAt(capturedRow, capturedColumn);
    	
    	if (capturedPiece != '_') {
    		/**
             * This removes the captured piece.
             */
    		board[capturedRow][capturedColumn] = '_';
    	}
    }
    /**
     * This method returns true if the total amount of pieces current player
     * has is equal to 0.
     * 
     * @return true if total amount of pieces is equal to 0
     * @return false if otherwise
     */
    public boolean isGameOver() {
        return countPieces() == 0;
    }

    /**
     * This method counts the total amount of pieces current player has.
     * 
     * @return count
     */
    public int countPieces() {
    	char currentPlayer = getCurrentPlayer();
    	int count = 0;
    	
    	for (int i = 0; i < 8; i ++) {
    		for (int j = 0; j < 8; j++) {
    			if (board[i][j] == currentPlayer) {
    				count++;
    			}
    		}
    	}
    	return count;
    }
}
