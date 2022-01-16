import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class WordLadder2Java {
    static class DistNode {
        String value;
        int dist = Integer.MAX_VALUE;
        ArrayList<DistNode> adj = new ArrayList<>();

        DistNode(String v) {
            value = v;
        }
    }

    public static ArrayList<ArrayList<String>> findLadders(String start, String end, ArrayList<String> dict) {
        if (start.equals(end)) {
            ArrayList<ArrayList<String>> solutions = new ArrayList<>();
            ArrayList<String> onlySolution = new ArrayList<>();
            onlySolution.add(start);
            solutions.add(onlySolution);
            return solutions;
        }
        DistNode startNode = new DistNode(start);
        startNode.dist = 0;
        DistNode endNode = new DistNode(end);
        ArrayList<DistNode> nodes = new ArrayList<>();
        if (oneDistAway(startNode.value, endNode.value)) connectNodes(startNode, endNode);
        for (String s : dict) {
            nodes.add(new DistNode(s));
        }
        for (int i = 0; i < dict.size(); i++) {
            DistNode n1 = nodes.get(i);
            if (oneDistAway(startNode.value, n1.value)) connectNodes(startNode, n1);
            if (oneDistAway(endNode.value, n1.value)) connectNodes(endNode, n1);
            for (int j = i + 1; j < dict.size(); j++) {
                DistNode n2 = nodes.get(j);
                if (oneDistAway(n1.value, n2.value)) connectNodes(n1, n2);
            }
        }
        bfs(startNode);
        ArrayList<ArrayList<String>> solutionHolder = new ArrayList<>();
        constructSolution(endNode, new ArrayList<>(), solutionHolder);
        return new ArrayList<>(new HashSet<>(solutionHolder));
    }

    private static void constructSolution(DistNode endNode, ArrayList<String> curSolution, ArrayList<ArrayList<String>> solutionSet) {
        curSolution.add(endNode.value);
        if (endNode.dist == 0) {
            ArrayList<String> finalSolution = (ArrayList<String>) curSolution.clone();
            Collections.reverse(finalSolution);
            solutionSet.add(finalSolution);
        } else {
            for (int i = 0; i < endNode.adj.size(); i++) {
                DistNode curAdj = endNode.adj.get(i);
                if (curAdj.dist + 1 == endNode.dist) {
                    constructSolution(curAdj, curSolution, solutionSet);
                }
            }
        }
        curSolution.remove(curSolution.size() - 1);
    }

    private static void bfs(DistNode startNode) {
        ArrayList<DistNode> q = new ArrayList<>();
        q.add(startNode);
        while (!q.isEmpty()) {
            DistNode curNode = q.remove(0);
            for (int i = 0; i < curNode.adj.size(); i++) {
                DistNode adjCur = curNode.adj.get(i);
                if (adjCur.dist > curNode.dist + 1) {
                    adjCur.dist = curNode.dist + 1;
                    q.add(adjCur);
                }
            }
        }
    }

    private static boolean oneDistAway(String s1, String s2) {
        //All words have the same length, so the only one diff can be a substitution
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                if (diff > 1) return false;
            }
        }
        return true;
    }

    private static void connectNodes(DistNode n1, DistNode n2) {
        n1.adj.add(n2);
        n2.adj.add(n1);
    }
}