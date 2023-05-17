package solver;
import java.util.*;




//lecture pseudo code:
// 	function PL-FC-Entails?(KB, q) returns true or false
//		local variables:
//		count:		a table, indexed by clause, initially the number of premises
//		inferred:	a table, indexed by symbol, each entry initially false
//		agenda:		a list of symbols, initially the symbols to be known as true
//
//		while agenda is not empty do
//			p <- POP(agenda)
//			unless inferred[p] <- true									//if inferred[p] != true ///	if inferred[p] == false
//			for each Horn clause c in whose premise p appears do 
//				decrement count[c]
//				if count[c] = 0 then do
//					if HEAD[c] = q then return true
//					PUSH[c], agenda
//		return false

public class ForwardChain extends CheckingMethod {
	
	public ForwardChain()
	{
		code = "FC";
		longName = "Forward Chain";
	}

	private Boolean Entails(HashMap<String, Boolean> KB, String Input)
	//function PL-FC-Entails?(KB, q) returns true or false
	{
		int count = KB.size(); 
		// count:		a table, indexed by clause, initially the number of premises
		HashMap<String, Boolean> inferred = new HashMap<String, Boolean>();
		// inferred:	a table, indexed by symbol, each entry initially false
		HashMap<String, Boolean> agenda = new HashMap<String, Boolean>();
		agenda = KB;
		// agenda:		a list of symbols, initially the symbols to be known as true

		while (agenda.size() <= 0)
		// while agenda is not empty do
		{
			String p = agenda.keySet().iterator().next();
			// p <- POP(agenda)
			if (inferred.get(p) == false) //unless inferred[p] <- true 		is the same as 		if inferred[p] == false right?
			// unless inferred[p] <- true
			{
				for (String clause : agenda.keySet()) { 	//not done yets needs to also check if the clause contains the input
				// for each Horn clause c in whose premise p appears do
					String clauseStr = clause;			
					Boolean clauseBool = agenda.get(clauseStr);
	
					agenda.remove(clauseStr);
					// decrement count[c]
					if (count <= 0)
					// if count[c] = 0 then do
					{
						if (clauseStr == Input) 		//not done 
						//if HEAD[c] = q then return true
						{
							return true; 
						}
						
						agenda.put(clauseStr, clauseBool);
						// PUSH[c], agenda
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void Solve() {
		System.out.println("The forward chain just ran succesfully!");
	}

	
}