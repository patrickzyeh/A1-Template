package ca.mcmaster.se2aa4.mazerunner;

public interface MoveCommand {
    public abstract void execute();

    public abstract void undo();
}
