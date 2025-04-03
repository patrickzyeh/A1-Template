package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileParser {

    private static final Logger logger = LogManager.getLogger();
    private final List<boolean[]> matrix;

    public FileParser() {
        matrix = new ArrayList<>();
    }

    public Maze parseFile(String filePath) {
        // Reading parsed file

        logger.info("** Starting Maze Runner");
        try {
            logger.info("**** Reading the maze from file " + filePath);
            BufferedReader reader = new BufferedReader(new FileReader(filePath)); // Read from file path supplied
            String line;

            int col = reader.readLine().length();
            boolean[] row = new boolean[col];
            for (int idx = 0; idx < col; idx++) {
                row[idx] = false;
                logger.trace("WALL ");
            }
            logger.trace(System.lineSeparator());
            matrix.add(row);

            // Create a 2D Arraylist of int which will be converted to a maze object

            while ((line = reader.readLine()) != null) { // Read file line by line logging and adding to Arraylist

                row = new boolean[col];

                if (line.isEmpty()) {
                    for (int idx = 0; idx < col; idx++) {
                        row[idx] = true;
                        logger.trace("PASS ");
                    }
                    logger.trace(System.lineSeparator());

                } else {
                    for (int idx = 0; idx < col; idx++) {
                        if (idx < line.length()) {
                            if (line.charAt(idx) == '#') {
                                row[idx] = false;
                                logger.trace("WALL ");
                            } else if (line.charAt(idx) == ' ') {
                                row[idx] = true;
                                logger.trace("PASS ");
                            }
                        } else {
                            row[idx] = true; // Pad with true is index is shorter than line
                            logger.trace("PASS ");
                        }
                    }
                    logger.trace(System.lineSeparator());
                }
                matrix.add(row);
            }
            reader.close();

        } catch (Exception e) {
            logger.error("/!\\ An error has occurred while reading from file /!\\");
        }

        return new Maze(matrix);
    }

}
