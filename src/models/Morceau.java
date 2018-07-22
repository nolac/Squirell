package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Morceau {
    int id;
    private StringProperty titre;
    private Compositeur compositeur;
/////     constructeurs   ////////
    public Morceau(){
    }
    public Morceau(String titre, Compositeur compositeur){
        this.titre=new SimpleStringProperty(titre);
        this.compositeur=compositeur;
    }
    public Morceau(String titre, String nom_compositeur,String prenom_compositeur){
        this(titre,new Compositeur(nom_compositeur,prenom_compositeur));
    }
/////     accesseurs       ///////
    public StringProperty titreProperty(){
        return this.titre;
    }
    public String getTitre(){
        return this.titre.get();
    }
    public Compositeur getCompositeur(){
        return this.compositeur;
    }
    public String toString(){
        return "["+ this.getTitre()+"\n"+
                this.compositeur.toString()+ "]";
    }

//////////////////////////////////
    public static class Compositeur{
        int id;
        private StringProperty nom,prenom;

        /*     constructeurs      */
        public Compositeur(){
            this.nom=new SimpleStringProperty();
            this.prenom=new SimpleStringProperty();
        }
        public Compositeur(String nom,String prenom){
            this.nom=new SimpleStringProperty(nom);
            this.prenom=new SimpleStringProperty(prenom);
        }

        /*      accesseurs        */
        public StringProperty nomProperty(){
            return this.nom;
        }
        public String getNom() {
            return nom.get();
        }
        public StringProperty prenomProperty(){
            return this.prenom;
        }
        public String getPrenom() {
            return prenom.get();
        }
        public StringProperty identité(){
            String id=this.nom.get() + " " + this.prenom.get();
            StringProperty identite = new SimpleStringProperty(id);
            return identite;
        }

        @Override
        public String toString() {
            return "Compositeur{" +
                    "nom=" + nom.get() +
                    ", prenom=" + prenom.get() +
                    '}';
        }
    }
}
