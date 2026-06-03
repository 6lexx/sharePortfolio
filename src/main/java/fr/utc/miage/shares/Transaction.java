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

/**
 * Représente une transaction d'achat ou de vente d'une action
 */
public class Transaction {
    private final Action action;
    private final Integer quantity;
    private final double price;
    private final double total;
    private final Jour jour;
    private final boolean isBuy;

    public Transaction(Action action, Integer quantity, double price, double total, Jour jour,
            boolean isBuy) {
        if (action == null) throw new IllegalArgumentException("Action cannot be null");
        if (quantity == null || quantity <= 0) throw new IllegalArgumentException("Quantity must be a positive integer");
        if (price <= 0) throw new IllegalArgumentException("Price must be a positive number");
        if (total <= 0) throw new IllegalArgumentException("Total must be a positive number");
        if (jour == null) throw new IllegalArgumentException("Jour cannot be null");

        this.action = action;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.jour = jour;
        this.isBuy = isBuy;
    }
    public Action getAction() {
        return action;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public double getPrice() {
        return price;
    }
    public double getTotal() {
        return total;
    }
    public Jour getJour() {
        return jour;
    }
    public boolean isBuy() {
        return isBuy;
    }
    
}
