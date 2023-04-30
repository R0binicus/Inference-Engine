package solver;

import java.util.*;

/**
 * @author COS30019
 *
 */
public class PuzzleState implements Comparable<PuzzleState>
{
	public int[][] Puzzle;
	public PuzzleState Parent;
	public ArrayList<PuzzleState> Children;
	public int Cost;
	public int HeuristicValue;
	private int EvaluationFunction;
	public direction PathFromParent;
	
	public PuzzleState(PuzzleState aParent, direction aFromParent, int[][] aPuzzle)
	{
		Parent = aParent;
		PathFromParent = aFromParent;
		Puzzle = aPuzzle;
		Cost = Parent.Cost + 1;
		EvaluationFunction = 0;
		HeuristicValue = 0;
	}
	
	public PuzzleState(int[][] aPuzzle)
	{
		Parent = null;
		PathFromParent = null;
		Cost = 0;
		Puzzle = aPuzzle;
		EvaluationFunction = 0;
		HeuristicValue = 0;
	}
	
	public int getEvaluationFunction()
	{
		return EvaluationFunction;
	}
	
	public void setEvaluationFunction(int value)
	{
		EvaluationFunction = value;
	}
	
	public direction[] getPossibleActions(int[] blankLocation)
	{
		//find where the blank cell is and store the directions.
		direction[] result;
		result = new direction[countMovements(blankLocation)];
		int thisIndex = 0;
		if(blankLocation[0] == 0)
		{
			//the blank cell is already as far left as it will go, it can move right
			result[thisIndex++] = direction.Right;
		}
		else if(blankLocation[0] == (Puzzle.length - 1))
		{
			result[thisIndex++] = direction.Left;
		}
		else
		{
			result[thisIndex++] = direction.Left;
			result[thisIndex++] = direction.Right;
		}
		
		if(blankLocation[1] == 0)
		{
			//the blank cell is already as far up as it will go, it can move down
			result[thisIndex++] = direction.Down;
		}
		else if(blankLocation[1] == (Puzzle[0].length - 1))
		{
			result[thisIndex++] = direction.Up;
		}
		else
		{
			result[thisIndex++] = direction.Up;
			result[thisIndex++] = direction.Down;
		}
		return result;
	}
	
	private int countMovements(int[] blankLocation)
	{
		int result = 4;
		//try
		//{
			//blankLocation = find Blank Cell(); 
			//#Disabled this find Blank Cell() line because it should be already inputted via the parameters
		
			for(int i = 0; i <= 1; i++)
			{
				if(blankLocation[i] == 0)
				{
					result--;
					//do nothing
				}
			}
			if(blankLocation[0] == (Puzzle.length - 1))
			{
				result--;
				//do nothing
			}
			if(blankLocation[1] == (Puzzle[0].length - 1))
			{
				result--;
				//do nothing
			}
		//}
		//catch (InvalidPuzzleException e)
		//{
		//	//do something
		//	System.out.println("InvalidPuzzleException exeption!");
		//}
		return result;
	}
	
	private int[] findBlankCell() throws InvalidPuzzleException
	{
		for(int i = 0; i < Puzzle.length; i++)
		{
			for(int j = 0; j < Puzzle[i].length; j++)
			{
				if(Puzzle[i][j] == 0)
				{
					//System.out.println(i + " " + j);
					int[] result = {i, j};
					return result;
				}
			}
		}
		//No blank cell found?
		throw new InvalidPuzzleException(this);
	}
	
	private int[][] cloneArray(int[][] cloneMe)
	{
		int[][] result = new int[cloneMe.length][cloneMe[0].length];
		for(int i = 0; i < cloneMe.length; i++)
		{
			for(int j = 0; j < cloneMe[i].length; j++)
			{
				result[i][j] = cloneMe[i][j];
			}
		}
		return result;
	}
	
	public PuzzleState move(direction aDirection) throws CantMoveThatWayException
	{
		//Moving up moves the empty cell up (and the cell above it down)
		//first, create the new one (the one to return)
		PuzzleState result = new PuzzleState(this, aDirection, cloneArray(this.Puzzle));
		
		//now, execute the changes: move the blank cell aDirection
		//find the blankCell
		int[] blankCell = {0, 0};
		try
		{
			blankCell = findBlankCell();
		}
		catch(InvalidPuzzleException e)
		{
			System.out.println("There was an error in processing! Aborting...2");
			System.exit(1);
		}
		try
		{
			//move the blank cell in the new child puzzle
			
			if(aDirection == direction.Up)
			{
				result.Puzzle[blankCell[0]][blankCell[1]] = result.Puzzle[blankCell[0]][blankCell[1] - 1];
				result.Puzzle[blankCell[0]][blankCell[1] - 1] = 0;
			}
			else if(aDirection == direction.Down)
			{
				result.Puzzle[blankCell[0]][blankCell[1]] = result.Puzzle[blankCell[0]][blankCell[1] + 1];
				result.Puzzle[blankCell[0]][blankCell[1] + 1] = 0;
			}
			else if(aDirection == direction.Left)
			{
				result.Puzzle[blankCell[0]][blankCell[1]] = result.Puzzle[blankCell[0] - 1][blankCell[1]];
				result.Puzzle[blankCell[0] - 1][blankCell[1]] = 0;
			}
			else	//aDirection == Right;
			{
				result.Puzzle[blankCell[0]][blankCell[1]] = result.Puzzle[blankCell[0] + 1][blankCell[1]];
				result.Puzzle[blankCell[0] + 1][blankCell[1]] = 0;
			}
			return result;
		}
		catch(IndexOutOfBoundsException ex)
		{
			System.out.println("IndexOutOfBoundsException!");
			System.out.println(aDirection);
			System.out.println(blankCell[0] + " " + blankCell[1]);
			//System.out.println(Puzzle.length + " " + Puzzle[0].length + " " + Puzzle[1].length + " " + Puzzle[2].length);
			
			//System.out.println(result.Puzzle[blankCell[0]][blankCell[1] - 1]);
			//System.out.println(result.Puzzle[blankCell[0]][blankCell[1] + 1]);
			//System.out.println(result.Puzzle[blankCell[0] - 1][blankCell[1]]);
			//System.out.println(result.Puzzle[blankCell[0] + 1][blankCell[1]]);

			throw new CantMoveThatWayException(this, aDirection);
		}
	}
	
