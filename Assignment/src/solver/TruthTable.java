package solver;
import java.util.*;

// https://www.freecodecamp.org/news/javascript-split-how-to-split-a-string-into-an-array-in-js/
// https://www.w3schools.com/java/java_arraylist.asp
// https://www.geeksforgeeks.org/array-get-method-in-java/
// https://www.appsdeveloperblog.com/java-stream-count-operation/
/* pseudocode for the truth table
TT-E NTAILS ?(KB , α) returns true or false
inputs: KB , the knowledge base, a sentence in propositional logic
α, the query, a sentence in propositional logic
symbols ← a list of the proposition symbols in KB and α
return TT-C HECK -A LL (KB , α, symbols, { })

function TT- CHECK -A LL (KB, α, symbols , model ) returns true or false
if EMPTY ?(symbols) then
	if PL-TRUE ?(KB , model ) then return PL-T RUE ?(α, model )
	else return true // when KB is false, always return true
else do
P ← F IRST (symbols)
rest ← R EST (symbols)
return (TT-C HECK -A LL (KB, α, rest, model ∪ {P = true}) and
	TT-C HECK -A LL (KB , α, rest, model ∪ {P = false }))
 */

public class TruthTable extends CheckingMethod {
	// this is here for ease of testing and will be removed later.
	// constructor
	public TruthTable(String KBString, String Query )
	{	// I am not sure what these 2 things are doing. Can you explain when you see this. thanks.
		code = "TT";
		longName = "Truth Table";
	}

	public static boolean TTEntails(String KBString, String Alpha)
	{	// I think a is 'd' in the test
		//symbols <-- list of probositions in kb and a
		List<String> KB = GetPropositions(String KBString, String Alpha);
		List<String> Symbols = GetSymbols(var KB, String Alpha)
		TTCheckAll(KB);
	}

	private List GetSymbols (List <String> KB, String Alpha){

	List<String> Symbols = new ArrayList<>();
		for(int i = 0; i < KB.stream().count()){

			string sym = KB.get(i);
			// somthing about the symbols remove stuff or somthing idk. I think we will use this to constriucth the logic
			symbols.add(sym);
		}
	return symbols;
	}
	private static boolean TTCheckAll(KB){

		while (!KB.isEmpty()){
			// remove first item in arraylist
			string check = KB.remove(0);
		}

	}

	// create the list
	private List <String> GetPropositions(String KBString, String Alpha)
	{
		List<String> KB = new ArrayList<>();
		 String[] sections = KBstring.split(";");
		 for(String arg : sections)
		 {	// remove white spaces
			 KB.add(arg.trim());
		 }
		 // return the list
	return KB;
	}

	Private static boolean TTCheckAll()
	{
		// what is true and what is false
	}
	// I assume this is where we want want our output to go.


	@Override
	public void Solve() {
		System.out.println("The truth table just ran succesfully!");
	}
}

