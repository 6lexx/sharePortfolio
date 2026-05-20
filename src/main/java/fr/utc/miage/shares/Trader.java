package fr.utc.miage.shares;

public class Trader {

    private String nom; 
    private String prenom;
    private String email;


    public Trader(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Trader createTrader(String nom, String prenom, String email) {
        if (nom == null || prenom == null || email == null) {
            throw new IllegalArgumentException("All parameters must be non-null");
        }
        return new Trader(nom, prenom, email);
    }


}
