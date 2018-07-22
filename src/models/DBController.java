package models;


import interfaces.Observable;
import interfaces.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class DBController implements Observable {
    private String url;
    //private ArrayList<Morceau> listeMorceau;
    //private Connection con;
    private Statement stat;

    public DBController(){
        System.out.println("["+this.getClass() +" : consctructor]");
        this.listeObservers=new ArrayList<>();
        this.url="/Users/nico/Desktop/testSquirell.db";
        //this.listeMorceau=new ArrayList<Morceau>();
        this.initDB();
        System.out.println("[end constructor "+this.getClass()+"]");
    }



    private void initDB(){
        System.out.println("\t[init database method]");
        try{
            Connection con=DriverManager.getConnection("jdbc:sqlite:"+url);
            System.out.println("\t\tdb connected");
            stat=con.createStatement();
            System.out.println("\t\tstatement initialized");
            addTableMorceau();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("\t[end init database method]");
    }

    private void addTableMorceau() throws SQLException{
        System.out.println("\t\t[creation table Morceau]");
        String sql=("CREATE TABLE IF NOT EXISTS morceau (" +
                "id integer primary key autoincrement," +
                "titre text," +
                "c_nom varchar(50)," +
                "c_prenom varchar(50)," +
                "description text" +
                ");");
            stat.execute(sql);
        System.out.println("\t\t[table morceau OK]");
    }

    /*
    private void addTableCompositeur() throws SQLException{
        String sql=("CREATE TABLE IF NOT EXISTS compositeur(" +
                "id integer primary key autoincrement," +
                "nom varchar(50)," +
                "prenom varchar(50));");
            stat.execute(sql);
    }
    */

    public void addMorceau(String titreMorceau,String nomCompositeur,String prenomCompositeur){
        String sql = ("INSERT INTO morceau(titre,c_nom,c_prenom) VALUES" +
                "('" + titreMorceau + "','" + nomCompositeur + "','" +prenomCompositeur+ "');");


        try {
           stat.execute(sql);
           // System.out.println(titreMorceau+" "+nomCompositeur+" "+prenomCompositeur+" ajouté a la base");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        notifyObserver();
    }

    /*
    public void addMorceau(Morceau m){
        String t=m.getTitre();
        String c_n=m.getCompositeur().nomProperty().get();
        String c_p=m.getCompositeur().prenomProperty().get();
        this.addMorceau(t,c_n,c_p);
    }
    */

    public ObservableList<Morceau> getListeMorceau(){
        ObservableList<Morceau> list=FXCollections.observableArrayList();
        ResultSet rs;
        try {
            stat.execute("SELECT * FROM morceau");
            rs=stat.getResultSet();
            while (rs.next()){
                String c_n=rs.getString("c_nom");
                String c_p=rs.getString("c_prenom");
                String t=rs.getString("titre");
                list.add(new Morceau(t,c_n,c_p));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /////////////  interface Observable  /////
    private ArrayList<Observer> listeObservers;
    @Override
    public void addObserver(Observer O) {
        listeObservers.add(O);
        notifyObserver();
    }

    @Override
    public void delOberverList() {
        this.listeObservers=new ArrayList<>();
    }

    @Override
    public void notifyObserver() {
        for (Observer listeObserver : listeObservers) {
            listeObserver.update(this.getListeMorceau());
        }
    }
}
