package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 6")
class Day6Test {
    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        private val examples = mapOf(
            "bvwbjplbgvbhsrlpgdmjqwftvncz" to 5,
            "nppdvjthqldpwncqszvftbrmjlhg" to 6,
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" to 10,
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" to 11
        )

        @TestFactory
        fun `How many characters need to be processed before the first start-of-packet marker (4) is detected`(): List<DynamicTest> =
            examples.map { (input, position) ->
                DynamicTest.dynamicTest("Input: $input Position: $position") {
                    assertThat(Day6(input).partOne()).isEqualTo(position)
                }
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