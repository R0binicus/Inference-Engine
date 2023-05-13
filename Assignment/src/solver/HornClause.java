//https://github.com/benp0821/TruthTableGenerator/


package solver;

public class HornClause extends ClauseParent {

	private boolean stateA;
	private ClauseParent symbolAClass = null; private String symbolAString = null;
	private String logic;
	private boolean stateB;
	private ClauseParent symbolBClass = null;  private String symbolBString = null;
	
	public HornClause(ClauseParent FirstSymbol, String LogicSymbol, ClauseParent SecondSymbol){ //Two classes as symbols
		stateA = true; //represents the ~ symbol, as it's not usally there it is true
		symbolAClass = FirstSymbol;
		logic = LogicSymbol;
		stateB = true; //represents the ~ symbol, as it's not usally there it is true
		symbolBClass = SecondSymbol;
	}

	public HornClause(String FirstSymbol, String LogicSymbol, String SecondSymbol){  //Two Strings as symbols
		stateA = true; 
		symbolAString = FirstSymbol;
		logic = LogicSymbol;
		stateB = true; 
		symbolBString = SecondSymbol;
	}

	public HornClause(ClauseParent FirstSymbol, String LogicSymbol, String SecondSymbol){  //One class and one string as symbols
		stateA = true; 
		symbolAClass = FirstSymbol;
		logic = LogicSymbol;
		stateB = true; 
		symbolBString = SecondSymbol;
	}

	public HornClause(String FirstSymbol, String LogicSymbol, ClauseParent SecondSymbol){  //One string and one class as symbols
		stateA = true; 
		symbolAString = FirstSymbol;
		logic = LogicSymbol;
		stateB = true; 
		symbolBClass = SecondSymbol;
	}
	
	/** Returns the first binary state */
	public boolean getStateA(){
		return stateA;
	}

	/** Returns the first Symbol */
	public ClauseParent getSymbolAClass(){ return symbolAClass; } 			//Get symbol A if it is a class
	public String getSymbolAString(){ return symbolAString; } 				//Get symbol A if it is a String

	/** Returns the significant bit */
	public String getSigBit(){
		return logic;
	}

	/** Returns the second binary state */
	public boolean getStateB(){
		return stateB;
	}

	/** Returns the first Symbol */
	public ClauseParent getSymbolBClass(){ return symbolBClass; } 			//Get symbol B if it is a class
	public String getSymbolBString(){ return symbolBString; }   			//Get symbol B if it is a String
	
	
	
	
	
	/**
	 * set symbol A to false
	 */
	public void setStateA(){
		stateA = false;
		
	}

	/**
	 * set symbol B to false
	 */
	public void setStateB(){
		stateB = false;
		
	}

	/**
	 * gets the whole clause and returns it as a string (like how it was inputted).     Used for debug purposes
	 */
	public String clauseString(){
		String returnString = "";

		if (stateA == false) // if first part has the not symbol ~
		{
			if (symbolAClass != null) // if the first symbol is a class
			{
				returnString += "~" + symbolAClass.clauseString() + " ";
			}
			else  // if the first symbol is a string
			{
				returnString += "~" + symbolAString + " ";
			}
		}
		else
		{
			if (symbolAClass != null)
			{
				returnString += symbolAClass.clauseString() + " ";
			}
			else 
			{
				returnString += symbolAString + " ";
			}
		}

		returnString += logic + " ";

		if (stateB == false) // if first part has the not symbol ~
		{
			if (symbolAClass != null) // if the first symbol is a class
			{
				returnString += "~" + symbolBClass.clauseString() + " ";
			}
			else // if the first symbol is a string
			{
				returnString += "~" + symbolBString + " ";
			}
		}
		else
		{
			if (symbolAClass != null)
			{
				returnString += symbolBClass.clauseString() + " ";
			}
			else 
			{
				returnString += symbolBString + " ";
			}
		}
		return returnString;
	}
}
