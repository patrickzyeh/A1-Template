package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static String filePath = null;
    private static String mazePath;
    private static final List<boolean[]> matrix = new ArrayList<>();

    public static void main(String[] args) {

        // CLI Parsing

        logger.info("**** Reading Command-Line Arguments");

        Options options = new Options();
        options.addOption("i", true, "Flag to indicate to program that a maze filepath will be provided"); // -i flag
        options.addOption(Option.builder("p").hasArgs().valueSeparator(' ')
                .desc("Flag to indicate to program to validate a path sequence")// -p flag
                .build());

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args); // Parses the command line arguments accordingly
            if (!cmd.hasOption("i")) {
                throw new ParseException("Error parsing -i flag");
            }
            if (cmd.hasOption("p")){
                String[] mazePathArray = cmd.getOptionValues("p");
                mazePath = String.join("", mazePathArray);
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

            // Create a 2D Arraylist of int which will be converted to a maze object

            while ((line = reader.readLine()) != null) { // Read file line by line logging and adding to Arraylist

                // Handles case where reader reads an empty line (Straight Path)

                boolean[] row = (!line.isEmpty()) ? new boolean[line.length()]
                        : new boolean[matrix.getLast().length];

                if (line.isEmpty()) {
                    for (int idx = 0; idx < row.length; idx++) {
                        row[idx] = true;
                        logger.trace("PASS ");
                    }
                    logger.trace(System.lineSeparator());

                } else {
                    for (int idx = 0; idx < row.length; idx++) {
                        if (line.charAt(idx) == '#') {
                            row[idx] = false;
                            logger.trace("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            row[idx] = true;
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

        // Initialize Maze object

        Maze maze = new Maze(matrix);
        AlgorithmExplorer explorer = new RightHandExplorer();

        // CAN ONLY VERIFY CANONICAL PATH

        if (mazePath != null){
            maze.verifyPath(mazePath);
        }
        else{
            Path path = explorer.findPath(maze);
            path.displayFactorized();
            path.displayCanonical();
        }

        logger.info("**** Computing path");
        logger.debug("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