	@Override
	public boolean equals(Object aObject) throws ClassCastException
	{
		PuzzleState aState = (PuzzleState)aObject;
		//evaluate if these states are the same (does this.Puzzle == aState.Puzzle)?
		for(int i = 0; i < Puzzle.length; i++)
		{
			for(int j = 0; j < Puzzle[i].length; j++)
			{
				if(this.Puzzle[i][j] != aState.Puzzle[i][j])
					return false;		//stop checking as soon as we find an 
										// element that doesn't match
			}
		}
		return true;	//All elements matched? Return true;
	}

	//this is to allow the TreeSet to sort it.
	public int compareTo(PuzzleState aState)
	{
		return EvaluationFunction - aState.getEvaluationFunction();
	}
	
	public ArrayList<PuzzleState> explore()
	{
		int[] blankLocation = {0, 0};	//dummy value to avoid errors.
		
		try
		{
			blankLocation = findBlankCell();
		}
		catch(InvalidPuzzleException e)
		{
			System.out.println("There was an error in processing! Aborting...New");
			System.exit(1);
		}

		//populate children
		direction[] possibleMoves = getPossibleActions(blankLocation);
		//order possible moves by direction
		possibleMoves = orderByDirection(possibleMoves);
		Children = new ArrayList<PuzzleState>();
		for(int i = 0; i < possibleMoves.length; i++)
		{
			try
			{
				Children.add(move(possibleMoves[i]));
			}
			catch (CantMoveThatWayException e)
			{
				System.out.println("There was an error in processing! Aborting...3");
				//System.exit(1);
			}
		}	
		return Children;
	}

	public ArrayList<PuzzleState> exploreBugless()
	{
		int[] blankLocation = {0, 0};	//dummy value to avoid errors.
		
		try
		{
			blankLocation = findBlankCell();
		}
		catch(InvalidPuzzleException e)
		{
			System.out.println("There was an error in processing! Aborting...New");
			System.exit(1);
		}

		//populate children
		direction[] possibleMoves = getPossibleActions(blankLocation);
		//possibleMoves = orderByDirection(possibleMoves);
		Children = new ArrayList<PuzzleState>();
		for(int i = 0; i < possibleMoves.length; i++)
		{
			try
			{
				Children.add(move(possibleMoves[i]));
			}
			catch (CantMoveThatWayException e)
			{
				System.out.println("There was an error in processing! Aborting...3");
				//System.exit(1);
			}
		}	
		return Children;
	}
	
	public direction[] GetPathToState()
	{
		direction result[];
		
		if(Parent == null)	//If this is the root node, there is no path!
		{
			result = new direction[0];
			return result;
		} else				//Other wise, path to here is the path to parent
							// plus parent to here
		{
			direction[] pathToParent = Parent.GetPathToState();
			result = new direction[pathToParent.length + 1];
			for(int i = 0; i < pathToParent.length; i++)
			{
				result[i] = pathToParent[i];
			}
			result[result.length - 1] = this.PathFromParent;
			return result;
		}
	}

	//Applies ths to the set of directions:
	// When all else is equal, nodes should be expanded according to the following order: 
	// the agent should try to move the empty cell UP before attempting LEFT, before 
	// attempting DOWN, before attempting RIGHT, in that order.
	public direction[] orderByDirection(direction[] directionInput)
	{
		direction[] sortedDirections = new direction[directionInput.length];
		Boolean upState = false;
		Boolean leftState = false;
		Boolean downState = false;
		Boolean rightState = false;
		for(int i = 0; i < directionInput.length; i++)
		{
			//check if each direction an available direction
			
			if (directionInput[i] == direction.Up)
			{
				upState = true;
			} else if (directionInput[i] == direction.Left)
			{
				leftState = true;
			} else if (directionInput[i] == direction.Down)
			{
				downState = true;
			} else if (directionInput[i] == direction.Right)
			{
				rightState = true;
			} else
			{
				System.out.println("Something has gone wrong with the path");
			}

		}
		for(int i = 0; i < sortedDirections.length; i++)
		{

			//If direction is there, add it to the list, but this time in the correct order
			if (upState == true)
			{
				sortedDirections[i] = direction.Up;
				upState = false;
				continue;
			}
			if (leftState == true)
			{
				sortedDirections[i] = direction.Left;
				leftState = false;
				continue;
			}
			if (downState == true)
			{
				sortedDirections[i] = direction.Down;
				downState = false;
				continue;
			}
			if (rightState == true)
			{
				sortedDirections[i] = direction.Right;
				rightState = false;
				continue;
			}
		}
		return sortedDirections;
	}
}
