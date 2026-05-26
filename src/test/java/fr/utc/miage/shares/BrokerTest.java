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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BrokerTest {

    private Broker broker;
    private Action action1;
    private Action action2;

    @BeforeEach
    void setUp() {
        broker = new Broker();
        action1 = new ActionSimple("AAPL", new Company("Apple"));
        action2 = new ActionSimple("GOOG", new Company("Google"));
    }

    @Test
    void getAvailableActionsMap_shouldReturnEmptyMapInitially() {
        Map<Action, Integer> map = broker.getAvailableActionsMap();

        assertNotNull(map);
        assertTrue(map.isEmpty());
    }

    @Test
    void addAction_shouldAddNewAction() {
        broker.addAction(action1, 10);

        assertEquals(10, broker.getActionCount(action1));
        assertEquals(1, broker.getAvailableActionsMap().size());
    }

    @Test
    void addAction_shouldIncreaseExistingActionCount() {
        broker.addAction(action1, 10);
        broker.addAction(action1, 5);

        assertEquals(15, broker.getActionCount(action1));
        assertEquals(1, broker.getAvailableActionsMap().size());
    }

    @Test
    void removeAction_shouldDecreaseCount() {
        broker.addAction(action1, 10);

        broker.removeAction(action1, 4);

        assertEquals(6, broker.getActionCount(action1));
    }

    @Test
    void removeAction_shouldRemoveEntryWhenCountBecomesZero() {
        broker.addAction(action1, 10);

        broker.removeAction(action1, 10);

        assertNull(broker.getActionCount(action1));
        assertTrue(broker.getAvailableActionsMap().isEmpty());
    }

    @Test
    void removeAction_shouldThrowWhenNotEnoughActions() {
        broker.addAction(action1, 3);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> broker.removeAction(action1, 5)
        );

        assertEquals("Not enough actions available to remove", ex.getMessage());
    }

    @Test
    void removeAction_shouldThrowWhenActionNotFound() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> broker.removeAction(action1, 1)
        );

        assertEquals("Action not found in broker", ex.getMessage());
    }

    @Test
    void addAction_shouldHandleDifferentActionsIndependently() {
        broker.addAction(action1, 10);
        broker.addAction(action2, 7);

        assertEquals(10, broker.getActionCount(action1));
        assertEquals(7, broker.getActionCount(action2));
        assertEquals(2, broker.getAvailableActionsMap().size());
    }
}