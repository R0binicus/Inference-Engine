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

public class ForwardChainPlagiarism extends CheckingMethod {

	public static List<String> tell;
	public static String ask;
	public static ArrayList<String> agenda;
	
	public static ArrayList<String> facts;
	public static ArrayList<String> clauses;
	public static ArrayList<Integer> count;
	public static ArrayList<String> inferred;
	
	public ForwardChainPlagiarism()
	{
		code = "FC";
		longName = "Forward Chain";

		agenda  = new ArrayList<String>();
		clauses  = new ArrayList<String>();
		inferred  = new ArrayList<String>();
		facts  = new ArrayList<String>();
		count  = new ArrayList<Integer>();
		tell = new ArrayList<String>();
		//ask = "d";
		//init(tell);

		
	}

public String execute(){
	String output = "";
	if (Entails()){
			output = "TRUE: ";
			for (int i=0;i<inferred.size();i++){
					output += inferred.get(i)+", ";
				}
			output += ask;	
	}
	else{
			output = "FALSE";
	}
	return output;		
}
 
public boolean Entails(){
while(!agenda.isEmpty()){
	 	String p = agenda.remove(0);
		inferred.add(p);
		for (int i=0;i<clauses.size();i++){
			if (premiseContains(clauses.get(i),p)){
			Integer j = count.get(i);
			count.set(i,--j);
				if (count.get(i)==0){
					String head = clauses.get(i).split("=>")[1];
					if (head.equals(ask))
						return true;
					agenda.add(head);					
				}
			}	
		}
	}
	return false;
}

public static void init(List<String> tell){
	//String[] sentences = tell.split(";");
	for (int i=0;i<tell.size();i++){
		//System.out.println(tell.get(i));
		if (!tell.get(i).contains("=>")) 
			agenda.add(tell.get(i));
		else{
			clauses.add(tell.get(i));
			count.add(tell.get(i).split("&").length);
		}
	}
}

public static boolean premiseContains(String clause, String p){
	String premise = clause.split("=>")[0];
	String[] conjuncts = premise.split("&");
	if (conjuncts.length==1)
		return premise.equals(p);
	else
		return Arrays.asList(conjuncts).contains(p);
}
	
	@Override
	public void Solve(List<String> KB, String Input) {
		tell = KB;
		ask = Input;
		init(tell);
		System.out.println(execute());
	}
}