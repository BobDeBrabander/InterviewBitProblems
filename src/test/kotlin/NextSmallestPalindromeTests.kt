import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NextSmallestPalindromeTests {

    @Test
    fun test1(){
        val input = "2345"
        val expectedOut = "2442"
        val output = NextSmallestPalindrome.solve(input)
        assertThat(output).isEqualTo(expectedOut)
    }

    @Test
    fun test2(){
        val input = "2993"
        val expectedOut = "3003"
        val output = NextSmallestPalindrome.solve(input)
        assertThat(output).isEqualTo(expectedOut)
    }

    @Test
    fun test3(){
        val input = "999"
        val expectedOut = "1001"
        val output = NextSmallestPalindrome.solve(input)
        assertThat(output).isEqualTo(expectedOut)
    }

    @Test
    fun test4(){
        val input = "289983"
        val expectedOut = "290092"
        val output = NextSmallestPalindrome.solve(input)
        assertThat(output).isEqualTo(expectedOut)
    }

    @Test
    fun test5(){
        val input = "291993"
        val expectedOut = "292292"
        val output = NextSmallestPalindrome.solve(input)
        assertThat(output).isEqualTo(expectedOut)
    }

    @Test
    fun test6(){
        val input = "23545"
        val expectedOut = "23632"
        val output = NextSmallestPalindrome.solve(input)
        assertThat(output).isEqualTo(expectedOut)
    }
}