package solver;
import java.util.*;

public class BackwardChaining extends SearchMethod
{
	
	public BackwardChaining()
	{
		code = "BC";
		longName = "Backward Chaining";
	}

	protected PuzzleState popFrontier()
	{
		return null;
	}
	
	public boolean addToFrontier(PuzzleState aState)
	{
		return false;
	}
	
	public direction[] Solve(nPuzzle aPuzzle)
	{
		return null;
	}
}
