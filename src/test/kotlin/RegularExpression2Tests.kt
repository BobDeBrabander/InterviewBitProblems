import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RegularExpression2Tests {

    @Test
    fun `example test 1`() {
        handleTestCase("aa", "a", 0)
    }

    @Test
    fun `example test 2`() {
        handleTestCase("aa", "aa", 1)
    }

    @Test
    fun `example test 3`() {
        handleTestCase("aaa", "aa", 0)
    }

    @Test
    fun `example test 4`() {
        handleTestCase("aa", "a*", 1)
    }

    @Test
    fun `example test 5`() {
        handleTestCase("aa", ".*", 1)
    }

    @Test
    fun `example test 6`() {
        handleTestCase("ab", ".*", 1)
    }

    @Test
    fun `example test 7`() {
        handleTestCase("aab", "c*a*b", 1)
    }

    private fun handleTestCase(original: String, pattern: String, expected: Int) {
        val output = RegularExpression2.solve(original, pattern)
        assertThat(output).isEqualTo(expected)
    }

}