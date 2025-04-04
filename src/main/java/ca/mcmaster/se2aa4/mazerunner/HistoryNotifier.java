package ca.mcmaster.se2aa4.mazerunner;

public interface HistoryNotifier {
    public abstract void attachObserver(HistoryObserver observer);

    public abstract void detachObserver(HistoryObserver observer);

    public abstract void notifyObservers(char c);

    public abstract void notifyObserversUndo(); // Need a seperate method to notify UNDO commands
}
