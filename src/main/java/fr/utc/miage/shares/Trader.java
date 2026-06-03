/*
 * Copyright 2025 David Navarre <David.Navarre@irit.fr>.
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

public class Trader {

    private String nom; 
    private String prenom;
    private String email;
    private final Portfolio portfolio;

    public Trader(String nom, String prenom, String email, Portfolio portfolio) {
        if (nom == null) throw new IllegalArgumentException("Nom cannot be null");
        if (prenom == null) throw new IllegalArgumentException("Prenom cannot be null");
        if (email == null) throw new IllegalArgumentException("Email cannot be null");
        if (portfolio == null) throw new IllegalArgumentException("Portfolio cannot be null");
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.portfolio = portfolio;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + nom.hashCode();
        result = prime * result + prenom.hashCode();
        result = prime * result + email.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trader other = (Trader) obj;
            return (nom.equals(other.nom)) && (prenom.equals(other.prenom)) && (email.equals(other.email));
    }

    @Override
    public String toString() {
        return "Trader [nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
    }



    public Portfolio getPortfolio() {
        return portfolio;
    }


}
