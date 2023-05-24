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
					output += inferred.get(i)+", ";
				}
			}
		}
		else
		{
			output = "NO";
		}
		return output;		
	}
	
	public boolean Entails()
	{
		while(!agenda.isEmpty())
		{
			String p = agenda.remove(agenda.size()-1);
			inferred.add(p);
		
			if (!facts.contains(p))
			{
				ArrayList<String> symbolsList = new ArrayList<String>();
				for(int i=0;i<clauses.size();i++)
				{
					if (PremiseContains(clauses.get(i),p))
					{
						ArrayList<String> temp = getPremises(clauses.get(i));
						for(int x=0;x<temp.size();x++)
						{
							symbolsList.add(temp.get(x));
						}
					}						
				}

				if (symbolsList.size()==0){
					return false;
				}
				else
				{
					for(int i=0;i<symbolsList.size();i++)
					{
						if (!inferred.contains(symbolsList.get(i)))
						{
							agenda.add(symbolsList.get(i));
						}
					}	
				}
			}
		}
		return true;
	}
	
	public static ArrayList<String> getPremises(String clause)
	{
		String premise = clause.split("=>")[0];
		ArrayList<String> temp = new ArrayList<String>();
		String[] conjuncts = premise.split("&|\\|\\|");
		for(int i=0;i<conjuncts.length;i++)
		{
			if (!agenda.contains(conjuncts[i]))
			{
				temp.add(conjuncts[i]);
			}
		}
		return temp;
	}
	

	public static boolean PremiseContains(String clause, String p)
	{
		String conclusion = clause.split("=>")[1];
		if (conclusion.equals(p))
		{
			return true;
		}
		else
		{
			return false;
		}
	
	}
	
	@Override
	public void Solve(List<String> Input, String Query)
	{
		agenda.add(Query);
		for (int i=0;i<Input.size();i++)
		{
			if (!Input.get(i).contains("=>")) 
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