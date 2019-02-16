/**SP 12 - DIAMETER OF A GRAPH
 * GROUP 16 - TEJAS RAVI RAO
 *            YASH MADANE
 *
 * NOV 25th 2018
 *
 * */




package txr171830;
import java.io.File;
import java.util.Scanner;

import rbk.BFSOO;
import rbk.Graph;
import rbk.Graph.Vertex;

public class SP12 {

    /** return the diameter of the graph
     * */
    public static int diameter(Graph g) {

        //set which vertex to select as source
        int n = 1;
        return diameter_helper(g, n);

    }

    /**
     * helper method to find diameter of the graph
     */

    public static int diameter_helper(Graph g, int n) {

        BFSOO bfsgraph = new BFSOO(g);

        // get source vertex
        Vertex source = g.getVertex(n);

        //perform BFS from selected source
        bfsgraph.bfs(source);

        //find node that is farthest from the source
        Vertex maxnode = findfarthestNode(bfsgraph,g);

        //perform BFS with the above node as second source
        bfsgraph.bfs(maxnode);

        //find node that is farthest from the second source
        Vertex maxnode2 = findfarthestNode(bfsgraph,g);

        //distance of maxnode2 is the diameter
        int diameter = bfsgraph.getDistance(maxnode2);

        return diameter;
    }

    /**locate farthest node from source
     * */
    public static Vertex findfarthestNode(BFSOO bfsgraph, Graph g){

        Vertex maxnode = null;
        int maxDistance = 0;
        int nodeDistance;

        // find the farthest node from source
        for (Vertex u: g){

            nodeDistance = bfsgraph.getDistance(u);

            if(nodeDistance>maxDistance){
                maxDistance=nodeDistance;

                maxnode = u;

            }

        }

        return maxnode;

    }


    public static void main(String[] args) throws Exception {
        String string = "9 8    1 2 2   2 4 3   2 3 5   3 7 4  7 9 3   1 5 1   5 6 1   6 8 2 0";
        Scanner in;

        in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
        // Read graph from input
        Graph g = Graph.readGraph(in);
        int s = in.nextInt();
        g.printGraph(false);

        int diam = diameter(g);
        System.out.println("Diameter of graph: " + diam);

    }

}