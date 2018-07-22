package interfaces;

public interface Observable {
    public void addObserver(Observer O);
    public void delOberverList();
    public void notifyObserver();
}
