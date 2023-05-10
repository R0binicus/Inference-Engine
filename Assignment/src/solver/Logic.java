package solver;
import java.util.*;
// https://www.w3schools.com/java/ref_string_contains.asp
// https://www.w3schools.com/java/java_foreach_loop.asp
// https://www.w3schools.com/java/ref_string_replace.asp
// https://www.w3schools.com/java/java_operators.asp
// https://stackoverflow.com/questions/29582240/how-to-code-implication-equivalence-in-java
// https://stackoverflow.com/questions/69912641/creating-truth-tables-for-propositional-logic-formulas


public class  Logic {

	public Logic()
	{

	}
	// change sentences into logic statments
	public void convert(List <String> KBString)
	{
		for (String logic: KBString)
		{
			//might not need the if statmenets but I think they are okay for now
			//negation
			if(logic.contains("~"))
			{
				logic.replace("~", "!");
			}
			//conjunciton
			if(logic.contains("&"))
			{
				logic.replace("&", "&&");
			}
			//disjunciton || does not need to be replaced because it is already in the format of an or logical statement

			//implication
			if(logic.contains("=>"))
			{
				//run a method which will convert as there is no symbol
				//logic.replace("=>", "");
			}
			//biconditional
			if(logic.contains("<=>"))
			{
				logic.replace("<=>", "");
			}
		}
	}
}


