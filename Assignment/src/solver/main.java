package solver;

class main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: Inference Engine <search-method> <filename>.");
            System.exit(1);
        }
        String textFile = args[1];
        reader Data = new reader();

        Data.read(textFile);
        System.out.println("Input String:   " + Data._horny);
        System.out.println("Input Query:    " + Data._query);
        System.out.println("Symbols used:   " + Data._symbols);

        // For testing the Forward Chaining Method
        ForwardChain FC = new ForwardChain();
        // FC.Solve(Data._horny, Data._query);

        // For testing the Backward Chaining Method
        BackwardsChain BC = new BackwardsChain();
        // BC.Solve(Data._horny, Data._query);

        // For testing the Backward Chaining Method
        SolveTT TT = new SolveTT();
        // BC.Solve(Data._horny, Data._query);

        // best practice to avoid case sensitivity
        String method = args[0].toLowerCase();

        // determine which method the user wants to use to solve the puzzles

        if (FC.code.compareTo(method) == 0) {
            FC.Solve(Data._horny, Data._query);
        } else if (BC.code.compareTo(method) == 0) {
            BC.Solve(Data._horny, Data._query);
        } else if (FC.code.compareTo(method) == 0) {

        }
        System.exit(0);
    }
}