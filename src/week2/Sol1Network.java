package week2;

public class Sol1Network {

    // (1) 컴퓨터 개수와 연결상태-2차원배열을 받는 함수
    public int solution(int n, int[][] computers){
        int answer = 0; // (2) 네트워크 수 카운트

        boolean[] visited = new boolean[n]; //
        // (3) 방문한 컴퓨터 기록용 배열

        // (4) 모든 컴퓨터를 하나씩 돌기
        for (int i =0; i < n; i++){
            if (visited[i]==false){ // (5) 아직 방문하지 않은 컴퓨터면
                dfs(i, visited, computers, n); // (6) dfs로 연결된 컴퓨터 전부 방문
                answer++; //(7) DFS로 하나의 네트워크를 탐색 완료했으므로 +1
            }
        }
        return answer;
    }
    // (8) DFS함수 선언: 현재노드(i)를 기준으로 연결된 애들 탐색
    public void dfs(int current, boolean[] visited, int[][] computers, int n){
        visited[current] = true; // (9) 현재 컴퓨터 방문 처리

        // (10) 모든 컴퓨터를 검사하면서
        for (int j=0; j < n; j++){
            // (11) i와 j가 연결되어 있고, (computer[i][j] == 1)
            //      j를 아직 방문하지 않으면
            if (computers[current][j] == 1 && !visited[j]) {
                dfs(j, visited, computers, n); // (12) 그 j도 dfs로 탐색
            }
        }
    }

}