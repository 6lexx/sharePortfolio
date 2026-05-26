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

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ExchangeTradedFundTest {

    private final Company COMPANY = new Company("Company");
    private final ExchangeTradedFund CORRECT_ETF = new ExchangeTradedFund("ETF");
    public static final Map<Company, Float> REPARTITION = Map.of(new Company("Apple"), 0.5f, new Company("Microsoft"), 0.5f);

    @Test
    public void TestgetRepartitionsWithValideParam(){
       assertEquals(new HashMap<>(), CORRECT_ETF.getRepartitions());
    }

    @Test
    public void TestgetRepartitionForCompanyWithValideParam(){
        assertEquals(0.0f, CORRECT_ETF.getRepartitionForCompany(COMPANY));
    }

    @Test
    public void TestGetRepartitionForCompanyWithInvalideParam(){
        var etf = new ExchangeTradedFund("ETF", REPARTITION);

        assertEquals(0.0f, etf.getRepartitionForCompany(COMPANY));
    }

    @Test
    public void TestGetRepartitionForCompanyWithNullParam(){
        assertThrows(IllegalArgumentException.class, () -> CORRECT_ETF.getRepartitionForCompany(null));
    }

    @Test
    public void TestEqualsWithValideParam(){
        var etf1 = new ExchangeTradedFund("etf1");
        var etf2 = new ExchangeTradedFund("etf1");
        assertEquals(etf1, etf2);
    }

    @Test
    public void TestEqualsWithSameInstance(){
        var etf = new ExchangeTradedFund("etf1");
        assertEquals(etf, etf);
    }

    @Test
    public void TestEqualsWithInvalideParam(){
        var etf1 = new ExchangeTradedFund("etf1");
        var etf2 = new ExchangeTradedFund("etf2");
        assertNotEquals(etf1, etf2);
    }

    @Test
    public void TestEqualsWithNullParam(){
        var etf1 = new ExchangeTradedFund("etf1");
        assertNotEquals(etf1, null);
    }

    @Test
    public void TestEqualsWithDifferentClass(){
        var etf1 = new ExchangeTradedFund("etf1");
        var company = new Company("company");
        assertNotEquals(etf1, company);
    }

    @Test
    public void TestEqualsWithDifferentRepartitions(){
        var sharedCompany = new Company("Apple");
        var repartitionA = Map.of(sharedCompany, 0.5f);
        var repartitionB = Map.of(sharedCompany, 1.0f);
        var etf1 = new ExchangeTradedFund("etf1", repartitionA);
        var etf2 = new ExchangeTradedFund("etf1", repartitionB);

        assertNotEquals(etf1, etf2);
    }

    @Test
    public void TestHashCodeWithValideParam(){
        var etf1 = new ExchangeTradedFund("etf1");
        var etf2 = new ExchangeTradedFund("etf1");
        assertEquals(etf1.hashCode(), etf2.hashCode());
    }

    @Test
    public void TestHashCodeWithInvalideParam(){
        var etf1 = new ExchangeTradedFund("etf1");
        var etf2 = new ExchangeTradedFund("etf2");
        assertNotEquals(etf1.hashCode(), etf2.hashCode());
    }

    @Test
    public void TestHashCodeWithDifferentRepartitions(){
        var sharedCompany = new Company("Apple");
        var repartitionA = Map.of(sharedCompany, 0.5f);
        var repartitionB = Map.of(sharedCompany, 1.0f);
        var etf1 = new ExchangeTradedFund("etf1", repartitionA);
        var etf2 = new ExchangeTradedFund("etf1", repartitionB);

        assertNotEquals(etf1.hashCode(), etf2.hashCode());
    }

    @Test
    public void TestHashCodeWithDifferentClass(){
        var etf1 = new ExchangeTradedFund("etf1");
        var company = new Company("company");
        assertNotEquals(etf1.hashCode(), company.hashCode());
    }

    @Test
    void testConstructorEmpty() {
        assertDoesNotThrow(() -> new ExchangeTradedFund("ETF"));
    }

    @Test
    void testConstructorWithParameters() {
        var etf = new ExchangeTradedFund("Vanguard", REPARTITION);

        assertAll(
                () -> assertEquals("Vanguard", etf.getLibelle()),
                () -> assertEquals(REPARTITION, etf.getRepartitions())
        );
    }

    @Test
    void testConstructorWithInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> new ExchangeTradedFund(null, REPARTITION));
        assertThrows(IllegalArgumentException.class, () -> new ExchangeTradedFund("Vanguard", null));
    }

    @Test
    void testValeur() {
        var etf = new ExchangeTradedFund("Vanguard", REPARTITION);
        assertThrows(UnsupportedOperationException.class, () -> etf.valeur(new Jour(2026, 20)));
    }

    @Test
    void testValeurInvalidDay(){
        var etf = new ExchangeTradedFund("Vanguard", REPARTITION);
        assertThrows(UnsupportedOperationException.class, () -> etf.valeur(null));
    }
}
