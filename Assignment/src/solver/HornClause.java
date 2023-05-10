//https://github.com/benp0821/TruthTableGenerator/


package solver;

public class HornClause {
	
	private boolean state;
	private char name;
	private int sigBit;


	private boolean stateA;
	private String symbolA;
	private String logic;
	private boolean stateB;
	private String symbolB;
	
	/**
	 * Stores variables with a name and a binary state (1 or 0)
	 * @param theName the name of the variable
	 * @param theState the current binary state of the variable
	 * @param significantBit helps with constructing the truth table in the TruthTableGUI class
	 */
	public HornClause(String FirstSymbol, String LogicSymbol, String SecondSymbol){
		stateA = true; //represents the ~ symbol, as it's not usally there it is true
		symbolA = FirstSymbol;
		logic = LogicSymbol;
		stateB = true; //represents the ~ symbol, as it's not usally there it is true
		symbolB = SecondSymbol;
	}
	
	/** Returns the first binary state */
	public boolean getStateA(){
		return stateA;
	}

	/** Returns the first Symbol */
	public String getSymbolA(){
		return symbolA;
	}

	/** Returns the significant bit */
	public int getSigBit(){
		return sigBit;
	}

	/** Returns the second binary state */
	public boolean getStateB(){
		return state;
	}

	/** Returns the first Symbol */
	public String getSymbolB(){
		return symbolB;
	}
	
	
	
	
	
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
		String returnString;

		if (stateA == false)
		{
			returnString = "~" + symbolA + " ";
		}
		else
		{
			returnString = symbolA + " ";
		}

		returnString += logic + " ";

		if (stateB == false)
		{
			returnString += "~" + symbolB + " ";
		}
		else
		{
			returnString += symbolB + " ";
		}

		return returnString;
	}
}
