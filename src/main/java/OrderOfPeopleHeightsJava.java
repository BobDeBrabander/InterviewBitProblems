import java.util.ArrayList;
import java.util.Collections;

public class OrderOfPeopleHeightsJava {

    class Person implements Comparable {
        int height;
        int infront;
        public Person(int height, int infront) {
            this.height = height;
            this.infront = infront;
        }


        @Override
        public int compareTo(Object o) {
            Person other = (Person) o;
            return this.height - other.height;
        }
    }

    public ArrayList<Integer> order(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) {
            people.add(new Person(A.get(i), B.get(i)));
        }
        Collections.sort(people);

        int i = people.size()-1;
        while (i>=0){
            if (people.get(i).infront > 0){
                Person p = people.get(i);
                people.remove(i);
                people.add(i+p.infront, p);
            }
            i--;
        }
        ArrayList<Integer> answer = new ArrayList<>();
        for(int j = 0; j<people.size(); j++){
            answer.add(people.get(j).height);
        }
        return answer;
    }
}
