package ca.mcmaster.se2aa4.mazerunner;

// Receiver of commands

public class Marker {

    private static final int[][] DIRECTIONS = {
            { -1, 0 }, // North
            { 0, 1 }, // East
            { 1, 0 }, // South
            { 0, -1 } // West
    };

    private int row;
    private int col;
    private int directionIndex;

    public Marker(int row, int col, int directionIndex) {
        this.row = row;
        this.col = col;
        this.directionIndex = directionIndex;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getDirectionIndex() {
        return directionIndex;
    }

    public void moveForward() {
        row += DIRECTIONS[directionIndex][0];
        col += DIRECTIONS[directionIndex][1];
    }

    public void moveBackward() {
        row -= DIRECTIONS[directionIndex][0];
        col -= DIRECTIONS[directionIndex][1];
    }

    public void turnRight() {
        directionIndex = (directionIndex + 1) % 4;
    }

    public void turnLeft() {
        directionIndex = (directionIndex + 3) % 4;
    }

}
