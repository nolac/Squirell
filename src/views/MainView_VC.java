package views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;

public class MainView_VC {
    @FXML
    MenuBar _menuBar;
    @FXML
    public void initialize(){
        System.out.println("\t["+this.getClass()+" initialize method]");
        _menuBar.useSystemMenuBarProperty().setValue(true);
        System.out.println(("\t[end initialize method "+this.getClass()));
    }


    public void about(){
        System.out.println("\t\t["+this.getClass()+" about method]");
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("a propos de SQUIRELL");
        a.setHeaderText("ABOUT SQUIRELL");
        a.setContentText("SQUIRELLE was wroten by N.Vidaud to explore Orchestra's archives of parts.\n" +
                "It's made on java, primary worked on Mac OS but would be working on every System running JVM\n" +
                "Database in made in a SQLITE file.It doesn't need external or localhost server " +
                "every parts files HAVE TO BE on the same machine");
        a.showAndWait();
        System.out.println("\t\t[end "+this.getClass()+" about method]");
    }
}
