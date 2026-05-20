package fr.utc.miage.shares;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static fr.utc.miage.shares.Trader.createTrader;
import static org.junit.jupiter.api.Assertions.*;

class TraderTest {
    private final String NOM = "Doe";
    private final String PRENOM = "John";
    private final String EMAIL = "john.doe@mail.com";
    private final String NOM_POUR_EQUALS = "Dupont";
    private final String PRENOM_POUR_EQUALS  = "Jean";
    private final String EMAIL_POUR_EQUALS  = "jean.dupont@mail.com";

    private final Trader trader =  new Trader(NOM,PRENOM,EMAIL);

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
        assertDoesNotThrow(()->createTrader(NOM,PRENOM,EMAIL));
    }

    @Test
    void createTraderWithNoNameThrowsException() {
        assertThrows(IllegalArgumentException.class, ()->createTrader(null,PRENOM,EMAIL));
    }

    @Test
    void createTraderWithNoEmailThrowsException() {
        assertThrows(IllegalArgumentException.class, ()->createTrader(NOM,null,EMAIL));
    }

    @Test
    void createTraderWithNoPrenomThrowsException() {
        assertThrows(IllegalArgumentException.class, ()->createTrader(NOM,PRENOM,null));
    }

    @Test
    void testHashCode() {
        assertDoesNotThrow(trader::hashCode);
    }

    @Test
    void testHashCodeWithNull(){
        final Trader trader1 = new Trader(null,null,null);
        assertDoesNotThrow(trader1::hashCode);
    }

    @Test
    void testEqualsWithSameObjectTrue() {
        assertTrue(trader.equals(trader));
    }

    @Test
    void testEqualsWithNullFalse() {
        assertFalse(trader.equals(null));
    }

    @Test
    void testEqualsWithDifferentObjectAndDifferentClassFalse() {
        assertFalse(trader.equals(NOM));
    }

    @Test
    void testEqualsWithDifferentNameFalse() {
        final Trader other = new Trader(NOM_POUR_EQUALS ,PRENOM,EMAIL);
        assertFalse(trader.equals(other));
    }

    @Test
    void testEqualsWithDifferentFirstnameFalse() {
        final Trader other = new Trader(NOM,PRENOM_POUR_EQUALS ,EMAIL);
        assertFalse(trader.equals(other));
    }

    @Test
    void testEqualsWithDifferentEmailFalse() {
        final Trader other = new Trader(NOM,PRENOM,EMAIL_POUR_EQUALS );
        assertFalse(trader.equals(other));
    }

    @Test
    void testToString() {
        assertDoesNotThrow(trader::toString);
    }
}