package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class Maze {

    // Maze Attributes

    private final List<boolean[]> matrix;

    // Maze Methods

    public Maze(List<boolean[]> matrix) {
        this.matrix = matrix;
    }

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
}
