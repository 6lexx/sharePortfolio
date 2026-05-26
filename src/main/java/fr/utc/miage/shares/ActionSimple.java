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
 * Allows the creation of simple Action objects.
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class ActionSimple extends Action {

    private static final int DEFAULT_ACTION_VALUE = 0;

    // attribut lien
    private final Map<Jour, Float> mapCours;

    private Company company;

    // constructeur
    public ActionSimple(final String libelle, Company company) {
        // Action simple initialisée comme 1 action
        super(libelle);
        this.company = company;
        // init spécifique
        this.mapCours = new HashMap<>();
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // enrg possible si pas de cours pour ce jour
    public void saveDailyPrice(final Jour j, final float v) {
        if(j == null)
            throw new IllegalArgumentException("Jour cannot be null");
        if (!this.mapCours.containsKey(j)) {
            this.mapCours.put(j, v);
        }
        else 
            throw new IllegalArgumentException("A price for this day already exists");
    }

    @Override
    public float valeur(final Jour j) {
        if(j == null)
            throw new IllegalArgumentException("Jour cannot be null");
        if (this.mapCours.containsKey(j)) {
            return this.mapCours.get(j);
        } else {
            return DEFAULT_ACTION_VALUE;
        }
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
