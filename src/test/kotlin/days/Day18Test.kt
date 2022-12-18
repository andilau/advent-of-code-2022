package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 18")
class Day18Test {

    private val map = """
        498,4 -> 498,6 -> 496,6
        503,4 -> 502,4 -> 502,9 -> 494,9""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `How many units of sand come to rest before sand starts flowing into the abyss below`() {
            assertThat(Day18(map).partOne()).isEqualTo(24)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `How many units of sand come to rest`() {
            assertThat(Day18(map).partTwo()).isEqualTo(93)
        }
    }
}