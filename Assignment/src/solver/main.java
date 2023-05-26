package solver;

class main {

    public static void main(String[] args) {
        if (args.length < 2) {
            // If the input args are missing print the args format
            System.out.println("Usage: Inference Engine <search-method> <filename>.");
            System.exit(1);
        }

        // Declare the checking method classes to be used
        ForwardChain FC = new ForwardChain();
        BackwardsChain BC = new BackwardsChain();
        TruthTable TT = new TruthTable();

        // Start Reader
        String textFile = args[1];
        reader Data = new reader();
        Data.read(textFile);

        // Output some Reader contents
        System.out.println("Input String:   " + Data._horny);
        System.out.println("Input Query:    " + Data._query);
        System.out.println("Symbols used:   " + TT.getTTCount(Data._symbols));

        // Set text to lower case to remove case sensitivity
        String method = args[0].toLowerCase();

        // Determine which method the user wants to use to solve the puzzles
        if (FC.code.compareTo(method) == 0) 
        { //Forward Chaining
            FC.Solve(Data._horny, Data._query);
        } else if (BC.code.compareTo(method) == 0) 
        { //Backward Chaining
            BC.Solve(Data._horny, Data._query);
        } else if (method.equals("tt")) 
        { //Truth Table

            TT.TT(Data._horny, Data._symbols);
            // Truth table incomplete so this is the sum of each column
            TT.printColNum();
        }
        System.exit(0);
    }
}