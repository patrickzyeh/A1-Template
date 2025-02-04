package ca.mcmaster.se2aa4.mazerunner;

public interface AlgorithmExplorer {
    public Path findPath(Maze maze);
    public boolean verifyPath(Maze maze, String path);
}
