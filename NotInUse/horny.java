package sovler;

import java.io.*;
import java.util.*;
// https://www.w3schools.com/java/java_hashmap.asp

public class Horny {

    HashMap<String, Boolean> _horn = new HashMap<String, Boolean>();

    public HashMap<String, Boolean> KBBQ(List<String> facts, List<String> logic) {
        SetFacts(facts);
        return _horn;
    }

    // Set facts into hashmap
    private void SetFacts(List<String> facts) {

        for (String s : facts) {
            _horn.put(s, true);
        }
        // check if added correctly. reference for how to use
        for (String i : _horn.keySet()) {
            System.out.println("Key: " + i + " value: " + _horn.get(i));
        }

    }

    /*
     * private boolean evaluate(List<String> logic) {
     * for (String s : logic) {
     * // split and first as we need to know if it is true given that is a
     * requirement
     * // for a ressult of false
     * // I assume that nothing will happen if there is no &
     * String[] statement = s.split("&");
     * // default value
     * boolean result = true;
     * for (String x : statement) {
     * 
     * }
     * 
     * }
     * return true;
     * }
     */

    public void parse(List<String> logic, List<String> facts) {
        // create arraylist = for boolean statements for calculation
        // loop through each statement in logic
        for (String s : logic) {
            // check for entailment
            if (s.contains("=>")) {
                // split by entail
                String[] parts = s.split("=>");
                // iniital logic values
                Boolean left = false;
                boolean right = true;
                // see if left side exactly matches to a fact
                for (String f : facts) {
                    if (parts[0].equals(f)) {
                        // if true set all as true and return results
                        left = true;
                        break;
                    }
                    // if the left side contains an and statement
                    else if (parts[0].contains("&")) {
                        boolean leftAnd = false;
                        boolean rightAnd = true;
                        String[] andStatement = parts[0].split("&");
                        for (String stringy : facts) {
                            if (andStatement[0].equals(stringy)) {
                                leftAnd = true;
                            }
                            if (andStatement[1].equals(stringy)) {
                                rightAnd = true;
                            }
                        }
                    }
                }
                // add logical conclusion.
                // note there is only 1 condition which results in false. as such we only need
                // to test until this is determined
                if (left == true && right == false) {
                    _horn.put(s, false);
                } else {
                    _horn.put(s, true);
                }
            }
        }
    }
}
