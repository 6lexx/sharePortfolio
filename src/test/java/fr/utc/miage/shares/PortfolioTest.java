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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PortfolioTest {
    private static final int ACTUAL_QUANTITY_NULL = 0;
    private static final int ACTUAL_QUANTITY = 1;
    private static final int ACTUAL_QUANTITY_EXCEEDING = 2;
    private static final String ACTUAL_ACTION_LIBELLE = "ACTION LIBELLE TEST";
    private static final String ACTUAL_COMPAGNY_NAME = "Company Test";



    private Portfolio p;
    private Action a;
    private Jour j;

    @BeforeEach
    void setUp() {
        p = new Portfolio();
        Company c = new Company(ACTUAL_COMPAGNY_NAME);
        a = new ActionSimple(ACTUAL_ACTION_LIBELLE, c);
        j = new Jour(2025, 1);
    }

    /**
     * Vérifie qu'un achat d'une action depuis un portefeuille vide fonctionne correctement.
     */
    @Test
    void TestBuyActionFromEmptyPortfolio(){
        Assertions.assertDoesNotThrow(() -> p.buyAction(a, ACTUAL_QUANTITY));
        Assertions.assertEquals(p.getLignes().get(a), ACTUAL_QUANTITY);
    }

    /**
     * Vérifie qu'un achat d'une action depuis un portefeuille contenant des actions fonctionne correctement.
     */
    @Test
    void TestBuySimpleActionWithActionsAlreadyInPortfolio(){
        p.buyAction(a, ACTUAL_QUANTITY);
        Assertions.assertDoesNotThrow(() -> p.buyAction(a, ACTUAL_QUANTITY));
        Assertions.assertEquals(p.getLignes().get(a), ACTUAL_QUANTITY * 2);
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
     * Vérifie que le portefeuille est bien égal à zéro si aucune action n'est achetée
     */
    @Test
    void TestPortfolioEqualZero() {
        Assertions.assertEquals(0f, p.seeValue(j));
    }
}
