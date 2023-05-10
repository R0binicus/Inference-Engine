package solver;
import java.util.*;

public class TruthTable extends SearchMethod
{
	
	public TruthTable()
	{
		code = "TV";
		longName = "Truth Table";
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
