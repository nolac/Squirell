import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.DBController;
import models.Morceau;
import views.ArchiveListView_VC;
import views.CreateMorceau_VC;

import javax.sound.midi.ControllerEventListener;
import java.io.IOException;

/*
*
*  Squirell
*  prototype de programme afin de gerer les archives d'un orchestre:
*   inserer les morceaux dans une base de données
*   recuperer les morceaux de la base de données
*
*/
public class Squirell extends Application {

    public Squirell(){
        this.listMorceau=new DataMock().getListeTest();

    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage=primaryStage;
        this.initMainView();
        this.initTableau();
        this.initCreateMorceau();
        System.out.println("views initialized ");
        System.out.println("connect to database");
        this.db=new DBController();
    }


    BorderPane mainCont;
    Stage stage;
    ObservableList<Morceau> listMorceau;
    DBController db;

    private void initMainView(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Squirell.class.getResource("./views/mainView.fxml"));
        try {
            mainCont=loader.load();
            Scene sc=new Scene(mainCont);
            stage.setScene(sc);
            stage.show();
        }catch (IOException e){
            e.getCause();
        }
    }

    private void initTableau(){
        AnchorPane tabPan;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Squirell.class.getResource("./views/archiveListView.fxml"));
        try {
            tabPan=loader.load();
            this.mainCont.setCenter(tabPan);
        }catch (IOException e){
            e.getCause();
        }
        ArchiveListView_VC tvvc = loader.getController();
        tvvc.setMainApp(this);
        tvvc.setListeMorceaux(this.listMorceau);
    }

    private void initCreateMorceau(){
        AnchorPane createPan;
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Squirell.class.getResource("./views/createMorceau.fxml"));
        try{
            createPan=loader.load();
            this.mainCont.setRight(createPan);
        }catch (IOException e){
            e.getCause();
        }
        CreateMorceau_VC cmvc = loader.getController();
        cmvc.setMainApp(this);
    }

    public void initMenuBar(){

    }

    public ObservableList getObservableList(){
        return this.listMorceau;
    }
}
