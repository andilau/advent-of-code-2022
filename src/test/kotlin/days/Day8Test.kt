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
        @Test
        fun `What is the highest scenic score possible for any tree`() {
            assertThat(Day8(input).partTwo()).isEqualTo(8)
        }
    }
}