package ca.mcmaster.se2aa4.mazerunner;

import java.util.List;

public class RightHandExplorer implements AlgorithmExplorer {

    private static final char[] DIRECTIONS = {'N', 'E', 'S', 'W'};

    @Override

    public String searchPath(Maze maze) {

        StringBuilder path = new StringBuilder();

        // Starting matrix, row, and col

        List<boolean[]> matrix = maze.getMatrix();
        int row = maze.getWestEntry();
        int col = 0;

        // Variables to manage direction

        char direction = DIRECTIONS[1];
        boolean right = true; //for readability
        boolean left = false; // for readability

        /* Right Hand Algorithm
        (Idea is to "hug" the right wall)
        If you cant go right, go forwards
        If you cant go right or forwards, go left */

        while ((0 <= col && col < matrix.getFirst().length - 1) && (1 <= row && row < matrix.size()-1)){
            switch (direction){
            case 'E':
                if (!matrix.get(row+1)[col]){ // Cant go right
                    if (!matrix.get(row)[col+1]){ // Cant go right or forwards
                        direction = changeDirection(direction, left);
                        path.append("L");
                    }
                    else {
                        path.append("F");
                        col += 1;
                    }
                }
                else{
                    direction = changeDirection(direction, right);
                    row += 1;
                    path.append("RF");
                }
                break;

            case 'W':
                if (!matrix.get(row-1)[col]){ // Cant go right
                    if (!matrix.get(row)[col-1]){ // Cant go right or forwards
                        direction = changeDirection(direction, left);
                        path.append("L");
                    }
                    else {
                        path.append("F");
                        col -= 1;
                    }
                }
                else{
                    direction = changeDirection(direction, right);
                    row -= 1;
                    path.append("RF");
                }
                break;

            case 'N':
                if (!matrix.get(row)[col+1]){ // Cant go right
                    if (!matrix.get(row-1)[col]){ // Cant go right or forwards
                        direction = changeDirection(direction, left);
                        path.append("L");
                    }
                    else {
                        path.append("F");
                        row -= 1;
                    }
                }
                else{
                    direction = changeDirection(direction, right);
                    col += 1;
                    path.append("RF");
                }
                break;
            case 'S':
                if (!matrix.get(row)[col-1]){ // Cant go right
                    if (!matrix.get(row+1)[col]){ // Cant go right or forwards
                        direction = changeDirection(direction, left);
                        path.append("L");
                    }
                    else {
                        path.append("F");
                        row += 1;
                    }
                }
                else{
                    direction = changeDirection(direction, right);
                    col -= 1;
                    path.append("RF");
                }
                break;
            }
        }

        return path.toString();
    }

    private static char changeDirection(char direction, boolean right) {
        char newDirection = ' ';
        if (right) {
            newDirection = switch (direction) {
                case 'N' -> 'E';
                case 'E' -> 'S';
                case 'S' -> 'W';
                case 'W' -> 'N';
                default -> newDirection;
            };
        } else {
            newDirection = switch (direction) {
                case 'N' -> 'W';
                case 'E' -> 'N';
                case 'S' -> 'E';
                case 'W' -> 'S';
                default -> newDirection;
            };
        }
        return newDirection;
    }
}