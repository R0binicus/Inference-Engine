package solver;

class main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: Inference Engine <search-method> <filename>.");
            System.exit(1);
        }
        String textFile = args[1];
        reader Data = new reader();

        // For testing the Forward Chaining Method
        ForwardChain FC = new ForwardChain();
        // FC.Solve(Data._horny, Data._query);

        // For testing the Backward Chaining Method
        BackwardsChain BC = new BackwardsChain();
        // BC.Solve(Data._horny, Data._query);

        // for testing the truth table
        TruthTable TT = new TruthTable();

        Data.read(textFile);
        System.out.println("Input String:   " + Data._horny);
        System.out.println("Input Query:    " + Data._query);
        System.out.println("Symbols used:   " + TT.getTTCount(Data._symbols));

        // best practice to avoid case sensitivity
        String method = args[0].toLowerCase();

        // determine which method the user wants to use to solve the puzzles

        if (FC.code.compareTo(method) == 0) {
            FC.Solve(Data._horny, Data._query);
        } else if (BC.code.compareTo(method) == 0) {
            BC.Solve(Data._horny, Data._query);
        } else if (method.equals("tt")) {

            TT.TT(Data._horny, Data._symbols);
            // Truth table incomplete so this is the sum of each column
            TT.printColNum();

        }
        System.exit(0);
    }
}