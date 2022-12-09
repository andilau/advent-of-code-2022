package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 9")
class Day9Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        private val input = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2""".trimIndent().lines()

        @Test
        fun `How many positions does the tail of the rope visit at least once`() {
            assertThat(Day9(input).partOne()).isEqualTo(13)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        private val input = """
        R 5
        U 8
        L 8
        D 3
        R 17
        D 10
        L 25
        U 20""".trimIndent().lines()

        @Test
        fun `How many positions does the tail of the rope visit at least once`() {
            assertThat(Day9(input).partTwo()).isEqualTo(36)
        }
    }
}