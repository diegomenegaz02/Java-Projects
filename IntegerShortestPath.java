package Project4;

public class IntegerShortestPath {
    public static void main(String[] args) {
        if (args.length > 2) {
            
            Graph graph = new Graph(args[0]);
            int start = Integer.parseInt(args[1]);
            int end = Integer.parseInt(args[2]);

            BreadthFirstSearch search = new BreadthFirstSearch(graph, start);
            int weight = search.getPathTo(end).size() - 1;
            System.out.print("Route: ");
            for (int i = 0; i < search.getPathTo(end).size(); i++) {
                if (search.getPathTo(end).get(i) < graph.getOrgNumVerts()) {
                    System.out.print(search.getPathTo(end).get(i));
                    if (i != search.getPathTo(end).size() - 1) {
                        System.out.print(" -> ");
                    }
                }
            }


            System.out.println("\nTotal cost: " + weight);

        } else {
            System.out.println("Wrong command line arguments given");
        }
    }
}
