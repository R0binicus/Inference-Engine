package solver;

import java.util.*;

public class TruthTable extends CheckingMethod {
	// this is here for ease of testing and will be removed later.

	HashMap<String, List<Integer>> _ttkb;

	public void TT(List<String> horn, List<String> symbols) {

		ArrayList<String> unique = getTTCount(symbols);
		HashMap<String, List<Integer>> TT = generateTT(unique);
		// System.out.println(TT);
		_ttkb = TT;
		// return _ttkb;
	}

	private ArrayList<String> getTTCount(List<String> symbols) {
		ArrayList<String> unique = new ArrayList<String>();
		for (String s : symbols) {
			if (!unique.contains(s)) {
				unique.add(s);
			}
		}
		/*
		 * for (String me : unique) {
		 * System.out.println("unique: " + me);
		 * }
		 */
		System.out.println(unique);

		return unique;
	}

	private HashMap<String, List<Integer>> generateTT(List<String> unique) {
		HashMap<String, List<Integer>> TT = new HashMap<String, List<Integer>>();
		// +1 for the query
		int colnum = unique.size();
		int power = (int) Math.pow(2, colnum);
		int rowcount = power / colnum;
		// test if correct n is used
		// System.out.println("Size of each " + power);

		for (int i = 0; i < colnum; i++) {
			// add data into HashMap
			TT.put(unique.get(i), new ArrayList<Integer>());
		}
		// fill facts
		for (String s : TT.keySet()) {
			// all parts that do not contain an and statement. The and statement is reliant
			// on these being fileld first
			List<Integer> list = TT.get(s);
			if (!s.contains("&")) {
				// alter this to be every combination
				for (int i = 0; i < rowcount; i++) {
					String num = Integer.toBinaryString(i);

					// fill in leading numbers with 0 until ints length is equal to the column
					// number (n)
					while (num.length() < colnum) {
						num = "0" + num;
					}
					for (int l = 0; l < colnum; l++) {
						int value = Character.getNumericValue(num.charAt(l));

						list.add(value);
					}
				}
				// I was having issues where the last 2 values were not working correctly.
				// Hopefuly this addresses the issue and does not mess with the expected outcome
				int remaining = power - list.size();
				for (int i = 0; i < remaining; i++) {
					String binaryString = Integer.toBinaryString(i);
					while (binaryString.length() < colnum) {
						binaryString = "0" + binaryString;
					}
					int value = Character.getNumericValue(binaryString.charAt(i));
					list.add(value);
				}
			}
		}
		// test col size is correct and & is not added to
		/*
		 * for (String s : TT.keySet()) {
		 * System.out.println(s + " " + TT.get(s).size());
		 * }
		 */
		// start to fill in & logic
		for (String s : TT.keySet()) {
			if (s.contains("&")) {
				String[] and = s.split("&");
				String left = and[0].trim();
				String right = and[1].trim();
				// test table selection
				// System.out.println("left: " + left + " Right: " + right);
				List<Integer> self = TT.get(s);
				List<Integer> leftcol = TT.get(left);
				List<Integer> rightcol = TT.get(right);
				// check if there is a null list. Will assume that in the case of a null list
				// the value is false
				if (leftcol == null || rightcol == null) {
					for (int i = 0; i < power; i++) {
						self.add(0);
					}
				} else {
					for (int i = 0; i < power; i++) {
						int leftval = leftcol.get(i);
						int rightval = rightcol.get(i);
						if (leftval == 1 && rightval == 1) {
							self.add(1);
						} else {
							self.add(0);
						}
					}
				}
			}
		}
		// before table is returned test table count
		/*
		 * for (String s : TT.keySet()) {
		 * System.out.println(s + " " + TT.get(s).size());
		 * }
		 */
		return TT;
	}

	public TruthTable()// (String KBString, String Query) please don't add any parameters here, they
						// are for the Solve() function
	{ // I am not sure what these 2 things are doing. Can you explain when you see
		// this. thanks.

		// I added some explaination
		// the CheckingMethod.java has an explaination commented there too

		code = "TT";
		// the code is the input name to be used when using the command prompt
		longName = "Truth Table";
		// the longName is just the full name, to be used if you want to print the name
		// of the check method being run
	}

	@Override
	public void Solve(List<String> KB, String Input) {
		System.out.println("The truth table just ran succesfully!");
	}
}
