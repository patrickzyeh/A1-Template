package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {

    private static final String FILE = "./examples/small.maz.txt";
    private static final FileParser fileParser = new FileParser();
    private Maze maze;

    @BeforeEach
    public void initializeMaze() {
        maze = fileParser.parseFile(FILE);
    }

    @Test
    public void westEntry() {
        assertEquals(8, maze.getWestEntry());
    }

    @Test
    public void eastEntry() {
        assertEquals(5, maze.getEastEntry());
    }

}
