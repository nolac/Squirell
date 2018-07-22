package views;

import controller.Squirell;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.DBController;
import models.Morceau;
import models.Morceau.Compositeur;

public class CreateMorceau_VC {
    /*
            ne fait qu'une et une seule chose : creer un nouveau morceau et l'envoyer dans la bdd
     */
    @FXML
    TextField _titre;
    @FXML
    TextField c_nom;
    @FXML
    TextField c_prenom;
    @FXML
    Button _addMorceau;
    @FXML
    public void initialize(){
    }



    private Squirell mainApp;
    public void onAddMorceau(){
        Morceau m=new Morceau(_titre.getText(),new Compositeur(c_nom.getText(),c_prenom.getText()));

        if(!_titre.getText().isEmpty()&&!c_nom.getText().isEmpty()&&!c_prenom.getText().isEmpty()){
            System.out.println(m.toString());
            mainApp.getDb().addMorceau(_titre.getText(),c_nom.getText(),c_prenom.getText());
        }
        else{
            Alert a = new Alert(Alert.AlertType.INFORMATION,"vous n'avez pas saisi de morceau");
            a.showAndWait();
        }

        _titre.setText("");
        c_nom.setText("");
        c_prenom.setText("");
    }

    public void setMainApp(Squirell app){
        this.mainApp=(Squirell)app;
        System.out.println(this.getClass()+" {main setted :"+this.mainApp+"}");

    }
}
