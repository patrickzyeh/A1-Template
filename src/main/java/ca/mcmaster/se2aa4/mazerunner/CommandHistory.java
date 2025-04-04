package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class CommandHistory implements HistoryNotifier {
    private final ArrayList<MoveCommand> history = new ArrayList<>();
    private final ArrayList<HistoryObserver> observers = new ArrayList<>();

    public void push(MoveCommand command) {
        history.add(command);
        command.execute();
        notifyObservers(command.getCommandChar());
    }

    public void pop() {
        history.remove(history.size() - 1).undo();
        notifyObserversUndo();
    }

    public void clear() {
        history.clear();
    }

    @Override
    public void attachObserver(HistoryObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(HistoryObserver observer) {
        int index = observers.indexOf(observer);
        if (index != -1) {
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers(char c) {
        for (HistoryObserver observer : observers) {
            observer.add(c);
        }
    }

    @Override
    public void notifyObserversUndo() {
        for (HistoryObserver observer : observers) {
            observer.remove();
        }
    }

}
