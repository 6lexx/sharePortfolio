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
    private ActionSimple a;

    @BeforeEach
    void setUp() {
        Portfolio p = new Portfolio();
        Company c = new Company(ACTUAL_COMPAGNY_NAME);
        Action a = new ActionSimple(ACTUAL_ACTION_LIBELLE, c);
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
}
