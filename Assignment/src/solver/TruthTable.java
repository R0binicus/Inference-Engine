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

	ArrayList<String> _ttkb;

	public void TT(List<String> horn, List<String> symbols, String query) {

		var unique = getTTCount(symbols);
		var emptyTT = generateTT(unique);
		System.out.println(emptyTT);
		// return _ttkb;
	}

	private ArrayList<String> getTTCount(List<String> symbols) {
		ArrayList<String> unique = new ArrayList<String>();
		for (String s : symbols) {
			if (!unique.contains(s)) {
				unique.add(s);
			}
		}
		for (String me : unique) {
			System.out.println("unique: " + me);
		}
		System.out.println(unique);

		return unique;
	}

	private HashMap<String, List<Integer>> generateTT(List<String> unique) {
		HashMap<String, List<Integer>> TT = new HashMap<String, List<Integer>>();
		// +1 for the query
		int colnum = unique.size();
		Double power = Math.pow(2, colnum);
		int powerInt = power.intValue();
		// int listSize = (powerInt / colnum);
		System.out.println("Size of each " + powerInt);

		for (int i = 0; i < colnum; i++) {
			// add data into HashMap
			TT.put(unique.get(i), new ArrayList<Integer>(powerInt));
		}
		// fill facts
		for (String s : TT.keySet()) {
			// all parts that do not contain an and statement. The and statement is reliant
			// on these being fileld first
			if (!s.contains("&")) {
				for (int i = 0; i < powerInt; i++) {
					int num = 0;
					var list = TT.get(s);
					list.add(num);
				}

			}
		}

		return TT;
	}

	public TruthTable()// (String KBString, String Query) please don't add any parameters here, they
						// are for the Solve() function
	{ // I am not sure what these 2 things are doing. Can you explain when you see
		// this. thanks.

		// I added some explaination
		// the CheckingMethod.java has an explaination commented there too

		code = "TT";
		// the code is the input name to be used when using the command prompt
		longName = "Truth Table";
		// the longName is just the full name, to be used if you want to print the name
		// of the check method being run
	}

	@Override
	public void Solve() {
		System.out.println("The truth table just ran succesfully!");
	}
}
