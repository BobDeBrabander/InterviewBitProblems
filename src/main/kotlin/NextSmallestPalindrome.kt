object NextSmallestPalindrome {

    fun solve(a: String): String {
        val addOne = addOne(a)
        val front = front(addOne)
        val middle = middle(addOne)
        val new = front + middle + front.reversed()
        return if(isPalindrome(new) && new >= addOne) new
        else increaseUntilBiggerOrEqual(addOne, new.toMutableList())
    }

    private fun increaseUntilBiggerOrEqual(original: String, newCharArray: MutableList<Char>) : String {
        var carry = 1
        //For odd length string pLeft = pRight,
        //For even length pLeft and pRight are the middle 2 elements
        var pLeft = (original.length - 1) / 2
        var pRight = original.length / 2
        while (pLeft >= 0) {
            if (carry == 1 || newIsStrictlySmaller(original, newCharArray, pLeft, pRight)) {
                if (newCharArray[pLeft] != '9') {
                    newCharArray[pLeft]++
                    newCharArray[pRight] = newCharArray[pLeft]
                    carry = 0
                } else {
                    newCharArray[pLeft] = '0'
                    newCharArray[pRight] = newCharArray[pLeft]
                    pLeft--
                    pRight++
                    carry = 1
                }
                continue
            }
            //We know if we get here we don't have a carry and we are not strictly smaller
            if (newIsStrictlyBigger(original, newCharArray, pLeft, pRight)) break
            else {
                //In this case we have that pLeft and pRight match for original and new
                pLeft--
                pRight++
            }
        }
        if (carry == 1){
            newCharArray.add(0, '1')
            newCharArray.add('1')
        }
        return String(newCharArray.toCharArray())
    }

    private fun newIsStrictlySmaller(original: String, newCharArray: MutableList<Char>, pLeft: Int, pRight: Int) : Boolean {
        return when {
            (newCharArray[pLeft] < original[pLeft]) -> true
            (newCharArray[pLeft] == original [pLeft]) -> original[pRight] > newCharArray[pRight]
            else -> false
        }
    }

    private fun newIsStrictlyBigger(original: String, newCharArray: MutableList<Char>, pLeft: Int, pRight: Int) : Boolean {
        return when {
            (newCharArray[pLeft] > original[pLeft]) -> true
            (newCharArray[pLeft] == original [pLeft]) -> original[pRight] < newCharArray[pRight]
            else -> false
        }
    }

    private fun addOne(a: String): String {
        var carry = 1
        val charArray = a.toMutableList()
        for (i in charArray.size - 1 downTo  0) {
            val sum = charArray[i].digitToInt() + carry
            val newVal = sum % 10
            carry = if (sum >= 10) 1 else 0
            charArray[i] = '0' + newVal
        }
        if (carry != 0) charArray.add(0, '1')
        return String(charArray.toCharArray())
    }

    private fun isPalindrome(a: String) : Boolean {
        return front(a) == back(a).reversed()
    }

    private fun front (a: String) : String {
        val size = a.length
        return a.substring(0, size/2)
    }

    private fun middle(a: String) : String {
        val size = a.length
        return a.substring(size/2, size - size/2)
    }

    private fun back(a: String) : String {
        val size = a.length
        return a.substring(size - size/2, size)
    }
}