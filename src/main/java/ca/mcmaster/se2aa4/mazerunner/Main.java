package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static String fileName;

    public static void main(String[] args) {

        // CLI Parsing

        System.out.println("**** Reading Command-Line Arguements");

        Options options = new Options();
        options.addOption("i", true, "Flag to indicate to program that a maze filepath will be provided"); // -i flag
        options.addOption("p", true, "Flag to indicate to program to validate a path sequence"); // -p flag
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args); // Parses the command line arguements accordingly
            fileName = cmd.getOptionValue("i");
            System.out.println("****** Parsed file name: " + fileName);
        } catch (ParseException e) {
            System.err.println("An error has occurred");
        }

        // Reading parsed file

        System.out.println("** Starting Maze Runner");
        try {
            System.out.println("**** Reading the maze from file " + fileName);
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
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
            System.err.println("/!\\ An error has occured while reading from file /!\\");
        }
        System.out.println("**** Computing path");
        System.out.println("PATH NOT COMPUTED");
        System.out.println("** End of MazeRunner");
    }
}
