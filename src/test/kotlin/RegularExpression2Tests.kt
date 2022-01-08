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

    @Test
    fun `edge case both blank`() {
        handleTestCase("", "", 1)
    }
    @Test
    fun `edge case only pattern blank`() {
        handleTestCase("aa", "", 0)
    }

    @Test
    fun `failing case memory`() {
        handleTestCase("ababbbbaaaaabbaaaaabbbbabbabaaaaaa", "a*.b*b*.b*b*.a*.a*a*..b*a.aa*.aa*b*b*bbb*aabaa*", 0)
    }

    @Test
    fun `failing time complexity`() {
        handleTestCase("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "a*aa....a*aa*a*.aaaa..aa*aa*a.a*.a.a*a*a.a*.a*.aa*.a.a..a.a*aaa*.aaa..a*a*.a..a*..aa*a*a*a*a.a*a*a..aaa.a*.a*a.a*.a*a*..aaa*.a*a*a.a*.aa*a*a*.a..a*a*a*.a*a*.aa*.a*a..aaa*a*a.aaaa..aa....a*a*.a*a..a*.a*a*aaa*a*.a*..a*aaa.aaaa*.a*a..a*aa.a*aa*a*a*...aa*a*a*.....a*.a.a*a...aa*.aa*a..a*aaaa*aa*.aa*a.a*a.a*aa.aa..aaa.a.aaa*aa*aa*a.a*a*aa*a*a*a..aaa*a*.a*.a*a*.aaa*...a.a*a.a*a*a*.a*a*aaa*a*a*a*a..a.a*a*a*a*.a*.aa*a.a*aa.aaa*a..a*aa*..a.aa.aaaa*a*aa*.aa.a*a*aa*a*a.a*a.a*aaaa*.a..aaaa.a*a.aa*a*a*a*aaa*a*aa*a.a*aa*..aaaa*a*aaa*..aa.a*a*.a.aa*aa.aa*aa*aaa*aa*a*..a.a*aa*a*aa*aa...a*a*.aaa*a.a.a*a*aa*aaaa*a*aaa*a*aa.aa*a*aaa*.a*a*.a*a*a*a*a.a*aaa*a.aa*a*.a*.aaa.a*a*aa*a*..a*aaa*...a*..a*a*a*a..aa*aaa*a*a*aaaaa.a*a*..aa.a*.a*aaa.aa.a*a*a*a*a*aa*a*a*a*a*a*a*a*aaa..aa.a*aa*.a*a*a*aa*a....aa.a*a*aaa..aa*..aa.a.aa*..a*..a*a*..a*.a.aa*.aa...aaa*a*a*.a*aaa*a..aa*..a*aaa.aa*aaaa*.a.a*a*a..a*aa*a*aa*.a..a*a.aa*.aaa*aaa*.....a*a*aaa...a*.aa*a*.aaaaa*..a*a.aa*a.a..aaaaa*a.a*a*a*..aa..aaa*a*aaaaaaa*aaaa*.a..a.a.a......aa*a*.a.a*.aaa*a*aa*.a..a*.aa...a*a*a*a*aaaa*a*aa*a*a*a*aaa*a*a*.a*...a*.aa*a*a*.a*a*.....a.a.a*.a*.a*..a*a*a*aaaa.aa.a*a*..a*.a*a.a*a*a*aaa*a..aaa*a..a*aa..a*a.aa*a*a*..a*..a*a*...a*.aa.aaaa*aa*a*a*.a*aa.a*aa*aa*aa*.a*.a.aaa*a*aaa*aa*.aaa*a*a.a*.a*aa*a*a*...a*..aa*a*aaa*a...aa.a.aa..aaa*.aa*.a.a*aa*.aa.a*a*a*aa*aa*a*a*a*a*a*..aa*.a*aa*aa*aa*a*.a....a*aa*a.a.a*aa*aaa..aa*aaa.a*a*aa*a*a*a*aaa.a*aa..a*.aaa*a*aa*.aaa.a*..a*a.a.a*a*aa*aaa*a*aa...a*..a*.aa*a*aa..aa.aaaa*aaaa*.a.a*aaa*.a*a*a*aa*a*aa.a*a*aaaa.aa*a.a*.aaa*a.aa*a.a*.aaaa*aa*a...a*a*a.aa*a*..a*.aa*.a*aa*..a*a*a*a.aa*...aaaaa*aaa*a*.aa*aa*.a.a*.aaa*a*a*.a*.aa.aaaa*aa*.aa*.aa*a*..a*aa*a*.aa*.aa.a.aa*.aaa*.a.a*a*.aaaa*a.a.a*a*..aa.a*a*aa*.a*aa..a*a*.aaaa*a..a*a*a*.a.a*a*a*.a*..a*.a*a*a*a*.a*.a...a*....a*a*a.a*aa.a*.a*.aa*aa*.aa*..aa*....aaa*a*a.aa*aa*.a*a*a*a*..a.a*a.a*.a*a.a...aa.a*...a.a*a*a.aa*....aa.a.aa*a*.aa.aaaaaaa...a..a*a*aa*aa*aa.a..a*..aa*a*a..a*aa.aaaa*a.a*.aa.a*.a*a..a*..a*a*.aa*a*aa.a*a.aaa*a*a*aa*aa.aaa.a*.aaa....a*a*a*....aa*a*a*a*aa*a...aaa*.aa*a*a*aaa*a.a..a.aaaa*aa*aa*a.aa*aa*a*a*a*a.aa*.a*aa...a*a*aa*..a*a*..a*a*.a.a*a*.aa*a*a*..a*..a*a*a*a*a*aa*aa.a*.a.aa*a*.a..aaaaa.a.a*a*.a*.aa..aaa.a*a*a*aaaa*a*a*..a*aa*a.aa*a*aaaaaa*a.a*.a.a*..a*.a*a*aa..a.a*aaa..a*a.aa.aa*a....a*aa..a*a..a.a*aa*...aa*.a*..a*a*aa*.aa*.a.a...a*.a*...a*.aaa.a*a*.a*a*aa*.aaaa.a*a*a*..a*a*.a.a*aa*a.a.a*a*a.aa*a.aa...a*a*aa.a*aa.a*a*aaa*.a..a*....aa*.a*a.a*.aa.a*a*a*aa..a*.a*.a*aaaa*a..a*a*a*a.a*.aaaaaa*.aa.a*aa.a*aa.a.aa*.a.a*.a*a*.aa*.a...a.aa..a*aaa*..a*a*a*a*.aa*.a*aaaa*a..a*a.aa*aaa*aaaa.a*.aa*aa.a*....a*..aa*.a.a*.a*..aa*a.a*a.a...a*...a*a*aa*a*a*a.a*.aa...a..a*a.a*a*a*a*a*.a*a.a.a.a*a*a*..a*.aa*...a*.a*a*a*aa*..a.a*.a*a.aaa*aaaa*a*.aa.a*..aa.a*aa*aa...a..a*.a*aaaa.a...a*..a*a*a*a.aa..aa*aa*a*a..a*.a..aaa*a.a*aa*aaa*..a*aaa..aa*.a*.aa*aa*a*a..a.aa*aa*a*.a*.a*aa*aa*.aa*..a*..a*a.aa.a*.a*..a*.a.a*a*aaa.aa*.a*a*a*aa*a*.a.a*aaaa*.a*....a*aa*..aa*..a*aaa*a*a.a*a.a*a*aa*..a*a*aa.a*a*a*aaa*aa*.a*...a*a.a*..aa*a.aa*a...a*a.aa.a.a*aa*.a*a*a*a*aaa*a*a*.aa*..a*.aa.a*.a*.a*.aa*aa*a*..a*aa.aa.aaa*aaa*aa*a...a*a*aaaaa*a.a*.aa*.a*.a*a*a*a..a.a...a*..a*a*.a*a*aa*aaa*.a*a*.a*a*...a*a*a*a*a..a*a*a.aa.aaa*...a*a.a..aaa*a*.a*a*.a*..a*aa.aa*a..a.a*a*........a.aaa*a*...a*aaa.a*a*aa*a*a*a*",
            1
        )
    }

    private fun handleTestCase(original: String, pattern: String, expected: Int) {
        //val output = RegularExpression2.solve(original, pattern)
        val outputJava = RegularExpression2Java.solve(original, pattern)
        //assertThat(output).isEqualTo(expected)
        assertThat(outputJava).isEqualTo(expected)
    }

}