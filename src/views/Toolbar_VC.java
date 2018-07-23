package views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Toolbar_VC {
    @FXML
    Button _armoire,_add,_editMorceau,_addPart,_addProgramme,_openProgramme;
    @FXML
    ImageView i_armoire,i_add,i_editMorceau,i_addPart,i_addProgramme,i_openProgramme;
    @FXML
    TextField _search;
    @FXML
    public void initialize(){
        initBoutonsImage();
        _search.textProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue));
    }

    private void initBoutonsImage(){
        int nbBut = 6;
        String[] urls={"armoire.png","add.png","edit.png","addPart.png","addProgramme.png","openProgramme.png"};
        ImageView[] buts={i_armoire,i_add,i_editMorceau,i_addPart,i_addProgramme,i_openProgramme};
        for(int i=0;i< 6;i++){
            buts[i].setImage(new Image(getClass().getResourceAsStream("./images/"+urls[i])));
            System.out.println("----- image "+urls[i]+" : OK");
        }

    }

    public void add(){
        System.out.println("ajouter un morceau dans la base de données");
    }
}
