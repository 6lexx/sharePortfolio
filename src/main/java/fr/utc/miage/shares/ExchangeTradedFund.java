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
import java.util.Objects;

public class ExchangeTradedFund extends Action {
    Map<Company, Float> repartitions;

    public ExchangeTradedFund(String libelle) {
        this(libelle, new HashMap<>());
    }

    public ExchangeTradedFund(String libelle, Map<Company, Float> repartitions) {
        super(libelle);
        if(libelle == null || repartitions == null){
            throw new IllegalArgumentException("Libelle and repartitions cannot be null");
        }
        this.repartitions = repartitions;
    }

    public Map<Company, Float> getRepartitions() {
        return repartitions;
    }

    public float getRepartitionForCompany(Company company) {
        if(company == null){
            throw new IllegalArgumentException("Company cannot be null");
        }
        return repartitions.getOrDefault(company, 0.0f);
    }

    @Override
    public float valeur(final Jour j) {
        throw new UnsupportedOperationException("ETF does not implement the valeur method");
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
}
