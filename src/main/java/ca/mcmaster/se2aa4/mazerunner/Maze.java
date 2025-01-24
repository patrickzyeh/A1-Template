package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class Maze {

    // Maze Attributes

    private final List<boolean[]> matrix;

    // Maze Constructor

    public Maze(List<boolean[]> matrix) {
        this.matrix = matrix;
    }

    // Getter Methods

    public List<boolean[]> getMatrix() {
        return this.matrix;
    }

    public int getWestEntry() {
        int entry = 0;

        for (int i = 0; i < this.matrix.size(); i++) {
            if (this.matrix.get(i)[0]) {
                entry = i;
            }
        }
        return entry;
    }

    public int getEastEntry() {
        int entry = 0;
        int lastCol = this.matrix.getFirst().length - 1;

        for (int i = 0; i < this.matrix.size(); i++) {
            if (this.matrix.get(i)[lastCol]) {
                entry = i;
            }
        }
        return entry;
    }

    // Method to Verify Canonical Form Path (Entering from the west)

    public void verifyPath(String path){

        // Store relevant matrix index in variables

        int row = this.getWestEntry();
        int col = 0;

        int exitRow = this.getEastEntry();
        int exitCol = this.matrix.getFirst().length - 1;

        // Variables to help manage direction as the path is traversed

        final char[] DIRECTIONS = {'N', 'E', 'S', 'W'};
        char direction = DIRECTIONS[1];
        boolean right = true; //for readability
        boolean left = false; // for readability

        // Traverse characters of the path and perform operations accordingly

        for (int i = 0; i < path.length(); i++){

            if (path.charAt(i) == 'F'){
                switch (direction){
                    case 'N':
                        row -= 1;
                        break;
                    case 'E':
                        col += 1;
                        break;
                    case 'S':
                        row += 1;
                        break;
                    case 'W':
                        col -= 1;
                        break;
                }
            }
            else if (path.charAt(i) == 'L'){
                direction = RightHandExplorer.changeDirection(direction, left);
            }
            else if (path.charAt(i) == 'R'){
                direction = RightHandExplorer.changeDirection(direction, right);
            }

            if (!this.matrix.get(row)[col]){
                System.out.println("Incorrect Path!");
                return;
            }
        }

        // Conditionals to check if we arrived at the exit after traversing through and performing the path

        if (row == exitRow && col == exitCol) {
            System.out.println("Correct Path!");
        }
        else {
            System.out.println("Incorrect Path!");
        }
    }

    // Print Maze Method

    public void printMaze() {
        for (boolean[] row : this.matrix) {
            for (boolean i : row) {
                if (i){
                    System.out.print(" ");
                }
                else{
                    System.out.print("#");
                }
            }
            System.out.print(System.lineSeparator());
        }
    }
}
