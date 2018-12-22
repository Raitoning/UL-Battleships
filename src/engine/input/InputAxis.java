package engine.input;

import engine.Mathf;

import java.util.ArrayList;

public class InputAxis {

    private String name;
    private float value;
    private ArrayList<Integer> positiveModifiers;
    private ArrayList<Integer> negativeModifiers;

    public InputAxis(String name, ArrayList<Integer> positiveModifiers, ArrayList<Integer> negativeModifiers) {

        this.name = name;
        this.positiveModifiers = positiveModifiers;
        this.negativeModifiers = negativeModifiers;
    }

    void update(ArrayList<Integer> keyEvents) {

        boolean isModified = false;

        for (Integer keyEvent : keyEvents) {

            if (positiveModifiers.contains(keyEvent)) {

                isModified = true;
                value = Mathf.clamp(value + 1f, -1f, 1f);
            } else if (negativeModifiers.contains(keyEvent)) {

                isModified = true;
                value = Mathf.clamp(value - 1f, -1f, 1f);
            }
        }

        if (!isModified) {

            value = 0f;
        }
    }

    float getValue() {

        return value;
    }

    String getName() {

        return name;
    }
}
