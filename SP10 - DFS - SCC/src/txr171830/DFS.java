/*
Created By
Esha Punjabi(EHP170000)
Tejas Ravi Rao(txr171830)
*/

package txr171830;

import txr171830.Graph.Edge;
import txr171830.Graph.Factory;
import txr171830.Graph.GraphAlgorithm;
import txr171830.Graph.Vertex;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
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
    int cno;

    public DFS(Graph g) {
        super(g, new DFSVertex(null));
        initialize();
    }

    public void initialize()
    {
        //initialize topnum = number of vertices in graph
        dfs_cyclic = false;
        cno=0;
        topnum = g.size();
        finishList = new LinkedList<>();

    }

    public void depthFirstSearch(Iterable<Vertex> iterable) {
        initialize();
        //initialize DFS
        for (Vertex u : iterable) {
            put(u, new DFSVertex(u));
        }

        //iterate through them, if not visited then visit it and make it's color black
        for (Vertex u : iterable) {
            if ((get(u)).color == 0) {
                cno++;
                DFS_Visit(u,cno);
            }

        }

    }

    public void DFS_Visit(Vertex u,int cno) {
        DFSVertex dv = this.get(u);
        //in process
        dv.color = 1;//color it to grey
        dv.cno=cno;
        //foreach vertices adjacent to u, visit them
        for (Edge e : this.g.outEdges(u)) {
            DFSVertex v = (this.get(e.otherEnd(u)));
            if (v.color == 0) {
                v.parent = dv;
                DFS_Visit(e.otherEnd(u),cno);
            } else if (v.color == 1) {
                //if the next node to be visited is still in process - in recurson stack, cycle is detected
                this.dfs_cyclic = true;

            }
        }
        //visited
        dv.color = 2;//black
        dv.top = topnum--;
        this.put(u, dv);
        this.finishList.addFirst(u);
    }

    //return dfs object with it's finishlist filled up with cno assigned to them
    public static DFS stronglyConnectedComponents(Graph g) {
        DFS d = new DFS(g);
        d.depthFirstSearch(g);
        List<Vertex> list=d.finishList;
        g.reverseGraph();
        d.depthFirstSearch(list);
        g.reverseGraph();
        return d;
    }

    // Member function to find topological order
    public List<Vertex> topologicalOrder1() {
        DFS d = new DFS(this.g);
        d.depthFirstSearch(this.g);
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
        String string = "5 5   1 2 1   2 3 3   3 1 5   2 4 4   4 5 1 0";
        Scanner in;
        // If there is a command line argument, use it as file from which
        // input is read, otherwise use input from string.
        in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);

        // Read graph from input
        Graph g = Graph.readGraph(in, true);
        g.printGraph(false);
        DFS d = DFS.stronglyConnectedComponents(g);
        System.out.println("Graph With total Strongly Connected components:"+ d.cno);
        if (d.finishList != null) {
            System.out.println("Name :   Cno");
            for (Vertex v : g)
                System.out.println(v.getName()+"    :    "+d.cno(v) + " ");
        }

    }
}