/*
Created By
Esha Punjabi(EHP170000)
Tejas Ravi Rao(txr171830)
*/

package txr171830;
import txr171830.Graph.Vertex;
import txr171830.Graph.Edge;
import txr171830.Graph.GraphAlgorithm;
import txr171830.Graph.Factory;
import txr171830.Graph.Timer;
import java.io.File;
import java.util.*;
import java.util.LinkedList;
import java.util.Scanner;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {
    public static class DFSVertex implements Factory {
        int cno;
        int color;
        DFSVertex parent;
        int top;

        //white-Unvisited -0
        //grey-In process -1
        //Black-Visited -2


        //initially the vertex will be unvisite-white
        public DFSVertex(Vertex u) {
            color = 0;//white
            parent = null;
            top = 0;

        }

        public DFSVertex make(Vertex u) {
            return new DFSVertex(u);
        }
    }

    boolean dfs_cyclic;
    int topnum;
    LinkedList<Vertex> finishList;

    public DFS(Graph g) {
        //initialize topnum = number of vertices in graph
        super(g, new DFSVertex(null));
        dfs_cyclic = false;
        topnum = g.size();
        finishList = new LinkedList<>();
    }


    public static DFS depthFirstSearch(Graph g) {
        DFS d = new DFS(g);
        //initialize DFS
        for (Vertex u : g) {
            d.put(u, new DFSVertex(u));
        }
        //iterate through them, if not visited then visit it and make it's color black
        for (Vertex u : g) {
            if ((d.get(u)).color == 0) {
                d.DFS_Visit(u);
            }
        }
        return d;
    }

    public void DFS_Visit(Vertex u) {
        DFSVertex dv = this.get(u);
        //in process
        dv.color = 1;//color it to grey

        //foreach vertices adjacent to u, visit them
        for (Edge e : this.g.outEdges(u)) {
            DFSVertex v = (this.get(e.to));
            if (v.color == 0) {
                v.parent = dv;
                DFS_Visit(e.to);
            } else if (v.color == 1) {
                //if the next node to be visited is still in process - in recurson stack, cycle is detected
                this.dfs_cyclic = true;
                return;
            }
        }
        //visited
        dv.color = 2;//black
        dv.top = topnum--;
        this.put(u, dv);
        this.finishList.addFirst(u);

    }

    // Member function to find topological order
    public List<Vertex> topologicalOrder1() {
        DFS d = depthFirstSearch(this.g);
        //if graph is not DAG return null
        return d.dfs_cyclic || !g.isDirected() ? null : d.finishList;
    }

    // Find the number of connected components of the graph g by running dfs.
    // Enter the component number of each vertex u in u.cno.
    // Note that the graph g is available as a class field via GraphAlgorithm.
    public int connectedComponents() {
        return 0;
    }

    // After running the onnected components algorithm, the component no of each vertex can be queried.
    public int cno(Vertex u) {
        return get(u).cno;
    }

    // Find topological oder of a DAG using DFS. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder1(Graph g) {
        DFS d = new DFS(g);
        return d.topologicalOrder1();
    }

    // Find topological oder of a DAG using the second algorithm. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder2(Graph g) {
        return null;
    }

    public static void main(String[] args) throws Exception {
        String string = "7 8   1 2 2   1 3 3   2 4 5   3 4 4   4 5 1   5 1 7   6 7 1   7 6 1 0";
//        String string = "4 2  1 2 1  3 4 1 0";
        Scanner in;
        // If there is a command line argument, use it as file from which
        // input is read, otherwise use input from string.
        in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);

        // Read graph from input
        Graph g = Graph.readGraph(in, true);
        g.printGraph(false);
        DFS d = new DFS(g);
        List<Vertex> finishList = DFS.topologicalOrder1(g);
        if (finishList != null) {
            System.out.println("Topological order:");
            for (Vertex v : finishList)
                System.out.print(v.getName() + " ");
        } else {
            System.out.println("Graph is not DAG");
        }
            /*int numcc = d.connectedComponents();
        System.out.println("Number of components: " + numcc + "\nu\tcno");
        for(Vertex u: g) {
            System.out.println(u + "\t" + d.cno(u));
        }*/
    }
}