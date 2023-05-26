package solver;
import java.util.*;

public class BackwardsChain extends CheckingMethod {

	public static ArrayList<String> facts;
	public static ArrayList<String> inferred;
	public static ArrayList<String> agenda;
	public static ArrayList<String> clauses;
	
	public BackwardsChain()
	{
		code = "bc";
		longName = "Backwards Chain";

		facts  = new ArrayList<String>();
		inferred  = new ArrayList<String>();
		agenda  = new ArrayList<String>();
		clauses  = new ArrayList<String>();
	}

	// Output string from the Entailing results
	public String MakeString()
	{
		String output = "";
	
		if (Entails() == true)
		{
			output = "YES: ";
			for (int i=inferred.size()-1;i>=0;i--)
			{
				if (i==0)
				{
					output += inferred.get(i);
				}
				else
				{
					output += inferred.get(i) + ", ";
				}
			}
		}
		else
		{
			output = "NO";
		}
		return output;		
	}
	
	// Actual algorithm
	public boolean Entails()
	{
		while(agenda.size() != 0)
		// while agenda is not empty do
		{
			String p = agenda.remove(0);
			// p <- POP(agenda)
			if (facts.contains(p) == false)
			//unless facts[p] <- true
			{
				inferred.add(p);
				ArrayList<String> symbolsList = new ArrayList<String>();
				for(int c=0;c<clauses.size();c++)
				// for each Horn clause c
				{
					if (PremiseContains(clauses.get(c),p))
					// in whose premise p appears do 
					{
						ArrayList<String> conjunct = getConjunct(clauses.get(c));
						// for each conjunct j
						for(int j=0;j<conjunct.size();j++)
						{
							symbolsList.add(conjunct.get(j));
							// add conjunct to list of symbols
						}
					}						
				}

				if (symbolsList.size() == 0)
				{
					return false;
					// if list of symbols is empty, returns false
				}
				else // otherwise
				{
					for(int s=0;s<symbolsList.size();s++)
					// for each symbol in the symbols list s
					{
						if (inferred.contains(symbolsList.get(s)) == false)
						// if s is NOT in inferred
						{
							agenda.add(symbolsList.get(s));
							// add s to agenda
						}
					}	
				}
			}
		}
		return true;
	}
	
	// Function get any conjunctions contained in the input clause
	public static ArrayList<String> getConjunct(String clause)
	{
		String premise = clause.split("=>")[0];
		ArrayList<String> conjunct = new ArrayList<String>();
		String[] conjuncts = premise.split("&|\\|\\|");
		for(int i=0;i<conjuncts.length;i++)
		{
			if (agenda.contains(conjuncts[i]) == false)
			{
				conjunct.add(conjuncts[i]);
			}
		}
		return conjunct;
	}
	
	// Function checks if the p string equals any subclauses in a clause
	public static boolean PremiseContains(String clause, String p)
	{
		String clauseSplit = clause.split("=>")[1];
		return clauseSplit.equals(p);
	}
	
	@Override
	public void Solve(List<String> Input, String Query)
	{
		agenda.add(Query);
		for (int i=0;i<Input.size();i++)
		{
			if (Input.get(i).contains("=>") == false) 
			{
				facts.add(Input.get(i));
			}
			else
			{
				clauses.add(Input.get(i));
			}
		}
		System.out.println(MakeString());
	}
}