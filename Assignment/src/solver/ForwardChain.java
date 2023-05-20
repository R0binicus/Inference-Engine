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

	List<String> inferred = new ArrayList<>();
	
	public ForwardChain()
	{
		code = "FC";
		longName = "Forward Chain";
	}

	private Boolean Entails(List<String> KB, String Input)
	//function PL-FC-Entails?(KB, q) returns true or false
	{
		int count = KB.size(); 
		// count:		a table, indexed by clause, initially the number of premises
		//List<String> inferred = new ArrayList<>();
		// inferred:	a table, indexed by symbol, each entry initially false
		List<String> agenda = new ArrayList<>();
		agenda = KB;
		// agenda:		a list of symbols, initially the symbols to be known as true

		while (agenda.size() != 0)
		// while agenda is not empty do
		{
			//String p = agenda.keySet().iterator().next();
			String p = agenda.remove(0);
			// p <- POP(agenda)
			if (inferred.contains(p) == false) //unless inferred[p] <- true 		is the same as 		if inferred[p] == false right?
			// unless inferred[p] <- true
			{
				inferred.add(p);
				for (int i=0;i<KB.size();i++){	//not done yets needs to also check if the clause contains the input
				// for each Horn clause c 
					if (ClauseContains(KB.get(i), p))
					// in whose premise p appears do
					{
						count--;
						// decrement count[c]
						if (count <= 0)
						// if count[c] = 0 then do
						{
							String head = getHead(KB.get(i));
							if (head.equals(Input))
							//if HEAD[c] = q then return true
							{
								return true; 
							}

							agenda.add(KB.get(i));
							// PUSH[c], agenda
						}
					}	
				}
			}
		}
		return false;
	}

	public static String getHead(String Clause){
		String[] subClauses1;
		subClauses1 = Clause.split("=>|<=>|&|\\|\\|");

		return subClauses1[subClauses1.length - 1];
	}

	public static boolean ClauseContains(String subClause, String p){
		// check if p is in the list of strings
		String[] subClausesProcessed = null;
		subClausesProcessed = subClause.split("=>|<=>|&|\\|\\|");
		if (subClausesProcessed.length == 1)
			return subClausesProcessed[0].equals(p);
		else
		{
			return Arrays.asList(subClausesProcessed).contains(p);
		}
	} 

		// method which calls the main fcentails() method and returns output back to iengine
public String run(List<String> KB, String Input){
	String output = "";
	if (Entails(KB, Input)){
			// the method returned true so it entails
			output = "True: ";
			// for each entailed symbol
			for (int i=0;i<inferred.size();i++){
					output += inferred.get(i)+", ";
				}
			output += Input;	
	}
	else{
			output = "False";
	}
	return output;		
}
	
	@Override
	public void Solve(List<String> KB, String Input) {
		System.out.println("The forward chain just ran succesfully!");


	}
}