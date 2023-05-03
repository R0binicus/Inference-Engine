package solver;
import java.io.*;
import java.util.*;
//import time;

/**
 * nPuzzler --- main class/initiate search methods/read problem file/etc.
 * @author    COS30019
 */
class InferenceEngine
{

	//the number of methods programmed into nPuzzler
	public static final int METHOD_COUNT = 3;
	//public static nPuzzle gPuzzle;
	public static CheckingMethod[] lMethods;


	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		//Create method objects
		InitMethods();
		
		//args contains:
		//  [0] - filename containing puzzle(s)
		//  [1] - method name
		
		if(args.length < 2) {
			System.out.println("Usage: nPuzzler <filename> <search-method>.");
			System.exit(1);			
		}
		
		//Get the puzzle from the file
		readProblemFile(args[0]);
		
		String method = args[1];
		CheckingMethod thisMethod = null;
		
		//determine which method the user wants to use to solve the puzzles
		for(int i = 0; i < METHOD_COUNT; i++)
		{
			//do they want this one?
			if(lMethods[i].code.compareTo(method) == 0)
			{
				//yes, use this method.
				thisMethod = lMethods[i];
			}
		}
		
		//Has the method been implemented?
		if(thisMethod == null)
		{
			//No, give an error
			System.out.println("Search method identified by " + method + " not implemented. Methods are case sensitive.");
			System.exit(1);
		}
		
		//Solve the puzzle, using the method that the user chose
		thisMethod.Solve();

		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		System.exit(0);
	}
	
	private static void InitMethods()
	{
		lMethods = new CheckingMethod[METHOD_COUNT];
		lMethods[0] = new TruthTable();
		lMethods[1] = new ForwardChain();
		lMethods[2] = new BackwardsChain();
	}
	
	private static void readProblemFile(String fileName) // this allow only one puzzle to be specified in a problem file 
	{
		
		try
		{
			//create file reading objects
			FileReader reader = new FileReader(fileName);
			BufferedReader puzzle = new BufferedReader(reader);
			nPuzzle result;
			
			String puzzleDimension = puzzle.readLine();
			//split the string by letter "x"
			String[] bothDimensions = puzzleDimension.split("x");
		
			//work out the "physical" size of the puzzle
			//here we only deal with NxN puzzles, so the puzzle size is taken to be the first number
			int puzzleSize = Integer.parseInt(bothDimensions[0]);
			int puzzleSizeV = Integer.parseInt(bothDimensions[1]);
			
			int[][] startPuzzleGrid = new int[puzzleSize][puzzleSizeV];
			int[][] goalPuzzleGrid = new int[puzzleSize][puzzleSizeV];
			
			//fill in the start state
			String startStateString = puzzle.readLine();
			startPuzzleGrid = ParseStateString(startStateString, startPuzzleGrid, puzzleSize);
			
			//fill in the end state
			String goalStateString = puzzle.readLine();
			goalPuzzleGrid = ParseStateString(goalStateString, goalPuzzleGrid, puzzleSize);
			
			//create the nPuzzle object...
			result = new nPuzzle(startPuzzleGrid, goalPuzzleGrid);
						
			puzzle.close();
			return result;
		}
		catch(FileNotFoundException ex)
		{
			//The file didn't exist, show an error
			System.out.println("Error: File \"" + fileName + "\" not found.");
			System.out.println("Please check the path to the file.");
			System.exit(1);
		}
		catch(IOException ex)
		{
			//There was an IO error, show and error message
			System.out.println("Error in reading \"" + fileName + "\". Try closing it and programs that may be accessing it.");
			System.out.println("If you're accessing this file over a network, try making a local copy.");
			System.exit(1);
		}
	}
	
	private static int[][] ParseStateString(String stateString, int[][] puzzleGrid, int pWidth)
	{
		//Parse state string converts the text file's format for each puzzle into
		// multidimensional arrays.
		
		//split the string by spaces
		String[] tileLocations = stateString.split(" ");
		
		// the top-left corner of the puzzle has a coordinate of [0,0]
		int x = 0;	
		int y = 0;
		
		for(int i = 0; i < tileLocations.length; i++)
		{
			//tileLocations[i] holds the (i + 1)th tile
			int tileNumber = Integer.parseInt(tileLocations[i]);
			
			//now, check the location of this tile
			if (x >= pWidth) {
				//reset x to 0 and go to next row (increase y by 1)
				x = 0;
				y++;
			}
			
			puzzleGrid[x][y] = tileNumber;
			x++;
		}
		
		return puzzleGrid;
	}
}