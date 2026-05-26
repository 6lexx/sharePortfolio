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
