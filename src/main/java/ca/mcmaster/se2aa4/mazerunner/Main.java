package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static FileParser fileParser = new FileParser();
    private static String filePath = null;
    private static String mazePath;

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
            if (cmd.hasOption("p")) {
                String[] mazePathArray = cmd.getOptionValues("p");
                mazePath = String.join("", mazePathArray);
            }
            filePath = cmd.getOptionValue("i"); // Stores file path
            logger.trace("****** Parsed file name: " + filePath);
        } catch (ParseException e) {
            logger.error("/!\\\\ An error has occurred while parsing command line arguments /!\\");
            System.exit(1); // Terminate program if errors with parsing
        }

        // Initialize Maze object

        Maze maze = fileParser.parseFile(filePath);
        maze.printMaze();
        AlgorithmExplorer explorer = new RightHandExplorer();

        // Verifies or computes path accordingly to supplied flag

        if (mazePath != null) {
            logger.info("**** Verifying");
            if (explorer.verifyPath(maze, mazePath)) {
                System.out.println("Correct Path!");
                logger.info("Path verified!");
            } else {
                System.out.println("Incorrect path!");
                logger.info("Path not verified!");
            }
        } else {
            logger.info("**** Computing path");
            Path path = explorer.findPath(maze);
            System.out.println(path.getFactorized());
        }

        logger.info("** End of MazeRunner");
    }
}
