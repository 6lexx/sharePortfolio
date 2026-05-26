package fr.utc.miage.shares;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PortfolioTest {
    private static final int ACTUAL_QUANTITY_NULL = 0;
    private static final int ACTUAL_QUANTITY = 1;
    private static final int ACTUAL_MORE_QUANTITY = 2;
    private static final String ACTUAL_ACTION_LIBELLE = "ACTION LIBELLE TEST";
    private static final String ACTUAL_COMPAGNY_NAME = "Company Test";

    /**
     * Vérifie qu'un achat d'une action depuis un portefeuille vide fonctionne correctement.
     */
    @Test
    void TestBuyActionFromEmptyPortfolio(){
        Portfolio p = new Portfolio();
        Company c = new Company(ACTUAL_COMPAGNY_NAME);
        ActionSimple a = new ActionSimple(ACTUAL_ACTION_LIBELLE, c);

        Assertions.assertDoesNotThrow(() -> p.buyAction(a, ACTUAL_QUANTITY));
        Assertions.assertEquals(p.getLignes().get(a), ACTUAL_QUANTITY);
    }

    /**
     * Vérifie qu'un achat d'une action depuis un portefeuille contenant des actions fonctionne correctement.
     */
    @Test
    void TestBuySimpleActionWithActionsAlreadyInPortfolio(){
        Portfolio p = new Portfolio();
        Company c = new Company(ACTUAL_COMPAGNY_NAME);
        ActionSimple a = new ActionSimple(ACTUAL_ACTION_LIBELLE, c);

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
        Portfolio p = new Portfolio();
        Company c = new Company(ACTUAL_COMPAGNY_NAME);
        ActionSimple a = new ActionSimple(ACTUAL_ACTION_LIBELLE, c);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                p.buyAction(a, ACTUAL_QUANTITY_NULL)
        );
    }

    /**
     * Vérifie que la vente d'une action depuis un portefeuille fonctionne correctement.
     */
    @Test
    void TestSellActionThrowExceptionIfQuantityNull(){
        Portfolio p = new Portfolio();
        Company c = new Company(ACTUAL_COMPAGNY_NAME);
        ActionSimple a = new ActionSimple(ACTUAL_ACTION_LIBELLE, c);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                p.sellAction(a, ACTUAL_QUANTITY_NULL)
        );
    }
}
