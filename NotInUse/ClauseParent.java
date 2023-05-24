package solver;

import java.util.*;

public abstract class ClauseParent {
	public abstract boolean getStateA();
	public abstract String getSymbolAString();
	public abstract ClauseParent getSymbolAClass();

	public abstract String getSigBit();

	public abstract boolean getStateB();
	public abstract String getSymbolBString();
	public abstract ClauseParent getSymbolBClass();

	public abstract void setStateA();
	public abstract void setStateB();

	public abstract String clauseString();
}