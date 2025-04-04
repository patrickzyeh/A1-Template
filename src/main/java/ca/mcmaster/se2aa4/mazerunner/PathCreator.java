package ca.mcmaster.se2aa4.mazerunner;

// Creates a path string based on observing move command history

public class PathCreator implements HistoryObserver {
    private final StringBuilder pathString;

    public PathCreator() {
        this.pathString = new StringBuilder();
    }

    // Updates for undo commands
    @Override
    public void remove() {
        if (pathString.length() > 0) {
            pathString.deleteCharAt(pathString.length() - 1);
        }
    }

    // Updates for commands
    @Override
    public void add(char c) {
        pathString.append(c);
    }

    @Override
    public String toString() {
        return pathString.toString();
    }
}
