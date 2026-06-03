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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class TraderTest {
    private static final String NOM = "Doe";
    private static final String PRENOM = "John";
    private static final String EMAIL = "john.doe@mail.com";
    private static final String NOM_POUR_EQUALS = "Dupont";
    private static final String PRENOM_POUR_EQUALS  = "Jean";
    private static final String EMAIL_POUR_EQUALS  = "jean.dupont@mail.com";
    private static final Portfolio VALID_PORTFOLIO = new Portfolio();
    private static final Portfolio INVALID_PORTFOLIO = null;

    private final Trader trader =  new Trader(NOM,PRENOM,EMAIL, VALID_PORTFOLIO);

    @Test
    void getNom() {
        assertEquals(NOM,trader.getNom());
    }

    @Test
    void setNom() {
        trader.setNom(NOM);
        assertEquals(NOM,trader.getNom());
    }

    @Test
    void getPrenom() {
        assertEquals(PRENOM,trader.getPrenom());
    }

    @Test
    void setPrenom() {
        trader.setPrenom(PRENOM);
        assertEquals(PRENOM,trader.getPrenom());
    }

    @Test
    void getEmail() {
        assertEquals(EMAIL,trader.getEmail());
    }

    @Test
    void setEmail() {
        trader.setEmail(EMAIL);
        assertEquals(EMAIL,trader.getEmail());
    }

    @Test
    void createTraderWithParameterSuccessful() {
        assertDoesNotThrow(()-> new Trader(NOM,PRENOM,EMAIL, VALID_PORTFOLIO));
    }

    @Test
    void createTraderWithNoNameThrowsException() {
        assertThrows(IllegalArgumentException.class, ()-> new Trader(null,PRENOM,EMAIL, VALID_PORTFOLIO));
    }

    @Test
    void createTraderWithNoPortfolioThrowsException() {
        assertThrows(IllegalArgumentException.class, ()-> new Trader(NOM,PRENOM,EMAIL, null));
    }

    @Test
    void createTraderWithNoEmailThrowsException() {
        assertThrows(IllegalArgumentException.class, ()->new Trader(NOM,null,EMAIL, VALID_PORTFOLIO));
    }

    @Test
    void createTraderWithNoPrenomThrowsException() {
        assertThrows(IllegalArgumentException.class, ()->new Trader(NOM,PRENOM,null, INVALID_PORTFOLIO));
    }

    @Test
    void testHashCode() {
        assertDoesNotThrow(trader::hashCode);
    }

    @Test
    void testEqualsWithSameObjectTrue() {
        assertEquals(trader, trader);
    }

    @Test
    void testEqualsWithNullFalse() {
        assertNotEquals(null, trader);
    }

    @Test
    void testEqualsWithDifferentObjectAndDifferentClassFalse() {
        assertNotEquals(NOM, trader);
    }

    @Test
    void testEqualsWithDifferentNameFalse() {
        final Trader other = new Trader(NOM_POUR_EQUALS ,PRENOM,EMAIL, VALID_PORTFOLIO);
        assertNotEquals(trader, other);
    }

    @Test
    void testEqualsWithDifferentFirstnameFalse() {
        final Trader other = new Trader(NOM,PRENOM_POUR_EQUALS ,EMAIL, VALID_PORTFOLIO);
        assertNotEquals(trader, other);
    }

    @Test
    void testEqualsWithDifferentEmailFalse() {
        final Trader other = new Trader(NOM,PRENOM,EMAIL_POUR_EQUALS, VALID_PORTFOLIO);
        assertNotEquals(trader, other);
    }

    @Test 
    void testEqualsWithNullPortfolioFalse() {
        assertNotEquals(null, trader);
    }
    @Test
    void testEqualsWithDifferentClassesFalse() {
        final String other = "N'importe quelle chaîne de caractères";
        assertNotEquals(trader, other);
    }

    @Test
    void testEqualsWithSameValuesTrue() {
        final Trader other = new Trader(NOM,PRENOM,EMAIL, VALID_PORTFOLIO);
        assertEquals(trader, other);
    }

    @Test
    void testToString() {
        assertDoesNotThrow(trader::toString);
    }

    @Test
    void testGetPortfolio() {
        assertDoesNotThrow(trader::getPortfolio);
    }

    @Test
    void testGetPortfolioReturnsCorrectValue() {
        assertEquals(VALID_PORTFOLIO, trader.getPortfolio());
    }
}