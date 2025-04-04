package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class CommandHistory {
    private final ArrayList<MoveCommand> history = new ArrayList<>();

    public void push(MoveCommand command) {
        history.add(command);
        command.execute();
    }

    public void pop() {
        history.remove(history.size() - 1).undo();
    }

    public void clear() {
        history.clear();
    }
}
