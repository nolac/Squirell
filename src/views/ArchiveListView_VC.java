package views;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Morceau;

public class ArchiveListView_VC {
    @FXML
    TableView<Morceau> _archives;
    @FXML
    TableColumn<Morceau,String> _titre;
    @FXML
    TableColumn<Morceau,String> _compositeur;
    @FXML
    public void initialize(){
        _titre.setCellValueFactory(d->d.getValue().titreProperty());
        _compositeur.setCellValueFactory(d->d.getValue().getCompositeur().identité());

        /// selection d'un morceau dans la liste//
        _archives.getSelectionModel().selectedItemProperty().addListener(
                //pour l'instant affiche titre et compositeur dans la console//
                // TODO : creer une alert //
                ((observable, oldValue, newValue) -> System.out.println(newValue.toString()))
        );
    }

    private Application mainApp;
    private ObservableList<Morceau> listeMorceaux;

    public void setMainApp(Application mainApp){
        this.mainApp=mainApp;
        System.out.println(this.getClass().toString() +" {main setted : "+this.mainApp+"}");
    }
    public void setListeMorceaux(ObservableList<Morceau> liste){
        _archives.setItems(liste);
    }
}
