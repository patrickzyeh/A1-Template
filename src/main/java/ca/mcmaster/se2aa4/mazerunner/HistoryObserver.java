package ca.mcmaster.se2aa4.mazerunner;

public interface HistoryObserver {

    public abstract void remove();

    public abstract void add(char c);
}
