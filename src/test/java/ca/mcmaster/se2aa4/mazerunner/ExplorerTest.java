package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExplorerTest {

    private FileParser fileParser;
    private AlgorithmExplorer explorer;

    @BeforeEach
    public void initialize() {
        fileParser = new FileParser();
        explorer = new RightHandExplorer();
    }

    @Test
    public void straightMaze() {
        Maze maze = fileParser.parseFile("./examples/straight.maz.txt");
        Path path = explorer.findPath(maze);
        assertEquals(path.getFactorized(), "4F ");
    }

    // @Test
    // public void verifyStraightMaze() {
    // Maze maze = fileParser.parseFile("./examples/straight.maz.txt");
    // Path path = explorer.findPath(maze);
    // assertTrue(explorer.verifyPath(maze, path.getFactorized()));
    // }

    @Test
    public void tinyMaze() {
        Maze maze = fileParser.parseFile("./examples/tiny.maz.txt");
        Path path = explorer.findPath(maze);
        assertEquals(path.getFactorized(), "5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F ");
    }

    // @Test
    // public void verifyTinyMaze() {
    // Maze maze = fileParser.parseFile("./examples/tiny.maz.txt");
    // Path path = explorer.findPath(maze);
    // assertTrue(explorer.verifyPath(maze, path.getFactorized()));
    // }

    @Test
    public void directMaze() {
        Maze maze = fileParser.parseFile("./examples/direct.maz.txt");
        Path path = explorer.findPath(maze);
        assertEquals(path.getFactorized(), "F R 2F L 3F R F L F R F L 2F ");
    }

    // @Test
    // public void verifyDirectMaze() {
    // Maze maze = fileParser.parseFile("./examples/direct.maz.txt");
    // Path path = explorer.findPath(maze);
    // assertTrue(explorer.verifyPath(maze, path.getFactorized()));
    // }

}
