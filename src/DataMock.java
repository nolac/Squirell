import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Morceau;

public class DataMock {
    ObservableList<Morceau> listeTest;
    public DataMock(){
        initListeTest();
    }
    private void initListeTest(){
        listeTest=FXCollections.observableArrayList(
                new Morceau("arizona","lantin","alain"),
                new Morceau("les tatars","devogel","jacques"),
                new Morceau("cavale","vidaud","nicolas"),
                new Morceau("the sands of time","tavernier","christian"),
                new Morceau("jus de cailloux","kregar","Stephane"),
                new Morceau("ready made","saraute","laurent"),
                new Morceau("a la poursuite du temps","coudrais","alain"),
                new Morceau("heaven in my heart","tavernier","christian"),
                new Morceau("yes sir","caplier","jean-jacques"),
                new Morceau("bolero militaire","devogel","jacques"),
                new Morceau("chants d'ouest","charles","jean-jacques"),
                new Morceau("miroirs de reve","lantin","alain")
        );
    }
    public ObservableList<Morceau> getListeTest(){
        return listeTest;
    }

}
