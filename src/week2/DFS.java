package week2;

import java.util.*;

public class DFS {
    public void dfs(List<List<Integer>> graph, Set<Integer> visited, int curVertex) {
        visited.add(curVertex);
        for (int nextVertex : graph.get(curVertex)) {
            if (!visited.contains(nextVertex)) {
                dfs(graph, visited, nextVertex);
            }
        }
    }

    public void solve(List<List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        dfs(graph, visited, 0);
    }
}
