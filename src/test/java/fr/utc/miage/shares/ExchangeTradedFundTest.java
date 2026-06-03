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

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


class ExchangeTradedFundTest {

    private static final String VALID_LABEL = "Apple";
    private static final String OTHER_LABEL = "Microsoft";
    private static final Company COMPANY = new Company("Company");
    private static final ExchangeTradedFund CORRECT_ETF = new ExchangeTradedFund("ETF");

    private static final Float VALEUR_REPARTITION = 0.5f;
    private static final Float VALEUR_REPARTITION_FALSE = -0.5f;
    private static final Float VALEUR_REPARTITION_SUPERIOR = 1.5f;
    private static final Map<Company, Float> REPARTITION = Map.of(COMPANY, VALEUR_REPARTITION);

    private static final Jour JOUR = new Jour(2012,1);
    private static final Jour AUTRE_JOUR = new Jour(2000,1);
    private static final Float VALEUR_COURS = 12.45f;
    private static final Map<Jour,Float> MAP_COURS = Map.of(JOUR,VALEUR_COURS);
    private static final Float DEFAULT_ACTION_VALUE = 0f ;

    @Test
    void TestgetRepartitionsWithValideParam(){
        assertDoesNotThrow(CORRECT_ETF::getRepartitions);
    }


    @Test
    void TestConstructorWithValideParam(){
        ExchangeTradedFund etf = new ExchangeTradedFund("ETF");
        assertEquals("ETF", etf.getLibelle());
    }

    @Test
    void TestsetRepartitionsForCompanyWithValideParam(){
        CORRECT_ETF.setRepartitionsForCompany(COMPANY, VALEUR_REPARTITION);
        assertEquals(VALEUR_REPARTITION, CORRECT_ETF.getRepartitionForCompany(COMPANY));
    }

    @Test
    void TestsetRepartitionsForCompanyWithInvalideParamInferior(){
        assertThrows(IllegalArgumentException.class,()->CORRECT_ETF.setRepartitionsForCompany(COMPANY, VALEUR_REPARTITION_FALSE));
    }

    @Test
    void TestsetRepartitionsForCompanyWithInvalideParamSuperior(){
        assertThrows(IllegalArgumentException.class,()->CORRECT_ETF.setRepartitionsForCompany(COMPANY, VALEUR_REPARTITION_SUPERIOR));
    }

    @Test
    void TestsetRepartitionsWithValideParam(){
        CORRECT_ETF.setRepartitions(REPARTITION);
        assertEquals(REPARTITION, CORRECT_ETF.getRepartitions());
    }

    @Test
    void TestgetRepartitionForCompanyWithValideParam(){
        assertDoesNotThrow(() ->  CORRECT_ETF.getRepartitionForCompany(COMPANY));
    }

    @Test
    void TestgetRepartitionForCompanyNULL(){
        assertThrows(IllegalArgumentException.class,() ->  CORRECT_ETF.getRepartitionForCompany(null));
    }

    @Test
    void testGetMapCours() {
        assertDoesNotThrow(CORRECT_ETF::getMapCours);
    }

    @Test
    void testSetMapCours() {
        CORRECT_ETF.setMapCours(JOUR, VALEUR_COURS);
        assertEquals(MAP_COURS, CORRECT_ETF.getMapCours());
    }

    @Test
    void testValeurWithContainsKey() {
        CORRECT_ETF.setMapCours(JOUR, VALEUR_COURS);
        assertEquals(VALEUR_COURS,CORRECT_ETF.valeur(JOUR));
    }

    @Test
    void testValeurWithoutKey() {
        assertEquals(DEFAULT_ACTION_VALUE, CORRECT_ETF.valeur(AUTRE_JOUR));
    }

    @Test
    void testgetHistoryETFValeurValideParam() {
        CORRECT_ETF.setMapCours(JOUR, VALEUR_COURS);
        assertEquals(MAP_COURS, CORRECT_ETF.getHistoryETFValeur());
    }

     @Test
     void testGetHistoryETFValeurUnmodifiable() {
        CORRECT_ETF.setMapCours(JOUR, VALEUR_COURS);
        Map<Jour, Float> history = CORRECT_ETF.getHistoryETFValeur();
        assertThrows(UnsupportedOperationException.class, () -> history.put(AUTRE_JOUR, 10.0f));
    }

    @Test
    void testEqualsWithSameLabel() {
        var action1 = new ExchangeTradedFund(VALID_LABEL);
        var action2 = new ExchangeTradedFund(VALID_LABEL);

        assertEquals(action1, action2);
    }

    @Test
    void testEqualsWithSameInstance() {
        var action = new ExchangeTradedFund(VALID_LABEL);

        assertEquals(action, action);
    }

    @Test
    void testEqualsWithDifferentLabel() {
        var action1 = new ExchangeTradedFund(VALID_LABEL);
        var action2 = new ExchangeTradedFund(OTHER_LABEL);

        assertNotEquals(action1, action2);
    }

    @Test
    void testEqualsWithNull() {
        var action = new ExchangeTradedFund(VALID_LABEL);

        assertNotEquals(action, null);
    }

    @Test
    void testEqualsWithDifferentClass() {
        var action = new ExchangeTradedFund(VALID_LABEL);
        var company = new Company(VALID_LABEL);

        assertNotEquals(action, company);
    }

    @Test
    void testHashCodeWithDifferentState() {
        var action1 = new ExchangeTradedFund(VALID_LABEL);
        var action2 = new ExchangeTradedFund(OTHER_LABEL);

        assertNotEquals(action1.hashCode(), action2.hashCode());
    }

}
