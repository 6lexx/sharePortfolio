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


    void buyActionSimple(ActionSimple action, int quantity){
        if(quantity <= 0) {
            throw new IllegalArgumentException("Erreur - Il est impossible d'acheter 0 actions");
        }
        if(this.lignes.containsKey(action)){
            this.lignes.put(action, quantity + this.lignes.get(action));
        } else {
            this.lignes.put(action, quantity);
        }
    }

}
