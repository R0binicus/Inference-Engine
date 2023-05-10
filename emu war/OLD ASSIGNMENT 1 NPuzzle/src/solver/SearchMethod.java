package solver;

import java.util.*;

public abstract class SearchMethod {
	public String code;				//the code used to identify the method at the command line
	public String longName;			//the actual name of the method.
	//public List<PuzzleState> nodeList;	//this is for catching repeated states and counting total nodes.
	public abstract direction[] Solve(nPuzzle aPuzzle);
	
	//The fringe needs to be a Queue and a Stack.
	//LinkedList implements both interfaces.
	//LinkedList also implements List, which allows it to be sorted easily.
	public LinkedList<PuzzleState> Frontier;
	
	//the searched list simply needs to be able to store nodes for the purpose of checking
	//Fast addition and removal is crucial here.
	//HashSet provides constant time for add, contains and size.
	public LinkedList<PuzzleState> Searched;
	
	abstract public boolean addToFrontier(PuzzleState aState);
	abstract protected PuzzleState popFrontier();
	
	public void reset()
	{
		this.Frontier.clear();
		this.Searched.clear();
	}
	public void SortByCostAsc()
	{
		Collections.sort(Frontier, new SortByCostAscComparator());
		
	}
	
	public void SortByCostDesc()
	{
		Collections.sort(Frontier, new SortByCostDescComparator());
	}
	
	public void SortByHeuristicAsc()
	{
		Collections.sort(Frontier, new SortByHeuristicAscComparator());
	}
	
	public void SortByHeuristicDesc()
	{
		Collections.sort(Frontier, new SortByHeuristicDescComparator());
	}
}

class SortByCostAscComparator implements java.util.Comparator<PuzzleState> {
    @Override
    public int compare(PuzzleState a, PuzzleState b) {
        return a.Cost - b.Cost;
    }
}

class SortByCostDescComparator implements java.util.Comparator<PuzzleState> {
    @Override
    public int compare(PuzzleState a, PuzzleState b) {
        return b.Cost - a.Cost;
    }
}

class SortByHeuristicAscComparator implements java.util.Comparator<PuzzleState> {
    @Override
    public int compare(PuzzleState a, PuzzleState b) {
        return a.HeuristicValue - b.HeuristicValue;
    }
}

class SortByHeuristicDescComparator implements java.util.Comparator<PuzzleState> {
    @Override
    public int compare(PuzzleState a, PuzzleState b) {
        return b.HeuristicValue - a.HeuristicValue;
    }
}