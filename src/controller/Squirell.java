package controller;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.DBController;
import models.Morceau;
import views.ArchiveListView_VC;
import views.CreateMorceau_VC;

import java.io.IOException;

/*
*
*  controller.Squirell
*  prototype de programme afin de gerer les archives d'un orchestre:
*   inserer les morceaux dans une base de données
*   recuperer les morceaux de la base de données
*
*/
public class Squirell extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("[start method]");
        this.stage=primaryStage;
        this.initMainView();
        this.initTableau();
        this.initCreateMorceau();
        System.out.println("views initialized ");
        System.out.println("connect to database");
        this.db=new DBController();
        System.out.println("[end start method]");
    }


    BorderPane mainCont;
    Stage stage;
    ObservableList<Morceau> listMorceau;
    DBController db;

    public Squirell(){
        this.db=new DBController();
        this.listMorceau=db.getListeMorceau();
        System.out.println("{armoire :\n"+this.listMorceau+"\n}");
    }

    private void initMainView(){
        System.out.println("\t["+this.getClass()+" initMainView method]");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Squirell.class.getResource("../views/mainView.fxml"));
        try {
            mainCont=loader.load();
            Scene sc=new Scene(mainCont);
            stage.setScene(sc);
            stage.show();
        }catch (IOException e){
            e.getCause();
        }
        System.out.println(("\t[end "+this.getClass()+" initMainView method]"));
    }

    private void initTableau(){
        System.out.println("\t["+this.getClass()+" initTableau method]");
        AnchorPane tabPan;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Squirell.class.getResource("../views/archiveListView.fxml"));
        try {
            tabPan=loader.load();
            this.mainCont.setCenter(tabPan);
        }catch (IOException e){
            e.getCause();
        }
        ArchiveListView_VC tvvc = loader.getController();
        tvvc.setMainApp(this);
        tvvc.setListeMorceaux(this.listMorceau);
        System.out.println("\t[end "+this.getClass()+" initTableau method]");
    }

    private void initCreateMorceau(){
        AnchorPane createPan;
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Squirell.class.getResource("../views/createMorceau.fxml"));
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
    public DBController getDb(){
        return this.db;
    }
}
