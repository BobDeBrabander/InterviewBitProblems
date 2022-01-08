import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MaxEdgeQueries {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] aLine = reader.readLine().split(" ");
        String[] bLine = reader.readLine().split(" ");

        int a = Integer.parseInt(aLine[0]);
        int b = Integer.parseInt(bLine[0]);
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        ArrayList<ArrayList<Integer>> B = new ArrayList<>();

        int pointer = 1;
        for (int i = 0; i < a; i++) {
            A.add(new ArrayList<>());
            int w = Integer.parseInt(aLine[pointer]);
            int s = Integer.parseInt(aLine[pointer + 1]);
            int t = Integer.parseInt(aLine[pointer + 2]);
            A.get(i).addAll(Stream.of(s, t, w).collect(Collectors.toList()));
            pointer += 3;
        }

        pointer = 1;
        for (int i = 0; i < b; i++) {
            B.add(new ArrayList<>());
            int s = Integer.parseInt(bLine[pointer]);
            int t = Integer.parseInt(bLine[pointer + 1]);
            B.get(i).addAll(Stream.of(s, t).collect(Collectors.toList()));
            pointer += 2;
        }

        ArrayList<Integer> answer = new MaxEdgeQueries().solve(A, B);
        System.out.println(answer);
        int val = (int) Math.ceil(Math.log(A.size()));
    }

    class GraphNode {
        int number;
        GraphNode[] pow2StepsUp;
        int[] pow2StepsUpMaxEdge;
        int depth;
        int maxEdgeToRoot;
        ArrayList<Edge> edges = new ArrayList<>();

        GraphNode(int number, int logNum) {
            this.number = number;
            pow2StepsUp = new GraphNode[logNum + 1];
            pow2StepsUpMaxEdge = new int[logNum + 1];
        }
    }

    class Edge {
        GraphNode target;
        int weight;

        public Edge(GraphNode target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public ArrayList<Integer> solve(ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> B) {
        //Since there is N  nodes and N-1 Edges and the graph is a Tree, we know:
        //There is no Cycles
        //There is only one path from each node to each other node
        int logNum = (int) Math.ceil(Math.log(A.size() + 1));

        /** Construct graph **/
        GraphNode[] nodes = new GraphNode[A.size() + 1];
        for (int i = 0; i <= A.size(); i++) nodes[i] = new GraphNode(i, logNum);
        for (int i = 0; i < A.size(); i++) {
            int weight = A.get(i).get(2);
            int source = A.get(i).get(0) - 1;
            int target = A.get(i).get(1) - 1;
            nodes[source].edges.add(new Edge(nodes[target], weight));
            nodes[target].edges.add(new Edge(nodes[source], weight));
        }
        setFromRoot(null, nodes[0], 0, 0); //Set for every node what is the direction of the root node
        //Assume node 0 is root

        //For every node pow2Root[i] = the node that is 2^i steps in the direction of the root node, or null if that does not exist.
        // and pow2DistRoot[i] = the maxEdge in the 2^i steps in the direction of the root
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 1; j <= logNum; j++) {
                if (nodes[i].pow2StepsUp[j - 1] != null) {
                    nodes[i].pow2StepsUp[j] = nodes[i].pow2StepsUp[j - 1].pow2StepsUp[j - 1];
                    nodes[i].pow2StepsUpMaxEdge[j] = Math.max(nodes[i].pow2StepsUpMaxEdge[j - 1], nodes[i].pow2StepsUp[j - 1].pow2StepsUpMaxEdge[j - 1]);
                } else {
                    //We don't actually have 2^i steps up, so just set it equal to the root node
                    nodes[i].pow2StepsUp[j] = nodes[0];
                    nodes[i].pow2StepsUpMaxEdge[j] = nodes[i].maxEdgeToRoot;
                }
            }
        }

        /** Execute queries **/
        ArrayList<Integer> answers = new ArrayList<>();
        for (int i = 0; i < B.size(); i++) {
            int source = B.get(i).get(0) - 1;
            int target = B.get(i).get(1) - 1;
            answers.add(solveQuery(nodes[source], nodes[target], logNum));
        }
        return answers;
    }

    private int solveQuery(GraphNode from, GraphNode to, int logNum) {
        if (from == to) {
            return 0;
        }

        GraphNode lessDeepNode;
        GraphNode deepestNode;
        if (from.depth >= to.depth) {
            deepestNode = from;
            lessDeepNode = to;
        } else {
            deepestNode = to;
            lessDeepNode = from;
        }
        int diff = deepestNode.depth - lessDeepNode.depth;
        int maxSoFar = 0;
        while (diff != 0) {
            int biggestJumpPow2 = 1;
            int pow2 = 0;
            while (biggestJumpPow2 * 2 <= diff) {
                biggestJumpPow2 *= 2;
                pow2++;
            }
            maxSoFar = Math.max(maxSoFar, deepestNode.pow2StepsUpMaxEdge[pow2]);
            diff -= biggestJumpPow2;
            deepestNode = deepestNode.pow2StepsUp[pow2];
        }
        if (from.number == 63 && to.number == 206) {
            System.out.println(deepestNode.number + " " + lessDeepNode.number);
        }
        if (deepestNode == lessDeepNode) return maxSoFar;
        //Now if they are not the same node they are on different subtrees.
        //They both need to ascend in the tree until they are the same node.
        //Go up with the biggest power of 2 that does not cause them to be the same
        //Do this for all powers of 2
        for (int k = logNum; k >= 0; k--) {
            if (deepestNode.pow2StepsUp[k] != null && lessDeepNode.pow2StepsUp[k] != null && deepestNode.pow2StepsUp[k] != lessDeepNode.pow2StepsUp[k]) {
                maxSoFar = Math.max(maxSoFar, deepestNode.pow2StepsUpMaxEdge[k]);
                maxSoFar = Math.max(maxSoFar, lessDeepNode.pow2StepsUpMaxEdge[k]);
                deepestNode = deepestNode.pow2StepsUp[k];
                lessDeepNode = lessDeepNode.pow2StepsUp[k];
            }
        }
        //Now they should be 1 step removed from the common ancestor so include that lest step
        maxSoFar = Math.max(maxSoFar, deepestNode.pow2StepsUpMaxEdge[0]);
        maxSoFar = Math.max(maxSoFar, lessDeepNode.pow2StepsUpMaxEdge[0]);
        return maxSoFar;
    }

    private void setFromRoot(GraphNode from, GraphNode curNode, int depth, int maxDistSoFar) {
        curNode.depth = depth;
        curNode.maxEdgeToRoot = maxDistSoFar;
        for (int i = 0; i < curNode.edges.size(); i++) {
            Edge e = curNode.edges.get(i);
            if (e.target == from) continue;
            e.target.pow2StepsUp[0] = curNode;
            e.target.pow2StepsUpMaxEdge[0] = e.weight;
            setFromRoot(curNode, e.target, depth + 1, Math.max(e.weight, maxDistSoFar));
        }
    }
}