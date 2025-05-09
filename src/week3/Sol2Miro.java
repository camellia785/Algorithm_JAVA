package week3;

import java.util.*;

class Sol2Miro {
    // 위치를 저장하는 클래스 (행, 열, 거리)
    static class Point {
        int row, col, dist;

        Point(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    public int solution(String[] maps) {
        int n = maps.length;             // 미로의 세로 길이
        int m = maps[0].length();        // 미로의 가로 길이

        int[] start = null;              // 시작점 좌표
        int[] lever = null;              // 레버 좌표
        int[] exit = null;               // 출구 좌표

        // 전체 미로를 돌면서 좌표를 찾는다
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                char ch = maps[r].charAt(c);
                if (ch == 'S') {
                    start = new int[]{r, c};
                } else if (ch == 'L') {
                    lever = new int[]{r, c};
                } else if (ch == 'E') {
                    exit = new int[]{r, c};
                }
            }
        }

        // 하나라도 찾지 못하면 -1 반환
        if (start == null || lever == null || exit == null) {
            return -1;
        }

        // S → L 거리 구함
        int dist1 = bfs(maps, start, lever);
        if (dist1 == -1) return -1;  // 못 가면 실패

        // L → E 거리 구함
        int dist2 = bfs(maps, lever, exit);
        if (dist2 == -1) return -1;

        // 총 이동 거리 반환
        return dist1 + dist2;
    }

    // BFS 함수: 시작 좌표에서 목표 좌표까지 최단거리 반환
    private int bfs(String[] maps, int[] start, int[] target) {
        int n = maps.length;
        int m = maps[0].length();

        boolean[][] visited = new boolean[n][m];  // 방문 여부 확인용 배열
        Queue<Point> queue = new LinkedList<>();  // BFS용 큐 선언

        // 시작 위치 초기화
        queue.add(new Point(start[0], start[1], 0));
        visited[start[0]][start[1]] = true;

        // 상, 하, 좌, 우 방향 정의
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Point p = queue.poll();  // 큐에서 현재 위치 꺼냄

            // 도착 지점에 도달했으면 거리 반환
            if (p.row == target[0] && p.col == target[1]) {
                return p.dist;
            }

            // 4방향으로 이동 시도
            for (int d = 0; d < 4; d++) {
                int nr = p.row + dr[d];
                int nc = p.col + dc[d];

                // 조건: 미로 범위 안이고, 아직 방문하지 않았고, 벽이 아님
                if (nr >= 0 && nr < n && nc >= 0 && nc < m &&
                        !visited[nr][nc] && maps[nr].charAt(nc) != 'X') {

                    visited[nr][nc] = true;  // 방문 체크
                    queue.add(new Point(nr, nc, p.dist + 1));  // 다음 위치 큐에 추가
                }
            }
        }

        // 도달할 수 없다면 -1 반환
        return -1;
    }
}
