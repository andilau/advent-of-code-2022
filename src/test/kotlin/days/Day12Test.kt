package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 12")
class Day12Test {

    private val input = """
Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `What is the fewest steps required to move from your current position to the location that should get the best signal`() {
            assertThat(Day12(input).partOne()).isEqualTo(31)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `what is the level of monkey business after 10000 rounds`() {
            val actual = Day12(input).partTwo()
            assertThat(actual).isEqualTo(0)
        }
    }
}