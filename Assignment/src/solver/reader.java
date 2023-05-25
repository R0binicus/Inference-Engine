package solver;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class reader {
    // fields. these are stored data
    List<String> _horny;
    String _query;
    List<String> _symbols;

    public void read(String textFile) {
        List<String> textLines = readFile(textFile);
        List<String> horn = splitByCol(textLines.get(1));
        List<String> symbols = getSymbols(horn);
        _symbols = symbols;
        _horny = horn;
        _query = textLines.get(3).replaceAll("\\s+", "");
    }

    // read in text file
    private List<String> readFile(String textFile) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(textFile));
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Error");
        }
        return lines;
    }

    // split by ; into a new list
    private List<String> splitByCol(String textLines) {
        String[] split = textLines.split(";");
        List<String> col = new ArrayList(split.length);
        for (String s : split) {
            // s.replaceAll("\u00A0", "");
            col.add(s.replaceAll("\\s+", ""));
        }
        return col;
    }

    private List<String> getSymbols(List<String> horn) {
        List<String> symbols = new ArrayList<String>();
        for (String s : horn) {
            String[] split = s.split("=>");
            for (String st : split) {
                symbols.add(st.replaceAll("\\s+", ""));
            }
        }
        return symbols;
    }
}
