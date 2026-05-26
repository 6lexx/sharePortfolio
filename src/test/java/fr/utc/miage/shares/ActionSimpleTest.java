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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ActionSimpleTest {
   private static final String VALID_LABEL = "Apple";
   private static final String INVALID_LABEL = null;
   private static final Jour VALID_DAY = new Jour(2026, 20);
   private static final Jour INVALID_DAY = null;
   private static final Company COMPANY = new Company(VALID_LABEL);

   @Test
    void testConstructorWithParameters() {
          assertDoesNotThrow(() -> new ActionSimple(VALID_LABEL, COMPANY));
    }

    @Test
    void testGetCompany() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        var company = action.getCompany();
        assertDoesNotThrow(() -> company.equals(COMPANY));
    }

    @Test
    void testGetCompanyInvalid() {
        var action = new ActionSimple(VALID_LABEL, null);
        var company = action.getCompany();
        assertDoesNotThrow(() -> company == null);
    }

    @Test
    void testValeur() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        var actual = action.valeur(VALID_DAY);
        assertEquals(0f, actual, 0.01f);
    }

    @Test
    void testValeurInvalidDay() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        assertThrows(IllegalArgumentException.class, () -> action.valeur(INVALID_DAY));
    }

    @Test
    void testSaveDailyPrice(){
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        assertDoesNotThrow(() -> action.saveDailyPrice(VALID_DAY, 150.0f));
    }

    @Test
    void testSaveDailyPriceDuplicate(){
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        action.saveDailyPrice(VALID_DAY, 150.0f);
        assertThrows(IllegalArgumentException.class, () -> action.saveDailyPrice(VALID_DAY, 200.0f));
    }

    @Test
    void testSaveDailyPriceInvalidDay() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        assertThrows(IllegalArgumentException.class, () -> action.saveDailyPrice(INVALID_DAY, 150.0f));
    }
}
