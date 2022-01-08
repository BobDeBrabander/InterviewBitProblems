import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxDistance {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(1);
        int answer = maximumGap(list);
        list.sort(Comparator.naturalOrder());
        System.out.println(answer);
    }

    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public static int maximumGap(final List<Integer> A) {
        int size = A.size();
        if (size <= 1) return 0;

        int[] biggestRight = new int[size]; //Index of the biggest element on the right of this element
        biggestRight[size-1] = -1;
        biggestRight[size-2] = size-1;
        for (int i = size-3; i>= 0; i--) {
            if (A.get(i+1) > A.get(biggestRight[i+1])) {
                biggestRight[i] = i+1;
            } else {
                biggestRight[i] = biggestRight[i+1];
            }
        }

        int leftIndex = 0;
        int rightIndex = 0;
        int biggest = 0;
        while (leftIndex < size && rightIndex < size) {
            biggest = Math.max(rightIndex - leftIndex, biggest);
            int nextBiggestIndex = biggestRight[rightIndex];
            if (nextBiggestIndex == -1) break;
            int nextVal = A.get(nextBiggestIndex);
            if (nextVal >= A.get(leftIndex)) rightIndex = nextBiggestIndex;
            else {
                leftIndex++;
                while(nextVal < A.get(leftIndex) && leftIndex < rightIndex){
                    leftIndex++;
                }
            }
        }
        return biggest;
    }
}
