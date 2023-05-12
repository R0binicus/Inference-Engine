package solver;

// https://www.w3schools.com/java/java_arraylist.asp
// I need this here for these lists according to internet overlord
import java.util.ArrayList;
import java.util.*;

public class KnowledgeBase {
    // fields
    private ArrayList<HornClause> horny;
    // I have seen facts as a thing in similar programs online so i think we will
    // need this
    private ArrayList<String> hornyFacts;
    // Symbols list as the pseudocode says
    private ArrayList<String> hornySymbols;

    // kb constructor
    public class KnowledgeBase()
    {

        }

        public ArrayList<HornClause> getHorny() {

        }

        ic ArrayList<String> getHornySymbols(){
        //check if symbol is in list. if it is not add to list
        foreach(HornClause hornyC: horny)
        {   //negation
            if(hornyC.contains("~") && !hornySymbols.contains("~"))
            {
                hornySymbols.add("~");
            }//conjunction
            if(hornyC.contains("&")&& !hornySymbols.contains("&"))
            {
                hornySymbols.add("&");
            }//disjunction
            if(hornyC.contains("||") && !hornySymbols.contains("||"))
            {
                hornySymbols.add("||");
            }//implication
            if(hornyC.contains("=>") && !hornySymbols.contains("=>"))
            {
                hornySymbols.add("=>");
            }//bi-conditional
            if(hornyC.contains("<=>") && !hornySymbols.contains("<=>"))
            {
                hornySymbols.add("<=>");
            }
        }
    }
    /

        public ArrayList<String> getHornyFacts() {
        foreach(HornClause hornyC : horny)
        {
            if(hornyC )
            {
                hornyFacts.add(hornyC);
            }      
        }
    }

    public 
}
