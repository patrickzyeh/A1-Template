package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PathTest {

    // Factorized

    @Test
    public void noConsequetiveFactorized() {
        Path path = new Path("FRLFRLFRL");
        assertEquals(path.getFactorized(), "F R L F R L F R L ");
    }

    @Test
    public void consequetiveFactorized() {
        Path path = new Path("FFFRRRLLL");
        assertEquals(path.getFactorized(), "3F 3R 3L ");
    }

    @Test
    public void emptyFactorized() {
        Path path = new Path("");
        assertEquals(path.getFactorized(), "");
    }

    // Canonical

    @Test
    public void canonical() {
        Path path = new Path("FFFRRLRLLFR");
        assertEquals(path.getCanonical(), "FFF RR L R LL F R");
    }

    @Test
    public void emptyCanonical() {
        Path path = new Path("");
        assertEquals(path.getCanonical(), "");
    }
}
