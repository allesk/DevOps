package me.rest.test;

import java.util.HashMap;
import java.util.Map;

public class StateDirectory {
    private Map<String, String> abbreviationLookup;
    private Map<String, State> statesDirectory;

    public StateDirectory(State[] states) {
        statesDirectory = new HashMap<>();
        abbreviationLookup = new HashMap<>();
        for (State state : states) {
            statesDirectory.put(state.getName().toLowerCase(), state);
            abbreviationLookup.put(state.getAbbr().toLowerCase(), state.getName().toLowerCase());
        }
    }

    public State getState(String stateAbbreviationOrName) {
        String state = stateAbbreviationOrName.trim().toLowerCase();
        if (state.length() == 2) {
            if (abbreviationLookup.containsKey(state)) {
                state = abbreviationLookup.get(state);
            } else {
                state = null;
            }
        }
        if (statesDirectory.containsKey(state)) {
            return statesDirectory.get(state);
        }
        return null;
    }

}
