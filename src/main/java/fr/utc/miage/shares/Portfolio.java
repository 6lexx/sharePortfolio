package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

/**
 * Représente un portefeuille contenant des actions
 */
public class Portfolio {
    private final Map<Action, Integer> lignes;

    /**
     * Construit un portefeuille vide
     */
    public Portfolio() {
        this.lignes = new HashMap<>();
    }

    /**
     * Récupère les enregistrements du portefeuille
     *
     * @return une vue non modifiable du portefeuille
     */
    public Map<Action, Integer> getLignes() {
        return lignes;
    }


    /**
     * Permet d'acheter une/plusieurs action(s) en l'ajoutant à son portefeuille
     */
    public void buyAction(Action action, int quantity){
        if(quantity <= 0) {
            throw new IllegalArgumentException("Erreur - Il est impossible d'acheter 0 actions");
        }
        if(this.lignes.containsKey(action)){
            this.lignes.put(action, quantity + this.lignes.get(action));
        } else {
            this.lignes.put(action, quantity);
        }
    }

    /**
     * Permet de vendre une/plusieurs action(s) et de les retirer de son portefeuille
     */
    public void sellAction(Action action, int quantity){
        if(quantity <= 0) {
            throw new IllegalArgumentException("Erreur - Il est impossible de vendre 0 actions");
        }
        if(this.lignes.containsKey(action)){
            if (quantity > this.lignes.get(action)) {
                throw new IllegalArgumentException("Erreur - Il est impossible de vendre plus actions que vous n'en possèdez");
            }
            if(this.lignes.get(action) - quantity == 0) {
                this.lignes.remove(action);
            } else {
                this.lignes.put(action, this.lignes.get(action) - quantity);
            }
        } else {
            throw new IllegalArgumentException("Erreur - Il n'existe pas d'action à ce nom");
        }
    }
}
