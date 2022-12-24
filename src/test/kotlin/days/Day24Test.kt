package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import kotlin.text.Typography.times

@DisplayName("Day 24")
class Day24Test {

    private val example = """
#.######
#>>.<^<#
#.<..<<#
#>v.><>#
#<^v^^>#
######.#""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `1What is the fewest number of minutes required to avoid the blizzards and reach the goal`() {
            val example = """
                #.#####
                #...v.#
                #..>..#
                #.....#
                #.....#
                #.....#
                #####.#""".trimIndent().lines()

            assertThat(Day24(example).partOne()).isEqualTo(18)
        }

        @Test
        fun `2What is the fewest number of minutes required to avoid the blizzards and reach the goal`() {
            val example = """
                #.#####
                #...^.#
                #..<..#
                #.....#
                #.....#
                #.....#
                #####.#""".trimIndent().lines()

            assertThat(Day24(example).partOne()).isEqualTo(18)
        }

        @Test
        fun `Move test`() {
            val point = Point(2, 2)
            val WEST = Point(-1, 0)
            val max = Point(6, 6)
            val tran = { p: Point, times: Int -> p + times * WEST }
            val func = { p: Point -> Point(((p.x - 1).mod  (max.x - 1)) + 1, ((p.y - 1) % (max.y - 1)) + 1) }

            (0..10).forEach { times->
                print("$times: ")
                val tran1 = tran(point, times).also { print("TRANS: $it ") }
                val func1 = func(tran1).also { println("MAP: ->$it") }
                assertThat(func1.x).isIn(1..5)
            }
        }

        @Test
        fun `What is the fewest number of minutes required to avoid the blizzards and reach the goal`() {
            assertThat(Day24(example).partOne()).isEqualTo(18)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `What is the number of the first round where no Elf moves`() {
            assertThat(Day24(example).partTwo()).isEqualTo(54)
        }
    }
}