package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static String filePath;

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
            while ((line = reader.readLine()) != null) { // Read file line by line
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occured while reading from file /!\\");
        }
        logger.info("**** Computing path");
        logger.debug("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
