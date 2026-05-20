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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BrokerTest {
    private static final Company COMPANY1 = new Company("company1");
    private static final Company COMPANY2 = new Company("company2");
    private static final Company COMPANY3 = new Company("company3");
    private static final Company COMPANY4 = new Company("company4");

    private static final Action ACTION_SIMPLE = new ActionSimple("action1", COMPANY1);
    private static final Map<Company, Float> ETFS = Map.of(COMPANY1, 0.5f, COMPANY2, 0.5f);
    
    private static final Action ACTION_ETF = new ExchangeTradedFund("action2", ETFS);
    
    private static final Map<String, Action> ACTIONS = Map.of(
        "action1", ACTION_SIMPLE,
        "action2", ACTION_ETF
    );

    @Test
    void testGetAction() {
        var broker = new Broker();

        broker.addAction(ACTION_SIMPLE);
        broker.addAction(ACTION_ETF);
        
        assertTrue(broker.getAction("action1").equals(ACTION_SIMPLE));
        assertTrue(broker.getAction("action2").equals(ACTION_ETF));
    }

    @Test
    void testAddAction() {
        var broker = new Broker();

        broker.addAction(ACTION_SIMPLE);
        assertTrue(broker.getActions().equals(Map.of("action1", ACTION_SIMPLE)));
    }

    @Test
    void testAddActionDuplicate() {
        var broker = new Broker();

        broker.addAction(ACTION_SIMPLE);
        assertThrows(IllegalArgumentException.class, () -> broker.addAction(ACTION_SIMPLE));
    }

    @Test
    void testRemoveAction() {
        var broker = new Broker();

        broker.addAction(ACTION_SIMPLE);
        broker.addAction(ACTION_ETF);
        broker.removeAction("action1");
        assertTrue(broker.getActions().equals(Map.of("action2", ACTION_ETF)));
    }

    @Test
    void testRemoveActionNotExist(){
        var broker = new Broker();

        broker.addAction(ACTION_ETF);
        assertThrows(IllegalArgumentException.class, () -> broker.removeAction("action1"));
    }
}
