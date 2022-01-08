import java.util.ArrayList;

public class CaptureRegionsOnBoard {

    public void solve(ArrayList<ArrayList<Character>> a) {
        //For every component of connected O's check if they can 'espace' the grid
        //If they can't they can't espace the grid they become 'X'
        int N = a.size();
        int M = a.get(0).size();
        boolean[][] seen = new boolean[N][M]; //default all false
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!seen[i][j] && a.get(i).get(j) == 'O') {
                    if (!canEscape(a, i, j, N, M, seen)) {
                        System.out.println(i + " " + j);
                        fillWithXs(a, i, j, N, M);
                    }
                }
            }
        }
    }

    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public boolean canEscape(ArrayList<ArrayList<Character>> a, int row, int col, int N, int M, boolean[][] seen) {
        seen[row][col] = true;
        //If this cell is on the edge, it can espace, otherwise check it for all the non-'X' neighbors
        boolean canDirectlyEscape = (row == 0 || row == N - 1 || col == 0 || col == M - 1);
        if (canDirectlyEscape) {
            return true;
        }
        for (int i = 0; i < 8; i++) {
            int nRow = row + dy[i];
            int nCol = col + dx[i];
            if (nRow < 0 || nRow >= N || nCol < 0 || nCol >= M) continue;
            if (a.get(nRow).get(nCol) == 'X') continue;
            if (seen[nRow][nCol]) continue;
            if (canEscape(a, nRow, nCol, N, M, seen)) {
                return true;
            }
        }
        return false;
    }

    public void fillWithXs(ArrayList<ArrayList<Character>> a, int row, int col, int N, int M) {
        a.get(row).set(col, 'X');
        for (int i = 0; i < 8; i++) {
            int nRow = row + dy[i];
            int nCol = col + dx[i];
            if (nRow < 0 || nRow >= N || nCol < 0 || nCol >= M) continue;
            if (a.get(nRow).get(nCol) == 'X') continue;
            fillWithXs(a, nRow, nCol, N, M);
        }
    }
}

