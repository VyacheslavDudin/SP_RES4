package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Automat {
    public Automat(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner fileReader = new Scanner(file);

        int stateCount = 0;
        if (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            stateCount = Integer.parseInt(line);
        }

        String line = "";
        String[] stateData;
        for (int i = 0; i < stateCount; i++) {
            if (fileReader.hasNextLine()) {
                line = fileReader.nextLine();
                stateData = line.split(" ");
                states.add(Integer.parseInt(stateData[0]), new State(stateData[0], Boolean.parseBoolean(stateData[1])));
            }
        }
        String[] transData;
        while (fileReader.hasNextLine()) {
            line = fileReader.nextLine();
            transData = line.split(" ");
            State startState = states.get(Integer.parseInt(transData[0]));
            State finishState = states.get(Integer.parseInt(transData[2]));
            startState.transitions.add(new Transition(transData[1], finishState));
        }

    }
    ArrayList<State> states = new ArrayList<>();
    public State goToNextState(char ch, State currentState) {
        for (Transition trans : currentState.transitions) {
            if (Character.toString(ch).matches(trans.perceivedCharacters)) {
                return trans.nextState;
            }
        }
        return new State("-1");
    }
    public boolean isLexem(String word) {
        State currentState = states.get(0);
        for (char ch: word.toCharArray()) {
            currentState = goToNextState(ch, currentState);
            if (currentState.name.compareTo("-1") == 0) {
                return false;
            }
        }
        if (currentState.isFinal == true) {
            return true;
        } else {
            return false;
        }
    }
}
