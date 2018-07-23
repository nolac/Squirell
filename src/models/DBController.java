package models;


import interfaces.Observable;
import interfaces.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DBController implements Observable {
    private String url;
    private ArrayList<Observer> listeObservers;
    //private ArrayList<Morceau> listeMorceau;
    //private Connection con;
    //private Statement stat;

    public DBController() {
        System.out.println("[" + this.getClass() + " : consctructor]");
        this.listeObservers = new ArrayList<>();
        this.url = "/Users/nico/Desktop/testSquirell.db";
        //this.listeMorceau=new ArrayList<Morceau>();
        //this.initDB();
        addTableMorceau();
        System.out.println("[end constructor " + this.getClass() + "]");
    }

    private Connection connection() throws SQLException {
        Connection con = null;

        con = DriverManager.getConnection("jdbc:sqlite:" + url);

        return con;
    }

    /*
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
    */
    private void addTableMorceau() {
        System.out.println("\t\t[creation table Morceau]");
        String sql = ("CREATE TABLE IF NOT EXISTS morceau (" +
                "id integer primary key autoincrement," +
                "titre text," +
                "c_nom varchar(50)," +
                "c_prenom varchar(50)," +
                "description text" +
                ");");
        try {
            connection().createStatement().execute(sql);
            if (connection() != null) {
                connection().close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    public void addMorceau(String titreMorceau, String nomCompositeur, String prenomCompositeur) {
        String sql = ("INSERT INTO morceau(titre,c_nom,c_prenom) VALUES" +
                "(\"" + titreMorceau + "\",\"" + nomCompositeur + "\",\"" + prenomCompositeur + "\");");


        try {
            connection().createStatement().execute(sql);
            connection().close();
            // System.out.println(titreMorceau+" "+nomCompositeur+" "+prenomCompositeur+" ajouté a la base");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //notifyObserver();
        getListeMorceau();
    }

    /*
    public void addMorceau(Morceau m){
        String t=m.getTitre();
        String c_n=m.getCompositeur().nomProperty().get();
        String c_p=m.getCompositeur().prenomProperty().get();
        this.addMorceau(t,c_n,c_p);
    }
    */

    /*
    public ObservableList<Morceau> getListeMorceau(){
        ObservableList<Morceau> list=FXCollections.observableArrayList();
        try {
            Statement stat = connection().createStatement();
            stat.execute("SELECT * FROM morceau");
            ResultSet rs = stat.getResultSet();
            while (rs.next()){
                String c_n=rs.getString("c_nom");
                String c_p=rs.getString("c_prenom");
                String t=rs.getString("titre");
                list.add(new Morceau(t,c_n,c_p));
            }
            connection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    */
    public void getListeMorceau() {
        ObservableList<Morceau> list = FXCollections.observableArrayList();
        try {
            Statement stat = connection().createStatement();
            stat.execute("SELECT * FROM morceau");
            ResultSet rs = stat.getResultSet();
            while (rs.next()) {
                String c_n = rs.getString("c_nom");
                String c_p = rs.getString("c_prenom");
                String t = rs.getString("titre");
                list.add(new Morceau(t, c_n, c_p));
            }
            stat.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        notifyObserver(list);
    }

    public void rechercher(String colonne, String st) {
        /*
                methode db.rechercher a réécrire afin d'y inclure les jointure avec compositeur nom
                pour pouvoir faire une recherche sur le compositeur
         */
        ObservableList<Morceau> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM morceau WHERE titre LIKE \"%" + st + "%\"";
        try {
            if (st != null) {
                Statement stat = connection().createStatement();
                stat.execute(sql);
                ResultSet rs = stat.getResultSet();
                while (rs.next()) {
                    String c_n = rs.getString("c_nom");
                    String c_p = rs.getString("c_prenom");
                    String t = rs.getString("titre");
                    list.add(new Morceau(t, c_n, c_p));
                }
                stat.getConnection().close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        notifyObserver(list);
    }


    /////////////  interface Observable  ///////

    @Override
    public void addObserver(Observer o) {
        listeObservers.add(o);
        getListeMorceau();
    }

    @Override
    public void delOberverList() {
        this.listeObservers = new ArrayList<>();
    }

    @Override
    public void notifyObserver(ObservableList<Morceau> list) {
        for (Observer listeObserver : listeObservers) {
            listeObserver.update(list);
        }
    }

}
