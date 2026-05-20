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
     * Permet d'acheter une/plusieurs action(s) simple(s) en l'ajoutant à son portefeuille
     */
    public void buyActionSimple(ActionSimple action, int quantity){
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
     * Permet de vendre une/plusieurs action(s) simple(s) et de les retirer de son portefeuille
     */
    public void sellActionSimple(ActionSimple action, int quantity){
        if(quantity <= 0) {
            throw new IllegalArgumentException("Erreur - Il est impossible de vendre 0 actions");
        }
        if(this.lignes.containsKey(action)){
            if (quantity > this.lignes.get(action)) {
                throw new IllegalArgumentException("Erreur - Il est impossible de vendre plus actions que vous n'en possèdez");
            }
            this.lignes.put(action, this.lignes.get(action) - quantity);
        } else {
            throw new IllegalArgumentException("Erreur - Il n'existe pas d'action à ce nom");
        }
    }

    /**
     * Permet d'acheter un/plusieurs ETF en l'ajoutant à son portefeuille
     */
    public void buyETF(ExchangeTradedFund etf, int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("Erreur - Il est impossible d'acheter 0 actions");
        }
        if(this.lignes.containsKey(etf)){
            this.lignes.put(etf, this.lignes.get(etf) + quantity);
        } else {
            this.lignes.put(etf, quantity);
        }
    }

    /**
     * Permet de vendre un/plusieurs ETF et de les retirer de son portefeuille
     */
    public void sellETF(ExchangeTradedFund etf, int quantity){
        if(quantity <= 0) {
            throw new IllegalArgumentException("Erreur - Il est impossible de vendre 0 actions");
        }
        if(this.lignes.containsKey(etf)){
            if(quantity > this.lignes.get(etf)){
                throw new IllegalArgumentException("Erreur - Il est impossible de vendre plus actions que vous n'en possèdez");
            }
            this.lignes.put(etf, this.lignes.get(etf) - quantity);
        } else {
            throw new IllegalArgumentException("Erreur - Il n'existe pas d'action à ce nom");
        }
    }

}
