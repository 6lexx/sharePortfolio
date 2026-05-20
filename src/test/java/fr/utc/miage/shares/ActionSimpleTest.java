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
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ActionSimpleTest {
   private static final Company COMPANY = new Company("Apple");

   @Test
   void testConstructorEmpty(){
        assertDoesNotThrow(() -> new ActionSimple(null, null));
   }

   @Test
    void testConstructorWithParameters() {
          assertDoesNotThrow(() -> new ActionSimple("Apple", COMPANY));
    }

    @Test
    void testGetCompany() {
        var action = new ActionSimple("Apple", COMPANY);
        var company = action.getCompany();
        assertDoesNotThrow(() -> company.equals(COMPANY));
    }

    @Test
    void testGetCompanyInvalid() {
        var action = new ActionSimple("Apple", null);
        var company = action.getCompany();
        assertDoesNotThrow(() -> company == null);
    }

    @Test
    void testValeur() {
        var action = new ActionSimple("Apple", COMPANY);
        assertDoesNotThrow(() -> action.valeur(new Jour(2026, 20)));
    }

    @Test
    void testValeurInvalidDay() {
        var action = new ActionSimple("Apple", COMPANY);
        assertThrows(IllegalArgumentException.class, () -> action.valeur(null));
    }

    @Test
    void testSaveDailyPrice(){
        var action = new ActionSimple("Apple", COMPANY);
        assertDoesNotThrow(() -> action.saveDailyPrice(new Jour(2026, 20), 150.0f));
    }

    @Test
    void testSaveDailyPriceDuplicate(){
        var action = new ActionSimple("Apple", COMPANY);
        action.saveDailyPrice(new Jour(2026, 20), 150.0f);
        assertThrows(IllegalArgumentException.class, () -> action.saveDailyPrice(new Jour(2026, 20), 200.0f));
    }

    @Test
    void testSaveDailyPriceInvalid() {
        var action = new ActionSimple("Apple", COMPANY);
        assertThrows(IllegalArgumentException.class, () -> action.saveDailyPrice(null, 150.0f));
    }
}
