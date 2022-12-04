package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 4")
class Day4Test {

    val assignments = """2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8""".lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `In two assignment pairs one range fully contains the other`() {
            assertThat(Day4(assignments).partOne()).isEqualTo(2)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `In four assignment pairs one range fully overlap the other at all`() {
            assertThat(Day4(assignments).partTwo()).isEqualTo(4)
        }
    }
}