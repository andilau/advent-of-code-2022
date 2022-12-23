package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 23")
class Day23Test {

    private val example = """
        ....#..
        ..###.#
        #...#.#
        .#...##
        #.###..
        ##.#.##
        .#..#..""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `What is the surface area of your scanned lava droplet`() {
            assertThat(Day23(example).partOne()).isEqualTo(110)
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