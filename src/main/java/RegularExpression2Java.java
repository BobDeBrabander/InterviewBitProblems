import java.util.HashMap;

public class RegularExpression2Java {

    static class Pair {
        int p1;
        int p2;

        public Pair(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    public final static int solve(String original, String pattern) {
        HashMap<Pair, Boolean> memo = new HashMap<>();
        return solveFrom(original, 0, pattern, 0, memo) ? 1 : 0;
    }

    private static boolean solveFrom(String original, int indexOrg, String pattern, int indexPat, HashMap<Pair, Boolean> memo) {
        if (indexOrg >= original.length() && indexPat >= pattern.length()) {
            return true;
        } else if (indexPat >= pattern.length()) {
            return false;
        } else {
            Pair pairKey = new Pair(indexOrg, indexPat);
            Boolean existingAnswer = memo.get(pairKey);
            if (existingAnswer != null) {
                return existingAnswer;
            } else {
                Character patternChar = pattern.charAt(indexPat);
                Character originalChar = indexOrg >= original.length() ? null : original.charAt(indexOrg);
                boolean nextCharIsStar = indexPat < pattern.length() - 1 && pattern.charAt(indexPat + 1) == '*';
                boolean answer;
                if (!nextCharIsStar) {
                    answer = (patternChar == '.' || patternChar.equals(originalChar)) && solveFrom(original, indexOrg + 1, pattern, indexPat + 1, memo);
                } else {
                    if (patternChar != '.' && !patternChar.equals(originalChar)) {
                        answer = solveFrom(original, indexOrg, pattern, indexPat + 2, memo);
                    } else {
                        answer = solveFrom(original, indexOrg, pattern, indexPat + 2, memo) ||
                                solveFrom(original, indexOrg + 1, pattern, indexPat, memo);
                    }
                }

                memo.put(pairKey, answer);
                return answer;
            }
        }
    }
}