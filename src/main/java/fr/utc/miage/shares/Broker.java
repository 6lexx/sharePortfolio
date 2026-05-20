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
