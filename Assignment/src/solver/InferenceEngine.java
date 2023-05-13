package solver;

import java.io.*;
import java.util.*;
//import time;

/**
 * nPuzzler --- main class/initiate search methods/read problem file/etc.
 * 
 * @author COS30019
 */
class InferenceEngine {

	// the number of methods programmed into nPuzzler
	public static final int METHOD_COUNT = 2;
	// public static nPuzzle gPuzzle;
	public static CheckingMethod[] lMethods;

	static ArrayList<HornClause> ClauseList = new ArrayList<>();

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		// Create method objects
		InitMethods();

		// args contains:
		// [0] - filename containing puzzle(s)
		// [1] - method name

		if (args.length < 2) {
			System.out.println("Usage: Inference Engine <filename> <search-method>.");
			System.exit(1);
		}

		// Get the puzzle from the file
		System.out.println(readProblemFile(args[0]));
		// readProblemFile(args[0]);

		String method = args[1];
		CheckingMethod thisMethod = null;

		// determine which method the user wants to use to solve the puzzles
		for (int i = 0; i < METHOD_COUNT; i++) {
			// do they want this one?
			if (lMethods[i].code.compareTo(method) == 0) {
				// yes, use this method.
				thisMethod = lMethods[i];
			}
		}

		// Has the method been implemented?
		if (thisMethod == null) {
			// No, give an error
			System.out
					.println("Search method identified by " + method + " not implemented. Methods are case sensitive.");
			System.exit(1);
		}

		// Solve the puzzle, using the method that the user chose
		thisMethod.Solve();

		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");
		System.exit(0);
	}

	private static void InitMethods() {
		lMethods = new CheckingMethod[METHOD_COUNT];
		lMethods[0] = new ForwardChain();
		lMethods[1] = new BackwardsChain();
		// lMethods[2] = new TruthTable();

	}

	private static String readProblemFile(String fileName) // this allow only one puzzle to be specified in a problem
															// file
	{
		String result = "";
		try {
			// create file reading objects
			FileReader reader = new FileReader(fileName);
			BufferedReader puzzle = new BufferedReader(reader);

			String TELL = puzzle.readLine();
			String KBinput = puzzle.readLine();
			String ASK = puzzle.readLine();
			String checkInput = puzzle.readLine();

			//result = KBinput + "         " + checkInput;
			List<String> printTest = ParseTell(KBinput);

			for (String arg : printTest) { // remove white spaces
											// System.out.println(arg + " ");
			}

			puzzle.close();

		} catch (FileNotFoundException ex) {
			// The file didn't exist, show an error
			System.out.println("Error: File \"" + fileName + "\" not found.");
			System.out.println("Please check the path to the file.");
			System.exit(1);
		} catch (IOException ex) {
			// There was an IO error, show and error message
			System.out.println(
					"Error in reading \"" + fileName + "\". Try closing it and programs that may be accessing it.");
			System.out.println("If you're accessing this file over a network, try making a local copy.");
			System.exit(1);
		}
		return result;
	}

	private static List<String> ParseTell(String TELLString) {
		List<String> TELLList = new ArrayList<>();
		String[] sections = TELLString.split(";");
		for (String arg : sections) { // remove white spaces
			TELLList.add(arg.trim());
		}

		return TELLList;
	}
}