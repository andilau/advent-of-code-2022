package days

import kotlin.math.pow

@AdventOfCodePuzzle(
    name = "Full of Hot Air",
    url = "https://adventofcode.com/2022/day/25",
    date = Date(day = 25, year = 2022)
)
class Day25(val input: List<String>) : Puzzle {

    override fun partOne() = input.sumOf { it.toDecimal() }.toSnafu()

    override fun partTwo() = Unit

    companion object {

        fun String.toDecimal(): Long {
            return reversed().foldIndexed(0L) { ix, sum, char ->
                sum + 5.0.pow(ix).toLong() * (DECODING[char] ?: error("Unknown char: $char"))
            }
        }

        fun Long.toSnafu() = this.decimalToSnafuReversed().joinToString("").reversed()

        private fun Long.decimalToSnafuReversed() = sequence {
            var number = this@decimalToSnafuReversed
            var carry = 0
            while (number > 0) {
                val element = number.mod(5)
                number /= 5
                val char = when (element + carry) {
                    3 -> '='.also { carry = 1 }
                    4 -> '-'.also { carry = 1 }
                    5 -> '0'.also { carry = 1 }
                    6 -> '1'.also { carry = 1 }
                    0, 1, 2 -> (element + carry).digitToChar().also { carry = 0 }
                    else -> error("Invalid digit: ${element + carry}")
                }
                yield(char)
            }
            if (carry > 0) yield(carry.digitToChar())
        }

        private val ENCODING: Map<Int, Char> = mapOf(
            -2 to '=',
            -1 to '-',
            0 to '0',
            1 to '1',
            2 to '2',
        )
        private val DECODING: Map<Char, Int> = buildMap {
            ENCODING.forEach { (k, v) -> put(v, k) }
        }
    }
}
