package solver;
import java.util.*;

public class BFSStrategy extends SearchMethod {
	
	public BFSStrategy()
	{
		code = "BFS";
		longName = "Breadth-First Search";
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
			//else put this item at the beginning of the queue;
			// was originally addLast however BFS is FIFO so it should be addFirst
			Frontier.addFirst(aState);
			return true;
		}
	}
	
	@Override
	public direction[] Solve(nPuzzle puzzle) {
		//This method uses the fringe as a queue.
		//Therefore, nodes are searched in order of cost, with the lowest cost
		// unexplored node searched next.
		//-----------------------------------------
		
		//put the start state in the Fringe to get explored.
		addToFrontier(puzzle.StartState);
		
		ArrayList<PuzzleState> newStates = new ArrayList<PuzzleState>();
				
		while(Frontier.size() > 0)
		{
			//get the next item off the fringe
			PuzzleState thisState = popFrontier();
			
			//is it the goal item?
			if(thisState.equals(puzzle.GoalState))
			{
				//We have found a solution! return it!
				return thisState.GetPathToState();
			}
			else
			{
				//This isn't the goal, just explore the node
				newStates = thisState.exploreBugless();
				
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
}

