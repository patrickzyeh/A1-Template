package ca.mcmaster.se2aa4.mazerunner;

public class LeftCommand implements MoveCommand {
    private final Marker marker;

    public LeftCommand(Marker marker) {
        this.marker = marker;
    }

    @Override
    public void execute() {
        marker.turnLeft();
    }

    @Override
    public void undo() {
        marker.turnRight();
    }

    @Override
    public char getCommandChar() {
        return 'L';
    }
}
