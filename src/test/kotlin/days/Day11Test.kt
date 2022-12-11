package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 11")
class Day11Test {

    private val input = """
Monkey 0:
  Starting items: 79, 98
  Operation: new = old * 19
  Test: divisible by 23
    If true: throw to monkey 2
    If false: throw to monkey 3

Monkey 1:
  Starting items: 54, 65, 75, 74
  Operation: new = old + 6
  Test: divisible by 19
    If true: throw to monkey 2
    If false: throw to monkey 0

Monkey 2:
  Starting items: 79, 60, 97
  Operation: new = old * old
  Test: divisible by 13
    If true: throw to monkey 1
    If false: throw to monkey 3

Monkey 3:
  Starting items: 74
  Operation: new = old + 3
  Test: divisible by 17
    If true: throw to monkey 0
    If false: throw to monkey 1""".trimIndent()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `What is the level of monkey business after 20 rounds of stuff-slinging simian shenanigans`() {
            assertThat(Day11(input).partOne()).isEqualTo(10605)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `what is the level of monkey business after 10000 rounds`() {
            val actual = Day11(input).partTwo()
            assertThat(actual).isEqualTo(2713310158L)
        }
    }
}