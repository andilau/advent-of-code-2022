package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 25")
class Day25Test {

    private val decToSnafu = """
    1              1
    2              2
    3             1=
    4             1-
    5             10
    6             11
    7             12
    8             2=
    9             2-
    10             20
    15            1=0
    20            1-0
    2022         1=11-2
    12345        1-0---0
    314159265  1121-1110-1=0""".trimIndent().lines().map { it.split(Regex("""\s+""")) }

    @TestFactory
    fun decimalToSnafu(): List<DynamicTest> = decToSnafu
        .map { (decimal, snafu) ->
            DynamicTest.dynamicTest("Test: $decimal -> $snafu") {
                assertThat(Day25.toSnafuNumber(decimal.toLong())).isEqualTo(snafu)
            }
        }

    @TestFactory
    fun snafuToDecimal(): List<DynamicTest> = decToSnafu
        .map { (decimal, snafu) ->
            DynamicTest.dynamicTest("Test: $snafu -> $decimal") {
                assertThat(Day25.snafuToDecimal(snafu)).isEqualTo(decimal.toLong())
            }
        }

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        private val snafuNumbers = """
            1=-0-2
            12111
            2=0=
            21
            2=01
            111
            20012
            112
            1=-1=
            1-12
            12
            1=
            122""".trimIndent().lines()

        @Test
        fun `What SNAFU number do you supply to Bob's console`() {
            assertThat(Day25(snafuNumbers).partOne()).isEqualTo("2=-1=0")
        }
    }

}