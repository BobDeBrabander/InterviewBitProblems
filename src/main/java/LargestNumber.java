import java.util.ArrayList;
import java.util.List;

public class LargestNumber {

    class StringWithCompare implements Comparable<StringWithCompare> {
        String value;

        public StringWithCompare(String value) {
            this.value = value;
        }

        @Override
        public int compareTo(StringWithCompare o) {
            return (o.value + this.value).compareTo(this.value + o.value);
        }
    }

    public String largestNumber(final List<Integer> A) {
        ArrayList<StringWithCompare> stringList = new ArrayList<>();
        A.forEach(num -> stringList.add(new StringWithCompare("" + num)));
        StringBuilder sb = new StringBuilder();
        stringList.stream().sorted().forEach(stringWithCompare ->
                sb.append(stringWithCompare.value));
        String answer = sb.toString();
        if (answer.charAt(0) == '0') return "0";
        return answer;
    }
}
