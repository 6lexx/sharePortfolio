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
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class CompanyTest {
    public static final String VALID_NAME = "Apple";
    public static final String INVALID_NAME = null;

    @Test
    void testConstructorEmpty() {
        assertDoesNotThrow(() -> new CompanyTest());
    }

    @Test
    void testConstructorWithParameters() {
        assertDoesNotThrow(() -> new Company(VALID_NAME));
    }
    
    @Test
    void testConstructorWithInvalidParameters() {
        assertDoesNotThrow(() -> new Company(INVALID_NAME));
    }

    @Test
    void testGetName() {
        var company = new Company(VALID_NAME);
        var name = company.getName();
        assertTrue(name.equals(VALID_NAME));
    }

    @Test
    void testGetNameInvalid() {
        var company = new Company(INVALID_NAME);
        var name = company.getName();
        assertTrue(name == INVALID_NAME);
    }
}
