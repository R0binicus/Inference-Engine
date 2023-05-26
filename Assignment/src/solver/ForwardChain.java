package solver;
import java.util.*;

//lecture pseudo code for reference:
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

	public static ArrayList<Integer> count;
	//count: a table, indexed by clause, initially the number of premises
	public static ArrayList<String> inferred;
	//inferred: a table, indexed by symbol, each entry initially false
	public static ArrayList<String> agenda;
	//agenda: a list of symbols, initially the symbols to be known as true
	public static ArrayList<String> clauses;
	
	public ForwardChain()
	{
		code = "fc";
		longName = "Forward Chain";

		count  = new ArrayList<Integer>();
		inferred  = new ArrayList<String>();
		agenda  = new ArrayList<String>();
		clauses  = new ArrayList<String>();
	}

	// Output string from the Entailing results
	public String MakeString(String query)
	{
		String returnString = "";
		if (Entails(query) == true)
		{
			returnString = "YES: ";
			for (int i=0;i<inferred.size();i++)
			{
				returnString += inferred.get(i) + ", ";
			}
			returnString += query;	
		}
		else
		{
			returnString = "NO";
		}
		return returnString;		
	}
	
	public boolean Entails(String query)
	{
		while(agenda.size() != 0)
		// while agenda is not empty do
		{
		 	String p = agenda.remove(0);
			// p <- POP(agenda)
			if (inferred.contains(p) == false) //unless inferred[p] <- true 		is the same as 		if inferred[p] == false right?
			// unless inferred[p] <- true
			{
				inferred.add(p);
				for (int c=0;c<clauses.size();c++)
				// for each Horn clause c
				{
					if (PremiseContains(clauses.get(c),p))
					// in whose premise p appears do 
					{
					Integer i = count.get(c);
					count.set(c,--i);
					// decrement count[c]
						if (count.get(c) == 0)
						// if count[c] = 0 then do
						{
							String head = clauses.get(c).split("=>|<=>")[1]; // | means or 
							if (head.equals(query))
							// if HEAD[c] = q then return true
							{
								return true;
							}
							agenda.add(head);
							// PUSH[c], agenda					
						}
					}
				}
			}
		}
		return false;
	}
	
	// Function checks if the p string appears in any of the clauses
	public static boolean PremiseContains(String clause, String p)
	{
		String premise = clause.split("=>")[0];
		String[] conjuncts = premise.split("&|\\|\\|"); // need the \\ symbols because | is a regex symbol
		// Split the string by the logic symbols
		if (conjuncts.length == 1)
		{
			return premise.equals(p);
		}
		else
		{
			return Arrays.asList(conjuncts).contains(p);
		}
	}
	
	@Override
	public void Solve(List<String> Input, String Query)
	{
		//use input to make agenda, clauses and count
		for (int i=0;i<Input.size();i++)
		{
			if (Input.get(i).contains("=>") == false) 
			{
				agenda.add(Input.get(i));
			}
			else
			{
				clauses.add(Input.get(i));
				count.add(Input.get(i).split("&|\\|\\|").length);
			}
		}
		System.out.println(MakeString(Query));
	}
}