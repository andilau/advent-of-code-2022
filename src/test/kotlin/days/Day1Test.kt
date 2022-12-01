package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1")
class Day1Test {

    val calories = """
        1000
        2000
        3000
        
        4000
        
        5000
        6000
        
        7000
        8000
        9000
        
        10000""".trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun shouldReturnSumOfCaloriesOfMaxCarried() {
            assertThat(Day1(calories).partOne()).isEqualTo(24000)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun shouldReturnSumOfCaloriesOfTopThree() {
            assertThat(Day1(calories).partTwo()).isEqualTo(45000)
        }
    }
}