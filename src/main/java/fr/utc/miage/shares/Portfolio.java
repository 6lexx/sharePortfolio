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
}
