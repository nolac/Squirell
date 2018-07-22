package interfaces;

import javafx.collections.ObservableList;
import models.Morceau;

public interface Observer {
    public void update(ObservableList<Morceau> list);
}
