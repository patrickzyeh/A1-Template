package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static String filePath;
    private static Maze maze;

    public static void main(String[] args) {

        // CLI Parsing

        logger.info("**** Reading Command-Line Arguments");

        Options options = new Options();
        options.addOption("i", true, "Flag to indicate to program that a maze filepath will be provided"); // -i flag
        options.addOption("p", true, "Flag to indicate to program to validate a path sequence"); // -p flag
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args); // Parses the command line arguments accordingly
            if (!cmd.hasOption("i")) {
                throw new ParseException("Error parsing -i flag");
            }
            filePath = cmd.getOptionValue("i"); // Stores file path
            logger.trace("****** Parsed file name: " + filePath);
        } catch (ParseException e) {
            logger.error("/!\\\\ An error has occurred while parsing command line arguments /!\\");
            System.exit(1); // Terminate program if errors with parsing
        }

        // Reading parsed file

        logger.info("** Starting Maze Runner");
        try {
            logger.info("**** Reading the maze from file " + filePath);
            BufferedReader reader = new BufferedReader(new FileReader(filePath)); // Read from file path supplied
            String line;

            // Create a 2D Arraylist of bool which will be converted to a maze object

            List<int[]> matrix = new ArrayList<>();

            while ((line = reader.readLine()) != null) { // Read file line by line logging and adding to Arraylist

                int[] row = new int[line.length()];

                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        row[idx] = 0;
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        row[idx] = 1;
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
                matrix.add(row);
            }
            reader.close();

            // Initialize Maze object

            maze = new Maze(matrix);
            maze.printMaze();

        } catch (Exception e) {
            logger.error("/!\\ An error has occured while reading from file /!\\");
        }

        logger.info("**** Computing path");
        logger.debug("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
