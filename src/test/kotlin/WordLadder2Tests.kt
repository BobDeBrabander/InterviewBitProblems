import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class WordLadder2Tests {

    @Test
    fun `example test`(){
        val inputStart = "hit"
        val inputEnd = "cog"
        val inputDict = listOf("hot", "dot", "dog", "lot", "log")
        val output = WordLadder2.findLadders(inputStart, inputEnd, inputDict)
        val expect = listOf(
            listOf("hit", "hot", "dot", "dog", "cog"),
            listOf("hit", "hot", "lot", "log", "cog")
        )
        assertThat(output).containsExactlyInAnyOrderElementsOf(expect)
    }
}