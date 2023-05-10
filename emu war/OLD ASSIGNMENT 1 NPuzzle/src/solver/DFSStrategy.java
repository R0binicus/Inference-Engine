package solver;
import java.util.*;

public class DFSStrategy extends SearchMethod 
{
	
	public DFSStrategy()
	{
		code = "DFS";
		longName = "Depth-First Search";
		Frontier = new LinkedList<PuzzleState>();
		Searched = new LinkedList<PuzzleState>();
	}
	
	protected PuzzleState popFrontier()
	{
		//remove an item from the fringe to be searched
		PuzzleState thisState = Frontier.pop();

		//add it to the list of searched states, so that it isn't searched again
		Searched.add(thisState);
		
		return thisState;
	}

	public boolean addToFrontier(PuzzleState aState)
	{
		//We only want to add the new state to the fringe if it doesn't exist
		// in the fringe or the searched list.
		if(Searched.contains(aState) || Frontier.contains(aState))
		{
			//discard it
			return false;
		}
		else
		{
			//else put this item at the end of the queue;
			Frontier.addLast(aState);
			return true;
		}
	}
	
	@Override
	public direction[] Solve(nPuzzle aPuzzle) {
		//This method uses the fringe as a queue.
		//Therefore, nodes are searched in order of cost, with the lowest cost
		// unexplored node searched next.
		//-----------------------------------------
		
		//put the start state in the Fringe to get explored.
		addToFrontier(aPuzzle.StartState);
		
		ArrayList<PuzzleState> newStates = new ArrayList<PuzzleState>();
				
		while(Frontier.size() > 0)
		{
			//get the next item off the fringe
			PuzzleState thisState = popFrontier();
			
			//is it the goal item?
			if(thisState.equals(aPuzzle.GoalState))
			{
				//We have found a solution! return it!
				return thisState.GetPathToState();
			}
			else
			{
				//This isn't the goal, just explore the node
				newStates = thisState.explore();
				
				for(int i = 0; i < newStates.size(); i++)
				{
					//add this state to the fringe, addToFringe() will take care of duplicates
					addToFrontier(newStates.get(i));
				}
			}
		}
		
		//No solution found and we've run out of nodes to search
		//return null.
		return null;
	}

	public ArrayList<PuzzleState> orderByDirection(ArrayList<PuzzleState> aState)
	{
		ArrayList<PuzzleState> sortedAState = new ArrayList<PuzzleState>();
		PuzzleState upState = null;
		PuzzleState leftState = null;
		PuzzleState downState = null;
		PuzzleState rightState = null;
		for(int i = 0; i < aState.size(); i++)
		{
			//System.out.println(aState.get(i).PathFromParent);
			
			if (aState.get(i).PathFromParent == direction.Up)
			{
			upState = aState.get(i);
			} else if (aState.get(i).PathFromParent == direction.Left)
			{
				leftState = aState.get(i);
			} else if (aState.get(i).PathFromParent == direction.Down)
			{
				downState = aState.get(i);
			} else if (aState.get(i).PathFromParent == direction.Right)
			{
				rightState = aState.get(i);
			} else
			{
				System.out.println("Something has gone wrong with the path");
			}

		}
		if (leftState != null)
		{
			sortedAState.add(leftState);
		}
		if (upState != null)
		{
			sortedAState.add(upState);
		}
		
		if (downState != null)
		{
			sortedAState.add(downState);
		}
		if (rightState != null)
		{
			sortedAState.add(rightState);
		}
		//System.out.println(" ");
		return sortedAState;
	}
}
