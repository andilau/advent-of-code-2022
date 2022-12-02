package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 2")
class Day2Test {

    val rounds = """
A Y
B X
C Z""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun shouldSumUpAllRounds() {
            assertThat(Day2(rounds).partOne()).isEqualTo(15)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun shouldSumUpAllRoundsDifferentCalculation() {
            assertThat(Day2(rounds).partTwo()).isEqualTo(12)
        }
    }
}