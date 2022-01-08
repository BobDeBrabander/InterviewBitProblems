object RegularExpression2 {

    //Return 1 if the pattern matches the original
    //Otherwise return 0
    fun solve(original: String, pattern: String): Int {
        //A pair of indexes mapped to an outcome so we avoid duplicate work
        val memo = HashMap<Pair<Int, Int>, Boolean>()
        val ans = solveFrom(original, 0, pattern, 0, memo)
        return if (ans) 1
        else 0
    }

    private fun solveFrom(
        original: String,
        indexOrg: Int,
        pattern: String,
        indexPat: Int,
        memo: HashMap<Pair<Int, Int>, Boolean>
    ): Boolean {
        //If both the pattern and original get over we have successfully mapped the pattern to the original
        if (indexOrg >= original.length && indexPat >= pattern.length) return true
        //If one of them got over prematurely we can't map it
        if (indexPat >= pattern.length) return false

        val pairKey = indexOrg to indexPat
        val existingAnswer = memo[pairKey]
        if (existingAnswer != null) return existingAnswer

        val patternChar = pattern[indexPat]
        val originalChar = if (indexOrg < original.length) original[indexOrg] else null
        val answer: Boolean = when (indexPat < pattern.length - 1 && pattern[indexPat + 1] == '*') {
            false -> {
                (patternChar == '.' || patternChar == originalChar) &&
                    solveFrom(original, indexOrg + 1, pattern, indexPat + 1, memo)
            }
            true -> {
                if (patternChar != '.' && patternChar != originalChar) {
                    //We can try to just advance the pattern because * includes 0 occurrences
                    solveFrom(original, indexOrg, pattern, indexPat + 2, memo)
                }
                else {
                    //either we take advantage of the star by trying to process more original characters with this same pattern char
                    //Or we just don't process any character from the original string with this pattern char
                    solveFrom(original, indexOrg, pattern, indexPat + 2, memo) || //advance pattern
                        solveFrom(original, indexOrg+1, pattern, indexPat, memo) //advance original
                }
            }
        }
        memo[pairKey] = answer
        return answer
    }
}