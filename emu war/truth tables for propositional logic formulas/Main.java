import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class TableMain
{

    public static void main(String[] args) {
        new TruthTable().make("￢A");
        //new TruthTable().make("￢A ∧ B");
        //new TruthTable().make("(￢A ∧ B) ∨ C");
        //new TruthTable().make("((￢A ∧ B) ∨ C) ∧ A");
    }

}