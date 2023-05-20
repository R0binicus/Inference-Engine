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

	public static String tell;
	public static String ask;
	public static ArrayList<String> agenda;
	
	public static ArrayList<String> facts;
	public static ArrayList<String> clauses;
	public static ArrayList<Integer> count;
	public static ArrayList<String> entailed;
	
	public ForwardChainPlagiarism()
	{
		code = "FC";
		longName = "Forward Chain";

		// initialize variables
		agenda  = new ArrayList<String>();
		clauses  = new ArrayList<String>();
		entailed  = new ArrayList<String>();
		facts  = new ArrayList<String>();
		count  = new ArrayList<Integer>();
		tell = "p=>q;q=>r;p;q";
		ask = "r";
		init(tell);
	}

	// method which calls the main fcentails() method and returns output back to iengine
public String execute(){
	String output = "";
	if (fcentails()){
			// the method returned true so it entails
			output = "YES: ";
			// for each entailed symbol
			for (int i=0;i<entailed.size();i++){
					output += entailed.get(i)+", ";
				}
			output += ask;	
	}
	else{
			output = "NO";
	}
	return output;		
}
 
// FC algorithm
public boolean fcentails(){
// loop through while there are unprocessed facts
while(!agenda.isEmpty()){
		// take the first item and process it
	 	String p = agenda.remove(0);
		// add to entailed
		entailed.add(p);
		// for each of the clauses....
		for (int i=0;i<clauses.size();i++){
			// .... that contain p in its premise
			if (premiseContains(clauses.get(i),p)){
			Integer j = count.get(i);
			// reduce count : unknown elements in each premise
			count.set(i,--j);
				// all the elements in the premise are now known
				if (count.get(i)==0){
					// the conclusion has been proven so put into agenda
					String head = clauses.get(i).split("=>")[1];
					// have we just proven the 'ask'?
					if (head.equals(ask))
						return true;
					agenda.add(head);					
				}
			}	
		}
	}
	// if we arrive here then ask cannot be entailed
	return false;
}
 
 
 
 
// method which sets up initial values for forward chaining
// takes in string representing KB and seperates symbols and clauses, calculates count etc..
public static void init(String tell){
   String[] sentences = tell.split(";");
	for (int i=0;i<sentences.length;i++){
 
		if (!sentences[i].contains("=>")) 
			// add facts to be processed
			agenda.add(sentences[i]);
		else{
			// add sentences
			clauses.add(sentences[i]);
			count.add(sentences[i].split("&").length);
			}
	}
}
 
 
// method which checks if p appears in the premise of a given clause	
// input : clause, p
// output : true if p is in the premise of clause
public static boolean premiseContains(String clause, String p){
	String premise = clause.split("=>")[0];
	String[] conjuncts = premise.split("&");
	// check if p is in the premise
	if (conjuncts.length==1)
		return premise.equals(p);
	else
		return Arrays.asList(conjuncts).contains(p);
}
	
	@Override
	public void Solve() {
		System.out.println("The forward chain just ran succesfully!");
	}

	
}