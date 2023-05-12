package solver;

// https://www.w3schools.com/java/java_arraylist.asp
//https://www.w3schools.com/java/java_linkedlist.asp
// I need this here for these lists according to internet overlord
import java.util.*;

public class KnowledgeBase {
    // fields
    private ArrayList<String> horny = new ArrayList<String>();
    // I have seen facts as a thing in similar programs online so i think we will
    // need this
    private ArrayList<String> hornyFacts = new ArrayList<String>();
    // Symbols list as the pseudocode says. im not sure what we need to do with this
    // right now but it is a thing
    private ArrayList<String> hornySymbols = new ArrayList<String>();

    // kb constructor
    public KnowledgeBase(String KBtext) {
        horny = getHorny(KBtext);
        hornySymbols = getHorny(horny);
        hornyFacts = getHornyFacts();
    }

    // using linked lists might be easier
    private ArrayList<String> getHorny(String KBtext) {
        String[] colonSplit = KBtext.split(";");
        for (String horn : colonSplit) {
            {
                horny.add(horn);
            }
            return horny;
        }
    }

    private ArrayList<String> getHornySymbols(ArrayList horny) {
        // check if symbol is in list. if it is not add to list
        for (String hornyC : horny) { // negation
            if (hornyC.contains("~") && !hornySymbols.contains("~")) {
                hornySymbols.add("~");
            } // conjunction
            if (hornyC.contains("&") && !hornySymbols.contains("&")) {
                hornySymbols.add("&");
            } // disjunction
            if (hornyC.contains("||") && !hornySymbols.contains("||")) {
                hornySymbols.add("||");
            } // implication
            if (hornyC.contains("=>") && !hornySymbols.contains("=>")) {
                hornySymbols.add("=>");
            } // bi-conditional
            if (hornyC.contains("<=>") && !hornySymbols.contains("<=>")) {
                hornySymbols.add("<=>");
            }
        }
        return hornySymbols;
    }

    // this will create the list of facts / 'a' in the pseudocode
    // this is a work in progress
    public ArrayList<String> getHornyFacts() {
        for (String hornyC : horny) {
            if (!hornyC.contains("~") || !hornyC.contains("&") || !hornyC.contains("||") || !hornyC.contains("=>")
                    || !hornyC.contains("<=>")) {
                hornyFacts.add(hornyC);
            }
        }
    }
}
