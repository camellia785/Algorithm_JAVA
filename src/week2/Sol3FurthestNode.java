package week2;

import java.util.*;

class Sol3FurthestNode {
    public int solution(int n, int[][] edge) {

        // (1) 각 노드의 인접 노드를 저장할 리스트 배열 : 2차원
        List<List<Integer>> graph = new ArrayList<>();

        // (2) 노드 개수만큼 리스트 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // (3) 양방향 간선 정보로 그래프 구성
        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        // (4) 방문 여부 및 최단 거리 저장 배열 (1번 인덱스부터 사용)
        int[] distance = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        // (5) BFS를 위한 큐 생성 및 시작 노드(1) 초기화
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        distance[1] = 0;

        // (6) BFS 탐색 시작
        while (!queue.isEmpty()) {
            int current = queue.poll(); // 현재 노드 꺼냄

            for (int next : graph.get(current)) { // 연결된 노드들 탐색
                if (!visited[next]) { // 방문 안 했으면
                    visited[next] = true; // 방문 처리
                    distance[next] = distance[current] + 1; // 거리 갱신
                    queue.offer(next); // 큐에 추가
                }
            }
        }

        // (7) 최댓값 찾기
        int maxDistance = 0;
        for (int d : distance) {
            if (d > maxDistance) {
                maxDistance = d;
            }
        }

        // (8) 가장 먼 거리와 같은 거리 가진 노드 수 세기
        int answer = 0;
        for (int d : distance) {
            if (d == maxDistance) {
                answer++;
            }
        }

        return answer; // (9) 결과 반환
    }
}
