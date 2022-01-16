import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LargestRectangleInHistogramTest {

    @Test
    fun `example test 1`() {
        val input = listOf(2, 1, 5, 6, 2, 3)
        val output = LargestRectangleInHistogram.solve(input)
        val expected = 10
        assertThat(output).isEqualTo(expected)
    }

    @Test
    fun `example test 2`() {
        val input = listOf(2)
        val output = LargestRectangleInHistogram.solve(input)
        val expected = 2
        assertThat(output).isEqualTo(expected)
    }
}