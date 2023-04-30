package solver;
import java.util.*;

public class ForwardChaining extends SearchMethod
{
	
	public ForwardChaining()
	{
		code = "FC";
		longName = "Forward Chaining";
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
