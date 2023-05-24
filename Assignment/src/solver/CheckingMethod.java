package solver;

import java.util.*;

public abstract class CheckingMethod {
	public String code;				//the code used to identify the method at the command line
	public String longName;			//the actual name of the method.
	public abstract void Solve(List<String> KB, String Input);
}