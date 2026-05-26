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

public class Broker {
    private final HashMap<Action, Integer> availableActions = new HashMap<>();

    public Map<Action, Integer> getAvailableActionsMap() {
        return availableActions;
    }

    public Integer getActionCount(Action action) {
        return availableActions.get(action);
    }

    public void addAction(Action action, Integer ct) {
        if (availableActions.containsKey(action)) {
            var curr = availableActions.get(action);
            availableActions.put(action, curr + ct);
        } else {
            availableActions.put(action, ct);
        }
    }

    public void removeAction(Action action, Integer ct) {
        if(availableActions.containsKey(action)) {
            var curr = availableActions.get(action);
            int intCt = ct;
            if(curr < intCt) {
                throw new IllegalArgumentException("Not enough actions available to remove");
            }
            else if(curr == intCt) {
                availableActions.remove(action);
            }
            else {
                availableActions.put(action, curr - intCt);
            }
        }
        else {
            throw new IllegalArgumentException("Action not found in broker");
        }
    }
}
