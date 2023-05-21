package solver;

import java.io.*;
import java.util.*;

public class SolveTT {
    String result;

    public void TTsolve(HashMap<String, List<Integer>> kb, String query, List<String> horny) {
        var hornyMap = hornyMap(kb, horny);
        // might not need this q
        var q = queryMap(query, hornyMap);
        var result = solveQuery(hornyMap, query);
        // System.out.println(hornyMap);
        // System.out.println(q);

    }

    private HashMap<String, List<Integer>> queryMap(String query, HashMap<String, List<Integer>> hornymap) {
        HashMap<String, List<Integer>> querymap = new HashMap<String, List<Integer>>();
        querymap.put(query, new ArrayList<Integer>());
        return querymap;
    }

    private HashMap<String, List<Integer>> hornyMap(HashMap<String, List<Integer>> kb, List<String> horny) {
        HashMap<String, List<Integer>> hornymap = new HashMap<String, List<Integer>>();

        int TTsize = (int) Math.pow(2, kb.size());

        for (String s : horny) {
            hornymap.put(s, new ArrayList<Integer>());
        }
        // works up to here I know for sure
        for (String s : hornymap.keySet()) {
            System.out.println("Selected: " + s);
            var list = hornymap.get(s);
            if (s.contains("=>")) {
                // split and compare
                String[] implicaiton = s.split("=>");
                var left = implicaiton[0].replaceAll("\\s+", "");
                var right = implicaiton[1].replaceAll("\\s+", "");

                var selectLeft = kb.get(left);
                var selectRight = kb.get(right);
                // get via key

                for (int i = 0; i < TTsize; i++) {
                    if (selectLeft.get(i) == 1 && selectRight.get(i) == 0) {
                        list.add(0);
                        // add 0 - false
                    } else {
                        // all other outcomes result in true unless there is a null
                        list.add(1);
                    }
                }
                // compare each side based on implication logic
            } else if (!s.contains("=>")) {
                for (int i = 0; i < TTsize; i++) {
                    var x = kb.get(s);
                    list.add(x.get(i));
                }
                // fill
            }
            System.out.println("Selected: " + s + " Count " + list.size());
        }
        return hornymap;
    }

    private String solveQuery(HashMap<String, List<Integer>> hornyMap, String query) {
        String answer = "Result Test: ";
        List<Integer> select = new ArrayList<>();
        for (String s : hornyMap.keySet()) {
            if (s.contains(query)) {
                System.out.println(s);
                select = hornyMap.get(s);
                System.out.println(select);
            }
        }
        Integer sum = 0;
        for (Integer i : select) {
            sum += i;
        }
        System.out.println(sum);
        return answer;
    }
}