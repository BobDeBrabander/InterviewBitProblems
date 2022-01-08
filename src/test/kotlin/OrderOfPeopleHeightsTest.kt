import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OrderOfPeopleHeightsTest {

    @Test
    fun `example test`(){
        val heights = listOf(5, 3, 2, 6, 1, 4)
        val inFronts = listOf(0, 1, 2, 0, 3, 2)
        val expected = listOf(5, 3, 2, 1, 6, 4)
        val output = OrderOfPeopleHeights.solve(heights, inFronts)
        assertThat(expected).isEqualTo(output)
    }
}