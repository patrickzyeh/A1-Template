package ca.mcmaster.se2aa4.mazerunner;

public class ForwardCommand implements MoveCommand {
    private final Marker marker;

    public ForwardCommand(Marker marker) {
        this.marker = marker;
    }

    @Override
    public void execute() {
        marker.moveForward();
    }

    @Override
    public void undo() {
        marker.moveBackward();
    }

    @Override
    public char getCommandChar() {
        return 'F';
    }
}
