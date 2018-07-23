package views;

import interfaces.Observer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Morceau;

public class ArchiveListView_VC implements Observer {
    @FXML
    TableView<Morceau> _archives;
    @FXML
    TableColumn<Morceau,String> _titre;
    @FXML
    TableColumn<Morceau,String> _compositeur;
    @FXML
    public void initialize(){
        System.out.println("\t[init "+this.getClass()+" method]");
        _titre.setCellValueFactory(d->d.getValue().titreProperty());
        _compositeur.setCellValueFactory(d->d.getValue().getCompositeur().identité());

        /// selection d'un morceau dans la liste//
        _archives.getSelectionModel().selectedItemProperty().addListener(
                //pour l'instant affiche titre et compositeur dans la console//
                // TODO : creer une alert //
                ((observable, oldValue, newValue) -> System.out.println(newValue.toString()))
        );
        System.out.println("\t[end initialize method "+this.getClass()+"]");
    }

    public ArchiveListView_VC(){
        System.out.println("["+this.getClass()+" constructor]");
        System.out.println("[end "+this.getClass() +" construtor]");
    }

    private Application mainApp;
    private ObservableList<Morceau> listeMorceaux;

    public void setMainApp(Application mainApp){
        System.out.println("\t\t["+this.getClass()+" setMainApp method]");
        this.mainApp=mainApp;
        System.out.println("\t\t"+this.getClass().toString() +" {main setted : "+this.mainApp+"}");
        System.out.println("\t\t[end "+this.getClass()+" setMainApp method]");
    }

    public void setListeMorceaux(ObservableList<Morceau> liste){
        System.out.println("\t\t["+this.getClass()+" setListeMorceau method]");
        _archives.setItems(liste);
        System.out.println(("\t\t[end "+this.getClass()+" setListeMorceau method]"));
    }

    @Override
    public void update(ObservableList<Morceau> list) {
        _archives.setItems(list);
    }
}
