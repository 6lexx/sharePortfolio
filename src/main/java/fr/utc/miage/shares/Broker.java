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
    private final HashMap<String, Action> actions = new HashMap<>();

    public Map<String, Action> getActions() {
        return actions;
    }

    public Action getAction(String libelle) {
        return actions.get(libelle);
    }

    public void addAction(Action action) {
        if(actions.containsKey(action.getLibelle())) {
            throw new IllegalArgumentException("An action with the same libelle already exists");
        }
        actions.put(action.getLibelle(), action);
    }

    public void removeAction(String libelle) {
        if(!actions.containsKey(libelle)) {
            throw new IllegalArgumentException("An action with the label does not exist");
        }
        actions.remove(libelle);
    }

}
