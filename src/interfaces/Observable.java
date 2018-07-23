package interfaces;

import javafx.collections.ObservableList;
import models.Morceau;

public interface Observable {
    public void addObserver(Observer o);
    public void delOberverList();
    public void notifyObserver(ObservableList<Morceau> list);
}
