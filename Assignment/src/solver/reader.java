package solver;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class reader {
    // fields. these are stored data
    List<String> _query;
    List<String> _fact;

    public void read(String textFile) {
        List<String> textLines = readFile(textFile);
        List<String> logic = splitByCol(textLines.get(1));
        // important
        List<String> facts = getFacts(logic);
        List<String> query = getExpressions(facts, logic);

        for(String s : facts)
        {
            s.trim();
        }
        for(String s : query)
        {
            s.trim();
        }

        _fact = facts;
        _query = query;
    }

    // read in text file
    private List<String> readFile(String textFile) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(textFile));
            /*
             * for (String s : lines) {
             * System.out.println("Line read in: " + s);
             * }
             */
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return lines;
    }

    // split by ; into a new list
    private List<String> splitByCol(String textLines) {
        String[] split = textLines.split(";");
        List<String> col = Arrays.asList(split);
        /*
         * for (String s : col) {
         * System.out.println("array value: " + s);
         * }
         */
        return col;
    }

    // get the facts - valeus that will always = true
    private List<String> getFacts(List<String> logic) {
        List<String> facts = new ArrayList<String>();
        for (String s : logic) {
            if (!s.contains("=>") && !s.contains("&")) {
                facts.add(s);
            }
        }
        /*
         * for (String me : facts) {
         * System.out.println("Facts: " + me);
         * }
         */
        return facts;
    }

    // seperate facts
    private List<String> getExpressions(List<String> facts, List<String> logic) {
        List<String> modified = new ArrayList<>(logic);
        modified.removeAll(facts);
        /*
         * for (String me : modified) {
         * System.out.println("New List: " + me);
         * }
         */
        return modified;
    }
}
