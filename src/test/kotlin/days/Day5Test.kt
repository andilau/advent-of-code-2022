package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 5")
class Day5Test {

    val input = """
            [D]    
        [N] [C]    
        [Z] [M] [P]
         1   2   3 
        
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `After the rearrangement procedure completes, what crate ends up on top of each stack`() {
            assertThat(Day5(input).partOne()).isEqualTo("CMZ")
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `After the rearrangement procedure completes, what crate ends up on top of each stack`() {
            assertThat(Day5(input).partTwo()).isEqualTo("MCD")
        }
    }
}