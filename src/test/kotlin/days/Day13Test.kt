package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@DisplayName("Day 13")
class Day13Test {

    private val map = """
        [1,1,3,1,1]
        [1,1,5,1,1]
        
        [[1],[2,3,4]]
        [[1],4]
        
        [9]
        [[8,7,6]]
        
        [[4,4],4,4]
        [[4,4],4,4,4]
        
        [7,7,7,7]
        [7,7,7]
        
        []
        [3]
        
        [[[]]]
        [[]]
        
        [1,[2,[3,[4,[5,6,7]]]],8,9]
        [1,[2,[3,[4,[5,6,0]]]],8,9]""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @TestFactory
        fun `Pair is in the right order`(): List<DynamicTest> = listOf(0, 1, 3, 5).map { index ->
            DynamicTest.dynamicTest("Pair ${index + 1} is in the right order") {
                assertThat(Day13(map.drop(3 * index).take(3)).partOne()).isEqualTo(1)
            }
        }

        @TestFactory
        fun `Pair is not in the right order`(): List<DynamicTest> = listOf(2, 4, 6, 7).map { index ->
            DynamicTest.dynamicTest("Pair ${index + 1} is not in the right order") {
                assertThat(Day13(map.drop(3 * index).take(3)).partOne()).isEqualTo(0)
            }
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Determine the indices of the two divider packets and multiply them together`() {
            assertThat(Day13(map).partTwo()).isEqualTo(140)
        }
    }
}