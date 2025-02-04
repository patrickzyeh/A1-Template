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
