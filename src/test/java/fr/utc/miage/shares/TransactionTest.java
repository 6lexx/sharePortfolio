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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TransactionTest {
    public static final Action VALID_ACTION = new ActionSimple("Test Action", new Company("TestComp"));
    public static final Action INVALID_ACTION = null;
    public static final Jour VALID_JOUR = new Jour(2025,32);
    public static final Jour INVALID_JOUR = null;
    public static final Integer VALID_QUANTITY = 10;
    public static final Integer INVALID_QUANTITY = null;
    public static final Integer INVALID_QUANTITY_LE_0 = -10;
    public static final double VALID_PRICE = 100.0;
    public static final double INVALID_PRICE = -100.0;
    public static final double VALID_TOTAL = 1000.0;
    public static final double INVALID_TOTAL = -1000.0;

    @Test
    void testConstructorWithValidParameters() {
        assertDoesNotThrow(() -> new Transaction(VALID_ACTION, VALID_QUANTITY, VALID_PRICE, VALID_TOTAL, VALID_JOUR, true));
    }
    @Test
    void testConstructorWithInvalidParametersThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(INVALID_ACTION, VALID_QUANTITY, VALID_PRICE, VALID_TOTAL, VALID_JOUR, true));
        assertThrows(IllegalArgumentException.class, () -> new Transaction(VALID_ACTION, VALID_QUANTITY, INVALID_PRICE, VALID_TOTAL, VALID_JOUR, true));
        assertThrows(IllegalArgumentException.class, () -> new Transaction(VALID_ACTION, INVALID_QUANTITY, VALID_PRICE, VALID_TOTAL, VALID_JOUR, true));
        assertThrows(IllegalArgumentException.class, () -> new Transaction(VALID_ACTION, INVALID_QUANTITY_LE_0, VALID_PRICE, VALID_TOTAL, VALID_JOUR, true));
        assertThrows(IllegalArgumentException.class, () -> new Transaction(VALID_ACTION, VALID_QUANTITY, VALID_PRICE, INVALID_TOTAL, VALID_JOUR, true));
        assertThrows(IllegalArgumentException.class, () -> new Transaction(VALID_ACTION, VALID_QUANTITY, VALID_PRICE, VALID_TOTAL, INVALID_JOUR, true));    
    }
    @Test
    void testGettersWithValidTransactionDoesNotThrow() {
        Transaction transaction = new Transaction(VALID_ACTION, VALID_QUANTITY, VALID_PRICE, VALID_TOTAL, VALID_JOUR, true);
        assertDoesNotThrow(transaction::getAction);
        assertDoesNotThrow(transaction::getQuantity);
        assertDoesNotThrow(transaction::getPrice);
        assertDoesNotThrow(transaction::getTotal);
        assertDoesNotThrow(transaction::getJour);
        assertDoesNotThrow(transaction::isBuy); 
    }
    @Test
    void testGettersWithValidTransactionReturnsCorrectValues() {
        Transaction transaction = new Transaction(VALID_ACTION, VALID_QUANTITY, VALID_PRICE, VALID_TOTAL, VALID_JOUR, true);
        assertEquals(VALID_ACTION, transaction.getAction());
        assertEquals(VALID_QUANTITY, transaction.getQuantity());
        assertEquals(VALID_PRICE, transaction.getPrice());
        assertEquals(VALID_TOTAL, transaction.getTotal());
        assertEquals(VALID_JOUR, transaction.getJour());
        assertTrue(transaction.isBuy());
    }
}