package ca.mcmaster.se2aa4.mazerunner;

public class RightCommand implements MoveCommand {
    private final Marker marker;

    public RightCommand(Marker marker) {
        this.marker = marker;
    }

    @Override
    public void execute() {
        marker.turnRight();
    }

    @Override
    public void undo() {
        marker.turnLeft();
    }
}
