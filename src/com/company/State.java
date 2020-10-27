package com.company;

import java.util.ArrayList;

public class State {
    public State(String _name) {
        name = _name;
    }
    public State(String _name, boolean _isFinal) {
        name = _name;
        isFinal = _isFinal;
    }
    public String name;
    public ArrayList<Transition> transitions = new ArrayList<>();
    public boolean isFinal = false;
}
