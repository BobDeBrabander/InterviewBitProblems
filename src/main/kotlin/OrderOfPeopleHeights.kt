object OrderOfPeopleHeights {

    data class Person(val height: Int, val inFront: Int)

    //Assumption: All heights are unique?
    fun solve(heights: List<Int>, inFronts: List<Int>) : List<Int> {
        val people = mutableListOf<Person>()
        for(i in heights.indices){
            people.add(Person(heights[i], inFronts[i]))
        }
        people.sortBy { it.height } //Sort by height ascending
        var i = heights.lastIndex
        while(i >= 0) {
            if (people[i].inFront > 0) {
                val person = people[i]
                people.removeAt(i)
                people.add(i + person.inFront, person)
            }
            i--
        }
        return people.map{it.height}
    }
}