package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 14")
class Day14Test {

    private val map = """
        498,4 -> 498,6 -> 496,6
        503,4 -> 502,4 -> 502,9 -> 494,9""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `How many units of sand come to rest before sand starts flowing into the abyss below`() {
            assertThat(Day14(map).partOne()).isEqualTo(24)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `What is the fewest steps required to move starting from any square with elevation a to the location that should get the best signal`() {
            assertThat(Day14(map).partTwo()).isEqualTo(29)
        }
    }
}