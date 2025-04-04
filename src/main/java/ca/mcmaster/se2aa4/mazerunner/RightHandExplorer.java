package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandExplorer implements AlgorithmExplorer {

    private static final Logger logger = LogManager.getLogger();

    // Method to search for a path and update the path attribute

    @Override

    public Path findPath(Maze maze) {

        // Initialize a path create to build a path
        PathCreator pathCreator = new PathCreator();
        // Instantiate a command history to track command calls
        CommandHistory history = new CommandHistory();

        // Add pathCreator as an Observer to history
        history.attachObserver(pathCreator);

        // Variables for traversing the maze
        List<boolean[]> matrix = maze.getMatrix();
        Marker marker = new Marker(maze.getWestEntry(), 0, 1);

        int endRow = maze.getEastEntry();
        int endCol = matrix.getFirst().length - 1;

        /*
         * Right Hand Algorithm
         * (Idea is to "hug" the right wall)
         * If you cant go right, go forwards
         * If you cant go right or forwards, go left
         */

        logger.info("Finding Path via Right Hand Algorithm...");

        while (marker.getRow() != endRow || marker.getCol() != endCol) {

            // Try going right and forwards
            history.push(new RightCommand(marker));
            history.push(new ForwardCommand(marker));

            if (isEmpty(matrix, marker.getRow(), marker.getCol())) {
                logger.info("Going right");
                logger.info("Going forwards");
            }

            // If forward square is not empty, undo the right turn and go forwards

            else {

                history.pop();
                history.pop();
                history.push(new ForwardCommand(marker));

                if (isEmpty(matrix, marker.getRow(), marker.getCol())) {
                    logger.info("Going forwards");
                }
                // If forward square is not empty, undo the forward, and go left
                else {
                    history.pop();
                    history.push(new LeftCommand(marker));
                    logger.info("Going Left");
                }
            }
            history.clear();
        }
        logger.info("Path Found!");
        return new Path(pathCreator.toString());
    }

    // Method to Verify Path

    @Override

    public boolean verifyPath(Maze maze, String path) {

        List<boolean[]> matrix = maze.getMatrix();

        int westEntry = maze.getWestEntry();
        int eastEntry = maze.getEastEntry();
        int eastEndCol = 0;
        int westEndCol = matrix.getFirst().length - 1;

        Marker westMarker = new Marker(westEntry, 0, 1);
        Marker eastMarker = new Marker(eastEntry, matrix.getFirst().length - 1, 3);

        boolean validWest = checkEntry(matrix, westMarker, eastEntry, westEndCol, path);
        boolean validEast = checkEntry(matrix, eastMarker, westEntry, eastEndCol, path);

        return validWest || validEast;
    }

    // Helper method to verify if a certain row, col index is empty or not

    private static boolean isEmpty(List<boolean[]> matrix, int row, int col) {
        return (0 <= col && col < matrix.getFirst().length) && (0 <= row && row < matrix.size())
                && (matrix.get(row)[col]);
    }

    // Helper method to check entrances

    private static boolean checkEntry(List<boolean[]> matrix, Marker marker, int endRow, int endCol, String path) {

        MoveCommand moveCommand;
        int i = 0;

        while (i < path.length()) {

            if (Character.isDigit(path.charAt(i))) {

                int repetition = Character.getNumericValue(path.charAt(i));
                char repeat = path.charAt(i + 1);

                for (int j = 0; j < repetition; j++) {
                    if (repeat == 'F') {
                        moveCommand = new ForwardCommand(marker);
                        moveCommand.execute();
                    } else if (repeat == 'L') {
                        moveCommand = new LeftCommand(marker);
                        moveCommand.execute();
                    } else {
                        moveCommand = new RightCommand(marker);
                        moveCommand.execute();
                    }
                }
                i++;
            }

            else if (path.charAt(i) == 'F') {
                moveCommand = new ForwardCommand(marker);
                moveCommand.execute();
            } else if (path.charAt(i) == 'L') {
                moveCommand = new LeftCommand(marker);
                moveCommand.execute();
            } else if (path.charAt(i) == 'R') {
                moveCommand = new RightCommand(marker);
                moveCommand.execute();
            }
            if (!isEmpty(matrix, marker.getRow(), marker.getCol())) {
                break;
            }
            i++;
        }

        return marker.getRow() == endRow && marker.getCol() == endCol;
    }

}