package week3;

import java.util.LinkedList;
import java.util.Queue;

class Sol1ShortestPathBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // 시작점, 끝점 길이 막힘
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1){
            return -1;
        }

        // 방문여부를 저장한 2차원 배열 -> 방문한 곳 다시 안가려고
        boolean[][] visited = new boolean[n][n];

        // 8방향 이동 정의 (행변환, 열변환)
        int[] dr ={-1, 1, 0, 0, -1, -1, +1, 1};
        int[] dc ={ 0, 0, 1, -1, -1, 1, -1, 1};

        // BFS에 사용할 큐, int[]는 (현재행, 현재열, 총이동거리) 저장
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0,1}); // 시작점과 거리1 넣기
        visited[0][0] = true;  // 시작점 방문 체크

        while (!queue.isEmpty()) { // queue가 안 비었다면
            int[] current = queue.poll(); // queue에서 가장 앞에 있는 요소 하나꺼내기

            int curR = current[0]; //행정보
            int curC = current[1]; //열정보
            int curDist = current[2]; //총 이동거리 정보

            // 현재 행, 열 정보가 목적지 도착하면, curDist 리턴
            if (curR == n-1 && curC == n-1){
                return curDist;
            }
            // 8방향 탐색위한 반복문
            for (int i = 0; i<8; i++){
                // 다음위치 nextR, nextC
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                // next위치가 grid 유효범위 안에 있는지 확인
                if (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n){
                    // grid가 0이여야 이동가능하고, visit하지 않았어야 함
                    if (grid[nextR][nextC] == 0 && !visited[nextR][nextC]) {
                        // 이동 가능한 위치를 큐에 추가 (거리+1)
                        queue.offer(new int[]{nextR, nextC, curDist +1 });
                        visited[nextR][nextC] = true; // 방문처리

                    }
                }
            }

        }

        // 끝까지 도달 못하면 -1반환
        return -1;

    }
}