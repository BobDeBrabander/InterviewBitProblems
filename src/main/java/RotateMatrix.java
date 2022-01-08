import java.util.ArrayList;

public class RotateMatrix {

    public void rotate(ArrayList<ArrayList<Integer>> a) {
        for (int i = 0; i <= (a.size()-1) / 2; i++){
            for (int j = i; j<a.get(i).size()-1-i; j++) {
                int small = i;
                int big = a.size()-1-i;
                swap(a, small, big, small, small);
                swap(a, small, small, big, small);
                swap(a, big, small, big, big);
            }
        }
    }

    public void swap(ArrayList<ArrayList<Integer>> a, int r1, int c1, int r2, int c2) {
        int temp = a.get(r1).get(c1);
        a.get(r1).set(c1, a.get(r2).get(c2));
        a.get(r2).set(c2, temp);
    }
}
