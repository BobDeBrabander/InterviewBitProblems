import java.util.ArrayList;

public class MaximalRectangle {

    public static void main(String[] args) {
        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(1);a1.add(1);a1.add(1);
        ArrayList<Integer> a2 = new ArrayList<>();
        a2.add(0);a2.add(1);a2.add(1);
        ArrayList<Integer> a3 = new ArrayList<>();
        a3.add(1);a3.add(0);a3.add(0);
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        A.add(a1);A.add(a2);A.add(a3);
        int ans = maximalRectangle(A);
    }

    public static int maximalRectangle(ArrayList<ArrayList<Integer>> A) { //NxM size matrix
        if (A.isEmpty()) return 0;
        int R = A.size();
        int C = A.get(0).size();
        ArrayList<ArrayList<Integer>> oneCount = new ArrayList<>(); //The amount of ones in the rectangle enclosed by (i, j) and (N-1, M-1)
        for (int i = 0; i<R; i++) {
            oneCount.add(new ArrayList());
            for(int j = 0; j<C; j++){
                oneCount.get(i).add(0);
            }
        }

        for (int i = R-1; i>=0; i--) {
            for(int j = C - 1; j>=0; j--){
                int count = 0;
                if (A.get(i).get(j) == 1) count++;
                if (i + 1 < R) count += oneCount.get(i+1).get(j);
                if (j+1 < C) count += oneCount.get(i).get(j+1);
                if (i+1 < R && j+1 < C) count -= oneCount.get(i+1).get(j+1);
                oneCount.get(i).set(j, count);
            }
        }

        int largestRectArea = 0;
        for (int r1 = 0; r1<R; r1++) {
//            if ((N - i)*(M) <= largestRectArea) break;
            for(int c1 = 0; c1<C; c1++){
//                if ((N - i)*(M - j) <= largestRectArea) break;
                for (int r2 = R-1; r2>=r1; r2--) {
//                    if ((k + 1 - i)*(M - j) <= largestRectArea) break;
                    for(int c2 = C-1; c2>=c1; c2--){
                        int area = (r2-r1 + 1)*(c2-c1+1);
                        if (area <= largestRectArea) break;
                        if (isFilledRectangle(oneCount, r1, c1, r2, c2)) largestRectArea = area;
                    }
                }
            }
        }
        return largestRectArea;
    }

    public static boolean isFilledRectangle(ArrayList<ArrayList<Integer>> oneCount, int r1, int c1, int r2, int c2){
        int R = oneCount.size();
        int C = oneCount.get(0).size();
        int countOfOnes = oneCount.get(r1).get(c1);
        if (r2+1 < R) countOfOnes -= oneCount.get(r2+1).get(c1);
        if (c2+1 < C) countOfOnes -= oneCount.get(r1).get(c2+1);
        if (r2+1 < R && c2+1 < C) countOfOnes += oneCount.get(r2+1).get(c2+1);
        return countOfOnes == (r2-r1+1)*(c2-c1+1);
    }
}
