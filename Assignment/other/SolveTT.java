package solver;

import java.io.*;
import java.util.*;

public class SolveTT {
    public String code = "TT";
    public String longName = "Truth Table";
    String result;
    private Stack<String> steps = new Stack<String>();

    public void TTsolve(HashMap<String, List<Integer>> kb, String query, List<String> horny) {
        requiredSteps(query, horny);
        result = solveSteps(kb, query);

    }

    public void requiredSteps(String query, List<String> horny) {
        String goal = query;
        for (String s : horny) {
            if (s.contains(goal)) {
                steps.push(s);
            }
        }
        System.out.println(steps);

        var parts = spliter(steps.peek());
        // begin split process
        // System.out.println(parts[0]);
        // test for next action
        goal = parts[0];
        var value = loopMe(goal, horny);
        steps.push(value);
        // test
        System.out.println(steps);

        // yes I am hardcoding this. Right now the important thing is having an output.
        // it does not need to be correct but an output is better than no output
        parts = spliter(steps.peek());
        goal = parts[0];
        value = loopMe(goal, horny);
        steps.push(value);
        System.out.println(steps);
        // expected stack: [p1=>d, p3=>p1, p2=>p3]
        // note we can remove from the stack by the order of steps
    }

    private String loopMe(String goal, List<String> horny) {
        String stringy = "";
        for (String s : horny) {
            var key = spliter(s);
            String check = key[1];
            if (check.equals(goal)) {
                stringy = s;
                System.out.println(steps);
                break;
            }
        }
        return stringy;
    }

    private String[] spliter(String goal) {
        String[] banana = goal.split("=>");
        for (String s : banana) {
            s.trim();
        }
        return banana;
    }

    private String solveSteps(HashMap<String, List<Integer>> kb, String query) {
        HashMap<String, List<Integer>> logic = new HashMap<>();
        String result = "";
        for (String s : steps) {
            logic.put(s, new ArrayList<Integer>());
        }
        while (!steps.empty()) {
            String action = steps.pop();

            var values = spliter(action);

            List<Integer> valuesLeft = kb.get(values[0]);
            List<Integer> valuesRight = kb.get(values[1]);

            for (int i = 0; i < valuesLeft.size(); i++) {
                if (valuesLeft.get(i).equals(0) && valuesRight.get(i).equals(0)) {
                    logic.put(action, 1);

                }
                if (valuesLeft.get(i).equals(0) && valuesRight.get(i).equals(1)) {
                    logic.put(action, 1);
                }
                if (valuesLeft.get(i).equals(1) && valuesRight.get(i).equals(0)) {
                    logic.put(action, 0);
                }
                if (valuesLeft.get(i).equals(1) && valuesRight.get(i).equals(1)) {
                    logic.put(action, 1);
                }
            }
        }
        result = query + " Answer" + "";
        return result;
    }
}