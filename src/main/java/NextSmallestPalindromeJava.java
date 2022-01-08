import java.util.ArrayList;

public class NextSmallestPalindromeJava {

    public static String solve(String a) {
        String addOne = addOne(a);
        String front = front(addOne);
        String middle = middle(addOne);
        StringBuilder sb = new StringBuilder();
        sb.append(front);
        sb.reverse();
        sb.insert(0, middle);
        sb.insert(0, front);
        String newString = sb.toString();

        if (isPalindrome(newString) && newString.compareTo(addOne) >= 0) {
            return newString;
        } else {
            ArrayList<Character> newChars = new ArrayList<>();
            for (int i = 0; i < newString.length(); i++) newChars.add(newString.charAt(i));
            return increaseUntilBiggerOrEqual(addOne, newChars);
        }
    }

    private static String increaseUntilBiggerOrEqual(String original, ArrayList<Character> newCharArray) {
        int carry = 1;
        int pLeft = (original.length() - 1) / 2;
        int pRight = original.length() / 2;

        while(pLeft >= 0) {
            if (carry == 1 || newIsStrictlySmaller(original, newCharArray, pLeft, pRight)) {
                if (newCharArray.get(pLeft) != '9') {
                    newCharArray.set(pLeft, (char) (newCharArray.get(pLeft) + 1));
                    newCharArray.set(pRight, newCharArray.get(pLeft));
                    carry = 0;
                } else {
                    newCharArray.set(pLeft, '0');
                    newCharArray.set(pRight, newCharArray.get(pLeft));
                    pLeft--;
                    pRight++;
                    carry = 1;
                }
                continue;
            }
            if (newIsStrictlyBigger(original, newCharArray, pLeft, pRight)) {
                break;
            } else {
                pLeft--;
                pRight++;
            }
        }

        if (carry == 1) {
            newCharArray.add(0, '1');
            newCharArray.add('1');
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : newCharArray) sb.append(character);
        return sb.toString();
    }

    private static boolean newIsStrictlySmaller(String original, ArrayList<Character> newCharArray, int pLeft, int pRight) {
        if (original.charAt(pLeft) > newCharArray.get(pLeft)) return true;
        if (original.charAt(pLeft) == newCharArray.get(pLeft)) return original.charAt(pRight) > newCharArray.get(pRight);
        return false;
    }

    private static boolean newIsStrictlyBigger(String original, ArrayList<Character> newCharArray, int pLeft, int pRight) {
        if (original.charAt(pLeft) < newCharArray.get(pLeft)) return true;
        if (original.charAt(pLeft) == newCharArray.get(pLeft)) return original.charAt(pRight) < newCharArray.get(pRight);
        return false;
    }

    private static String addOne(String a) {
        int carry = 1;
        ArrayList<Character> newCharArray = new ArrayList<>();
        for(int i = 0; i<a.length(); i++){
            newCharArray.add(a.charAt(i));
        }


        for(int i = newCharArray.size() - 1; i >= 0; --i) {
            if (carry == 0) break;
            if (newCharArray.get(i) == '9'){
                newCharArray.set(i, '0');
                carry = 1;
            } else {
                newCharArray.set(i, (char) (newCharArray.get(i) + 1));
                carry = 0;
            }
        }

        if (carry != 0) {
            newCharArray.add(0, '1');
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<newCharArray.size(); i++) sb.append(newCharArray.get(i));
        return sb.toString();
    }

    private static boolean isPalindrome(String a) {
        String front =front(a);
        String back = back(a);
        StringBuilder sb = new StringBuilder();
        sb.append(back);
        sb.reverse();
        return sb.toString().equals(front);
    }

    private static String front(String a) {
        return a.substring(0, a.length()/2);
    }

    private static String middle(String a) {
        return a.substring(a.length()/2, a.length() - a.length()/2);
    }

    private static String back(String a) {
        return a.substring(a.length() - a.length()/2);
    }
}
