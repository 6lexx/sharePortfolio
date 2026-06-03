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

class ActionSimpleTest {
   private static final String VALID_LABEL = "Apple";
   private static final Jour VALID_DAY = new Jour(2026, 20);
   private static final Jour INVALID_DAY = null;
   private static final Company COMPANY = new Company(VALID_LABEL);
   private static final Jour AUTRE_JOUR = new Jour(2000,1);


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
    void testSetCompany(){
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        var newCompany = new Company("Microsoft");
        action.setCompany(newCompany);
        assertEquals(newCompany, action.getCompany());
    }

    @Test
    void testEqualsWithSameLabel() {
        var action1 = new ActionSimple(VALID_LABEL, COMPANY);
        var action2 = new ActionSimple(VALID_LABEL, COMPANY);

        assertEquals(action1, action2);
    }

    @Test
    void testEqualsWithSameInstance() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);

        assertEquals(action, action);
    }

    @Test
    void testEqualsWithDifferentLabel() {
        var action1 = new ActionSimple(VALID_LABEL, COMPANY);
        var action2 = new ActionSimple("Microsoft", COMPANY);

        assertNotEquals(action1, action2);
    }

    @Test
    void testEqualsWithNull() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);

        assertNotEquals(null,action);
    }

    @Test
    void testEqualsWithDifferentClass() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        var company = new Company(VALID_LABEL);

        assertNotEquals(action, company);
    }

    @Test
    void testEqualsWithDifferentCompany() {
        var action1 = new ActionSimple(VALID_LABEL, COMPANY);
        var action2 = new ActionSimple(VALID_LABEL, new Company(VALID_LABEL));

        assertNotEquals(action1, action2);
    }

    @Test
    void testEqualsWithDifferentDailyPrices() {
        var action1 = new ActionSimple(VALID_LABEL, COMPANY);
        var action2 = new ActionSimple(VALID_LABEL, COMPANY);
        var anotherDay = new Jour(2026, 21);

        action1.saveDailyPrice(VALID_DAY, 150.0f);
        action2.saveDailyPrice(anotherDay, 150.0f);

        assertNotEquals(action1, action2);
    }

    @Test
    void testHashCodeWithSameState() {
        var action1 = new ActionSimple(VALID_LABEL, COMPANY);
        var action2 = new ActionSimple(VALID_LABEL, COMPANY);

        action1.saveDailyPrice(VALID_DAY, 150.0f);
        action2.saveDailyPrice(VALID_DAY, 150.0f);

        assertEquals(action1.hashCode(), action2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentState() {
        var action1 = new ActionSimple(VALID_LABEL, COMPANY);
        var action2 = new ActionSimple("Microsoft", COMPANY);

        assertNotEquals(action1.hashCode(), action2.hashCode());
    }

    @Test
    void testValeurDefaultValue() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        var actual = action.valeur(VALID_DAY);
        assertEquals(0f, actual, 0.01f);
    }

    @Test
    void testValeurWithValidDay() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        action.saveDailyPrice(VALID_DAY, 150.0f);
        var actual = action.valeur(VALID_DAY);
        assertEquals(150.0f, actual, 0.01f);
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

    @Test
    void testGetMapCoursUnmodifiable() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        action.saveDailyPrice(VALID_DAY, 150.0f);
        assertDoesNotThrow(action::getMapCours);
    }

    @Test
    void testgetHistoryActionSimpleValeurValideParam() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        action.saveDailyPrice(VALID_DAY, 150.0f);
        assertEquals(action.getMapCours(), action.getHistoryActionSimpleValeur());
    }


     @Test
     void testGetHistoryActionSimpleValeurUnmodifiable() {
        var action = new ActionSimple(VALID_LABEL, COMPANY);
        action.saveDailyPrice(VALID_DAY, 150.0f);
        Map<Jour, Float> history = action.getHistoryActionSimpleValeur();
        assertThrows(UnsupportedOperationException.class, () -> history.put(AUTRE_JOUR, 10.0f));
    }
}
