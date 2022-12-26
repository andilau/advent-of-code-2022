package days

import kotlin.math.pow

@AdventOfCodePuzzle(
    name = "Full of Hot Air",
    url = "https://adventofcode.com/2022/day/25",
    date = Date(day = 25, year = 2022)
)
class Day25(val input: List<String>) : Puzzle {

    override fun partOne() = input.sumOf(::snafuToDecimal).toSnafu()

    override fun partTwo() = Unit

    companion object {

        fun snafuToDecimal(snafu: String): Long {
            var answer = 0L
            snafu.reversed().mapIndexed { ix, char ->
                val decoded = DECODING[char] ?: error("Unknown char: $char")
                answer += 5.0.pow(ix).toLong() * decoded
            }
            return answer
        }

        private fun Long.toSnafu() = toSnafuNumber(this).toList().reversed().joinToString("")

        fun toSnafuNumber(number: Long) = number.decimalToSnafuReversed().toList().reversed().joinToString("")

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
