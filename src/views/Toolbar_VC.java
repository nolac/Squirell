package views;

import controller.Squirell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Toolbar_VC {
    @FXML
    Button _armoire, _add, _editMorceau, _addPart, _addProgramme, _openProgramme;
    @FXML
    ImageView i_armoire, i_add, i_editMorceau, i_addPart, i_addProgramme, i_openProgramme;
    @FXML
    TextField _searchTitle, _searchComposer;

    @FXML
    public void initialize() {

        initBoutonsImage();
        _searchTitle.textProperty().addListener(((observable, oldValue, newValue) -> {
            this.mainApp.getDb().rechercher("titre", newValue);
            /*
                methode db.rechercher a réécrire afin d'y inclure les jointure avec compositeur nom
                pour pouvoir faire une recherche sur le compositeur
            */
        }
        ));
        _searchComposer.textProperty().addListener(((observable, oldValue, newValue) -> {
            this.mainApp.getDb().rechercher("compositeur", newValue);
        }));
    }

    private Squirell mainApp;

    public void setMainApp(Squirell main) {
        this.mainApp = main;
    }

    private void initBoutonsImage() {
        int nbBut = 6;
        String[] urls = {"armoire.png", "add.png", "edit.png", "addPart.png", "addProgramme.png", "openProgramme.png"};
        ImageView[] buts = {i_armoire, i_add, i_editMorceau, i_addPart, i_addProgramme, i_openProgramme};
        for (int i = 0; i < 6; i++) {
            buts[i].setImage(new Image(getClass().getResourceAsStream("./images/" + urls[i])));
            System.out.println("----- image " + urls[i] + " : OK");
        }

    }

    public void add() {
        System.out.println("ajouter un morceau dans la base de données");
    }
}
