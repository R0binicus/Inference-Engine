package solver;
import java.util.*;

public class BackwardsChain extends CheckingMethod {

	public static String tell;
	public static String ask;

	public static ArrayList<String> facts;
	public static ArrayList<String> entailed;
	public static ArrayList<String> agenda;
	public static ArrayList<String> clauses;
	
	public BackwardsChain()
	{
		code = "BC";
		longName = "Backwards Chain";

		facts  = new ArrayList<String>();
		entailed  = new ArrayList<String>();
		agenda  = new ArrayList<String>();
		clauses  = new ArrayList<String>();

		tell = "p2=>p3;p3=>p1;c=>e;b&e=>f;f&g=>h;p1=>d;p1&p3=>c;a;b;p2;";
		ask = "d";
	}

	public static void init(String tell)
	{
		init(tell);
		agenda.add(ask);
	  	String[] sentences = tell.split(";");
		for (int i=0;i<sentences.length;i++)
		{
			if (!sentences[i].contains("=>")) 
			{
				facts.add(sentences[i]);
			}
			else
			{
				clauses.add(sentences[i]);
			}
		}
	}
	
	public String MakeString()
	{
		String output = "";
	
		if (bcentails())
		{
			output = "YES: ";
			for (int i=entailed.size()-1;i>=0;i--)
			{
				if (i==0)
				{
					output += entailed.get(i);
				}
				else
				{
					output += entailed.get(i)+", ";
				}
			}
		}
		else{
				output = "NO";
		}
		return output;		
	}
	
	public boolean bcentails()
	{
		while(!agenda.isEmpty())
		{
			String q = agenda.remove(agenda.size()-1);
			entailed.add(q);
		
			if (!facts.contains(q))
			{
				ArrayList<String> p = new ArrayList<String>();
				for(int i=0;i<clauses.size();i++)
				{
					if (conclusionContains(clauses.get(i),q))
					{
						ArrayList<String> temp = getPremises(clauses.get(i));
						for(int j=0;j<temp.size();j++)
						{
							p.add(temp.get(j));
						}
					}						
				}

				if (p.size()==0){
					return false;
				}
				else
				{
					for(int i=0;i<p.size();i++)
					{
						if (!entailed.contains(p.get(i)))
						{
							agenda.add(p.get(i));
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
	

	public static boolean conclusionContains(String clause, String c)
	{
		String conclusion = clause.split("=>")[1];
		if (conclusion.equals(c))
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

