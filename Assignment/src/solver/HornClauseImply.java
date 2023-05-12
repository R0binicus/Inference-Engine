//https://github.com/benp0821/TruthTableGenerator/


package solver;

public class HornClauseImply extends ClauseParent {

	private boolean stateA;
	private ClauseParent symbolA;
	private String logic;
	private boolean stateB;
	private ClauseParent symbolB;
	
	public HornClauseImply(ClauseParent FirstSymbol, String LogicSymbol, ClauseParent SecondSymbol){
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
		return symbolA.getSymbolA();
	}

	/** Returns the significant bit */
	public String getSigBit(){
		return logic;
	}

	/** Returns the second binary state */
	public boolean getStateB(){
		return stateB;
	}

	/** Returns the first Symbol */
	public String getSymbolB(){
		return symbolB.getSymbolB();
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
