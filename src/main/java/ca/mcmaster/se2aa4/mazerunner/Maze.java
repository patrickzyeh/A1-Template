package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class Maze {
    private final List<int[]> matrix;

    public Maze(List<int[]> matrix) {
        this.matrix = matrix;
    }

    public void printMaze() {
        for (int i = 0; i < matrix.size(); i++) {
            int[] row = matrix.get(i);
            for (int j = 0; j < row.length; j++) {
                System.out.print(row[j]);
            }
            System.out.print(System.lineSeparator());
        }
    }

    public boolean verifyPath(String path) {
        return true;
    }

    public int[] getEntries() {
        int[] entries = new int[2];
        return entries;
    }
}
