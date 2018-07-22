package models;


import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBController {
    private String url;
    private ArrayList<Morceau> listeMorceau;
    Connection con;

    public DBController(){
        this.url="/Users/nico/Desktop/testDBArchives.db";
        this.listeMorceau=new ArrayList<Morceau>();

        try{
            con=DriverManager.getConnection("jdbc:sqlite:"+url);
            System.out.println("db connected");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void initDB(){

    }
}
