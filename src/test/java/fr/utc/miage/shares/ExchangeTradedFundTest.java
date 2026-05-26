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

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ExchangeTradedFundTest {

    private final Company COMPANY = new Company("Company");
    private final ExchangeTradedFund CORRECT_ETF = new ExchangeTradedFund("ETF");
    private final ExchangeTradedFund INCORRECT_ETF = new ExchangeTradedFund(null);
    public static final Map<Company, Float> REPARTITION = Map.of(new Company("Apple"), 0.5f, new Company("Microsoft"), 0.5f);

    @Test
    public void TestgetRepartitionsWithValideParam(){
       assertEquals(new HashMap<>(), CORRECT_ETF.getRepartitions());
    }

    @Test
    public void TestgetRepartitionsWithInvalideParam(){
        assertEquals(new HashMap<>(), INCORRECT_ETF.getRepartitions());
    }

    @Test
    void testConstructorEmpty() {
        assertAll(ExchangeTradedFundTest::new);
    }

    @Test
    void testConstructorWithParameters() {
        assertAll(() -> new ExchangeTradedFund("Vanguard", REPARTITION));
    }

    @Test
    void testValeur() {
        var etf = new ExchangeTradedFund("Vanguard", REPARTITION);
        assertEquals(0, etf.valeur(new Jour(2026, 20)));
    }

    @Test
    void testValeurInvalidDay(){
        var etf = new ExchangeTradedFund("Vanguard", REPARTITION);
        assertEquals(0, etf.valeur(null));
    }
}
