package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandExplorer implements AlgorithmExplorer {

    // Represents the change of row and col when moving forwards from a certain
    // direction {row, col}

    private static final int[][] DIRECTIONS = {
            { -1, 0 }, // North
            { 0, 1 }, // East
            { 1, 0 }, // South
            { 0, -1 } // West
    };

    private static final Logger logger = LogManager.getLogger();

    // Method to search for a path and update the path attribute

    @Override

    public Path findPath(Maze maze) {

        // Initialize a string builder to build a path

        StringBuilder path = new StringBuilder();

        // Variables for traversing the maze

        List<boolean[]> matrix = maze.getMatrix();
        int row = maze.getWestEntry(); // Entry row
        int col = 0;
        int directionIndex = 1; // Starts facing East, Adding 1 to the index will turn right, Adding 3 will turn
                                // left

        int endRow = maze.getEastEntry();
        int endCol = matrix.getFirst().length - 1;

        /*
         * Right Hand Algorithm
         * (Idea is to "hug" the right wall)
         * If you cant go right, go forwards
         * If you cant go right or forwards, go left
         */

        logger.info("Finding Path via Right Hand Algorithm...");

        while (row != endRow || col != endCol) {

            // Try going right first

            int directionFaced = (directionIndex + 1) % 4;
            int forwardRow = row + DIRECTIONS[directionFaced][0];
            int forwardCol = col + DIRECTIONS[directionFaced][1];

            // Checks if the forward square is empty

            if (isEmpty(matrix, forwardRow, forwardCol)) {
                path.append("RF");
                row = forwardRow;
                col = forwardCol;
                directionIndex = directionFaced;
                logger.info("Going right");
            }

            // Try going forwards if you cant go right

            else {
                forwardRow = row + DIRECTIONS[directionIndex][0];
                forwardCol = col + DIRECTIONS[directionIndex][1];

                if (isEmpty(matrix, forwardRow, forwardCol)) {
                    path.append("F");
                    row = forwardRow;
                    col = forwardCol;
                    logger.info("Going forwards");
                }
                // Cant go right or forwards, go left
                else {
                    directionIndex = (directionIndex + 3) % 4;
                    path.append('L');
                    logger.info("Going Left");
                }
            }

        }
        logger.info("Path Found!");
        return new Path(path.toString());
    }

    // Method to Verify Path

    @Override

    public boolean verifyPath(Maze maze, String path) {

        boolean verified = false;
        List<boolean[]> matrix = maze.getMatrix();

        // Check west entrance first

        int row = maze.getWestEntry();
        int col = 0;
        int exitRow = maze.getEastEntry();
        int exitCol = matrix.getFirst().length - 1;
        int directionIndex = 1; // Starts facing east
        int i = 0;

        while (i < path.length()) {

            if (Character.isDigit(path.charAt(i))) {

                int repetition = Character.getNumericValue(path.charAt(i));
                char repeat = path.charAt(i + 1);

                for (int j = 0; j < repetition; j++) {
                    if (repeat == 'F') {
                        row = row + DIRECTIONS[directionIndex][0];
                        col = col + DIRECTIONS[directionIndex][1];
                    } else if (repeat == 'L') {
                        directionIndex = (directionIndex + 3) % 4;
                    } else {
                        directionIndex = (directionIndex + 1) % 4;
                    }
                }
                i++;
            }

            else if (path.charAt(i) == 'F') {
                row = row + DIRECTIONS[directionIndex][0];
                col = col + DIRECTIONS[directionIndex][1];
            } else if (path.charAt(i) == 'L') {
                directionIndex = (directionIndex + 3) % 4;
            } else if (path.charAt(i) == 'R') {
                directionIndex = (directionIndex + 1) % 4;
            }
            if (!isEmpty(matrix, row, col)) {
                break;
            }
            i++;
        }

        if (row == exitRow && col == exitCol) {
            verified = true;
            return verified;
        }

        // Check east entrance if west entrance is not verified

        row = exitRow;
        col = exitCol;
        exitRow = maze.getWestEntry();
        exitCol = 0;
        directionIndex = 3; // Starts facing west
        i = 0;

        while (i < path.length()) {

            if (Character.isDigit(path.charAt(i))) {

                int repetition = Character.getNumericValue(path.charAt(i));
                char repeat = path.charAt(i + 1);

                for (int j = 0; j < repetition; j++) {
                    if (repeat == 'F') {
                        row = row + DIRECTIONS[directionIndex][0];
                        col = col + DIRECTIONS[directionIndex][1];
                    } else if (repeat == 'L') {
                        directionIndex = (directionIndex + 3) % 4;
                    } else {
                        directionIndex = (directionIndex + 1) % 4;
                    }
                }
                i++;
            }

            else if (path.charAt(i) == 'F') {
                row = row + DIRECTIONS[directionIndex][0];
                col = col + DIRECTIONS[directionIndex][1];
            } else if (path.charAt(i) == 'L') {
                directionIndex = (directionIndex + 3) % 4;
            } else if (path.charAt(i) == 'R') {
                directionIndex = (directionIndex + 1) % 4;
            }
            if (!isEmpty(matrix, row, col)) {
                break;
            }
            i++;
        }

        if (row == exitRow && col == exitCol) {
            verified = true;
        }

        return verified;
    }

    // Helper method to verify if a certain row, col index is empty or not

    private static boolean isEmpty(List<boolean[]> matrix, int row, int col) {
        return (0 <= col && col < matrix.getFirst().length) && (0 <= row && row < matrix.size())
                && (matrix.get(row)[col]);
    }

}