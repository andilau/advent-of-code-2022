package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 8")
class Day8Test {

    val input = """
        30373
        25512
        65332
        33549
        35390""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `How many trees are visible from outside the grid`() {
            assertThat(Day8(input).partOne()).isEqualTo(21)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        val examples = mapOf(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 19,
            "bvwbjplbgvbhsrlpgdmjqwftvncz" to 23,
            "nppdvjthqldpwncqszvftbrmjlhg" to 23,
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 29,
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 26,
        )

        @TestFactory
        fun `How many characters need to be processed before the first start-of-packet marker (14) is detected`(): List<DynamicTest> =
            examples.map { (input, position) ->
                DynamicTest.dynamicTest("Input: $input Position: $position") {
                    assertThat(Day6(input).partTwo()).isEqualTo(position)
                }
            }
    }
}