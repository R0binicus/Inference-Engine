package solver;

import java.util.*;

public abstract class ClauseParent {
	public abstract boolean getStateA();
	public abstract String getSymbolA();

	public abstract String getSigBit();

	public abstract boolean getStateB();
	public abstract String getSymbolB();

	public abstract void setStateA();
	public abstract void setStateB();

	public abstract String clauseString();
}