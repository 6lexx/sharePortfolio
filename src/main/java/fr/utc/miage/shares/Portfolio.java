/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.utc.miage.shares;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Représente un portefeuille contenant des actions
 */
public class Portfolio {
    private final Map<Action, Integer> lignes;
    private final List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

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
        LocalDateTime local = LocalDateTime.parse("2018-12-03T12:39:10");
        int dayOfYear = local.getDayOfYear();
        int year = local.getYear();

        Jour today = new Jour(year, dayOfYear);

        transactions.add(new Transaction(action, quantity, action.valeur(today), action.valeur(today) * quantity, today, true));
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
            if(this.lignes.get(action) - quantity <= 0) {
                this.lignes.remove(action);
            } else {
                this.lignes.put(action, this.lignes.get(action) - quantity);
            }
            LocalDateTime local = LocalDateTime.parse("2018-12-03T12:39:10");
            int dayOfYear = local.getDayOfYear();
            int year = local.getYear();

            Jour today = new Jour(year, dayOfYear);
            transactions.add(new Transaction(action, quantity, action.valeur(today), action.valeur(today) * quantity, today, false));
        } else {
            throw new IllegalArgumentException("Erreur - Il n'existe pas d'action à ce nom");
        }
    }

    /**
     * Calcule la valeur totale du portefeuille pour un jour donné.
     *
     * @param j le jour pour lequel calculer la valeur
     * @return la valeur totale du portefeuille
     */
    public float seeValue(Jour j){
        float total = 0;
        for (Map.Entry<Action, Integer> ligne : this.lignes.entrySet()) {
            total += ligne.getKey().valeur(j) * ligne.getValue();
        }
        return total;
    }
}
