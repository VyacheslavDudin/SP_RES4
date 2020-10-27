package com.company;

public class Transition {
    public Transition(String _characters, State state) {
        perceivedCharacters = _characters;
        nextState = state;
    }
    public String perceivedCharacters;
    public State nextState;
}
