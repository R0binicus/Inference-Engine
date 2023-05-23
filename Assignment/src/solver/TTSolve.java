package solver;

import java.io.*;
import java.util.*;

public class SolveTT {
    String result;
    Stack<String> steps = new Stack<String>();

    // new version of TTSolver. I am currently trying to constuct a list of the
    // requred steps to solve the query. expected: [p1=>d ,p3 => p1 ,p2=> p3]
    // I am still wokring on this but any input is apreciated
    // assumption is that we will loop throgh the lsit splittng the left side to
    // determin the right.
    // also probably do not need to use a stack
    public void TTsolve(HashMap<String, List<Integer>> kb, String query, List<String> horny) {
        requiredSteps(query, horny);

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
        System.out.println(parts[0]);
        // test for next action
        goal = parts[0];
        var value = loopMe(goal, horny);
        steps.push(value);
        System.out.println(steps);
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
}