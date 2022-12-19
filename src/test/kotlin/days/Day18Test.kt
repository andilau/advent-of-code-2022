package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 18")
class Day18Test {

    private val example = """
        2,2,2
        1,2,2
        3,2,2
        2,1,2
        2,3,2
        2,2,1
        2,2,3
        2,2,4
        2,2,6
        1,2,5
        3,2,5
        2,1,5
        2,3,5""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `What is the surface area`() {
            val droplets = listOf("1,1,1", "2,1,1")
            assertThat(Day18(droplets).partOne()).isEqualTo(10)
        }

        @Test
        fun `What is the surface area of your scanned lava droplet`() {
            assertThat(Day18(example).partOne()).isEqualTo(64)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `What is the exterior surface area of your scanned lava droplet`() {
            assertThat(Day18(example).partTwo()).isEqualTo(58)
        }
    }
}