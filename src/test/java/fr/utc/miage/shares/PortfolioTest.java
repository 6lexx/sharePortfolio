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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PortfolioTest {
    private static final int ACTUAL_QUANTITY_NULL = 0;
    private static final int ACTUAL_QUANTITY = 1;
    private static final int ACTUAL_QUANTITY_10 = 10;
    private static final float VALID_DAILY_PRICE = 12.3f;
    private static final int ACTUAL_QUANTITY_EXCEEDING = 2;
    private static final String ACTUAL_ACTION_LIBELLE = "ACTION LIBELLE TEST";
    private static final String ACTUAL_COMPANY_NAME = "Company Test";



    private Portfolio p;
    private ActionSimple a;

    private Jour j;

    @BeforeEach
    void setUp() {
        p = new Portfolio();
        Company c = new Company(ACTUAL_COMPANY_NAME);
        a = new ActionSimple(ACTUAL_ACTION_LIBELLE, c);
        LocalDateTime local = LocalDateTime.parse("2018-12-03T12:39:10");
        int dayOfYear = local.getDayOfYear();
        int year = local.getYear();
        j = new Jour(year, dayOfYear);
        a.saveDailyPrice(j, VALID_DAILY_PRICE);
    }

    /**
     * Vérifie qu'un achat d'une action depuis un portefeuille vide fonctionne correctement.
     */
    @Test
    void TestBuyActionFromEmptyPortfolio(){
        Assertions.assertDoesNotThrow(() -> p.buyAction(a, ACTUAL_QUANTITY));
        Assertions.assertEquals(ACTUAL_QUANTITY, p.getLignes().get(a));
    }

    /**
     * Vérifie qu'un achat d'une action depuis un portefeuille contenant des actions fonctionne correctement.
     */
    @Test
    void TestBuySimpleActionWithActionsAlreadyInPortfolio(){
        p.buyAction(a, ACTUAL_QUANTITY);
        Assertions.assertDoesNotThrow(() -> p.buyAction(a, ACTUAL_QUANTITY));
        Assertions.assertEquals(ACTUAL_QUANTITY * 2, p.getLignes().get(a));
    }

    /**
     * Vérifie qu'une exception est levée lors de l'achat d'une quantité
     * invalide (zéro ou négative).
     */
    @Test
    void TestBuyActionThrowExceptionIfQuantityNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                p.buyAction(a, ACTUAL_QUANTITY_NULL)
        );
    }

    /**
     * Vérifie que la vente d'une action depuis un portefeuille fonctionne correctement.
     */
    @Test
    void TestSellActionThrowExceptionIfQuantityNull(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                p.sellAction(a, ACTUAL_QUANTITY_NULL)
        );
    }

    /**
     * Vérifie qu'une exception est levée lors de la vente d'une action absente du portefeuille.
     */
    @Test
    void TestSellActionThrowExceptionIfActionDoesNotExist(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                p.sellAction(a, ACTUAL_QUANTITY)
        );
    }

    /**
     * Vérifie qu'une exception est levée lors de la vente d'une quantité supérieure aux actions possédées.
     */
    @Test
    void TestSellActionThrowExceptionIfQuantityExceedsOwnedActions(){
        p.buyAction(a, ACTUAL_QUANTITY);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                p.sellAction(a, ACTUAL_QUANTITY_EXCEEDING)
        );
    }

    /**
     * Vérifie que la quantité est correctement réduite lors d'une vente partielle.
     */
    @Test
    void testSellActionReducesQuantityWhenPartialSell() {
        p.buyAction(a, ACTUAL_QUANTITY_EXCEEDING);
        p.sellAction(a, ACTUAL_QUANTITY);
        Assertions.assertEquals(ACTUAL_QUANTITY, p.getLignes().get(a));
    }

    /**
     * Vérifie que l'action est correctement supprimée du Portfolio lorsqu'une
     * vente d'actions réduit la quantité d'actions à 0.
     */
    @Test
    void TestSellActionDeleteActionWhenQuantityEqualZero() {
        p.buyAction(a, ACTUAL_QUANTITY);
        Assertions.assertDoesNotThrow(() -> p.sellAction(a, ACTUAL_QUANTITY));
        Assertions.assertNull(p.getLignes().get(a));
    }
    /**
     * Vérifie que l'action est correctement supprimée du Portfolio lorsqu'une
     * vente d'actions réduit la quantité d'actions à 0.
     */
    @Test
    void TestSellActionNoDeleteActionWhenQuantityMoreThanZero() {
        p.buyAction(a, ACTUAL_QUANTITY_10);
        Assertions.assertDoesNotThrow(() -> p.sellAction(a, ACTUAL_QUANTITY_10 - 1));
        Assertions.assertEquals(1, p.getLignes().get(a).intValue());
    }

    @Test
    void testGetTransactions() {
        Assertions.assertDoesNotThrow(p::getTransactions);
    }

    @Test
    void testBuyActionAddsTransaction() {
        p.buyAction(a, ACTUAL_QUANTITY);
        Assertions.assertEquals(1, p.getTransactions().size());
        Transaction t = p.getTransactions().get(0);
        Assertions.assertEquals(a, t.getAction());
        Assertions.assertEquals(ACTUAL_QUANTITY, t.getQuantity());
        Assertions.assertEquals(VALID_DAILY_PRICE, t.getPrice());
        Assertions.assertEquals(VALID_DAILY_PRICE * ACTUAL_QUANTITY, t.getTotal());
        Assertions.assertTrue(t.isBuy());
    }

    @Test 
    void testSellActionAddsTransaction() {
        p.buyAction(a, ACTUAL_QUANTITY);
        p.sellAction(a, ACTUAL_QUANTITY);
        Assertions.assertEquals(2, p.getTransactions().size());
        Transaction t = p.getTransactions().get(1);
        Assertions.assertEquals(a, t.getAction());
        Assertions.assertEquals(ACTUAL_QUANTITY, t.getQuantity());
        Assertions.assertEquals(VALID_DAILY_PRICE, t.getPrice());
        Assertions.assertEquals(VALID_DAILY_PRICE * ACTUAL_QUANTITY, t.getTotal());
        Assertions.assertFalse(t.isBuy());
    }

    /**
     * Vérifie que la valeur du portefeuille est bien égal à zéro si aucune action n'est achetée
     */
    @Test
    void TestPortfolioEqualZero() {
        Assertions.assertEquals(0f, p.seeValue(j));
    }
}
