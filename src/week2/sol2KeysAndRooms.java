package week2;

import java.util.List;

public class sol2KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms){
        int n = rooms.size(); // 방의 총 개수 정의
        boolean[] visited = new boolean[n]; // (1)각 방의 방문 여부 기록!!!

        // DFS로 0번방부터 방문 시작
        dfs(0, rooms, visited);

        // 모든 방이 방문되었는지 확인
        for (boolean v : visited ) {
            if (!v) return false; // 방문하지못한 방이 있으면 false
        }
        return true; // 모든 방 방문 완료

    }
    // (2) 방문로직-> DFS함수: 현재방 번호와 방문 배열, rooms 정보
    // List<List<Integer>> rooms → 인접 리스트 형태
    // 함수의 입력값(매개변수 parameter) : java에서 2차원 리스트를 일반적으로 다룰때 사용

    private void dfs(int room, List<List<Integer>> rooms, boolean[] visited ){
        visited[room] = true; // 현재 방 방문처리

        // 현재 방에서 얻은 열쇠(다른 방 번호)들을 하나씩 꺼내기
        for (int key : rooms.get(room)){
            if (!visited[key]){ // 아직 방문 안했다면
                dfs(key, rooms, visited); //그 방을 열고 DFS 탐색
            }
        }
    }

}