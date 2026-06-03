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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ExchangeTradedFund extends Action {
    private static final float DEFAULT_ACTION_VALUE = 0;
    Map<Company, Float> repartitions;

    private final Map<Jour, Float> mapCours;

    public ExchangeTradedFund(String libelle) {
        super(libelle);
        this.repartitions = new HashMap<>();
        this.mapCours = new HashMap<>();
    }

    public Map<Jour, Float> getMapCours() {
        return mapCours;
    }

    public void setMapCours(Jour jour, Float f) {
        mapCours.put(jour, f);
    }

    public Map<Company, Float> getRepartitions() {
        return repartitions;
    }
    

    public void setRepartitions(Map<Company, Float> repartitions) {
        this.repartitions = repartitions;
    }

    public void setRepartitionsForCompany(Company company, Float repartition) {
        if (repartition < 0 || repartition > 1) {
            throw new IllegalArgumentException("Repartition must be between 0 and 1");
        }
        this.repartitions.put(company, repartition);
    }
    public float getRepartitionForCompany(Company company) {
        if(company == null){
            throw new IllegalArgumentException("Company cannot be null");
        }
        return repartitions.getOrDefault(company, 0.0f);
    }

    @Override
    public float valeur(final Jour j) {
        if (this.mapCours.containsKey(j)) {
            return this.mapCours.get(j);
        } else {
            return DEFAULT_ACTION_VALUE;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExchangeTradedFund other)) {
            return false;
        }
        return super.equals(obj) && Objects.equals(this.repartitions, other.repartitions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), repartitions);
    }

    
    public Map<Jour, Float> getHistoryETFValeur() {
        return Collections.unmodifiableMap(mapCours);
    }

    
}
