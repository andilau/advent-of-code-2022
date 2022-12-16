import days.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import util.InputReader

@DisplayName("Solutions")
class SolutionsTest {
    @TestFactory
    fun testAdventOfCode() = listOf(
        Day1(InputReader.getInputAsString(1)) to Pair(71924, 210406),
        Day2(InputReader.getInputAsList(2)) to Pair(12794, 14979),
        Day3(InputReader.getInputAsList(3)) to Pair(8233, 2821),
        Day4(InputReader.getInputAsList(4)) to Pair(453, 919),
        Day5(InputReader.getInputAsList(5)) to Pair("MQSHJMWNH", "LLWJRBHVZ"),
        Day6(InputReader.getInputAsString(6)) to Pair(1042, 2980),
        Day7(InputReader.getInputAsList(7)) to Pair(1845346, 3636703),
        Day8(InputReader.getInputAsList(8)) to Pair(1816, 383520),
        Day9(InputReader.getInputAsList(9)) to Pair(6354, 2651),
        Day10(InputReader.getInputAsList(10)) to Pair(14760, EFGERURE),
        Day11(InputReader.getInputAsString(11)) to Pair(101436, 19754471646),
        Day12(InputReader.getInputAsList(12)) to Pair(468, 459),
    )
        .map { (day, answers) ->
            DynamicTest.dynamicTest("${day.javaClass.simpleName} -> ${answers.first} / ${answers.second}") {
                assertThat(day.partOne()).isEqualTo(answers.first)
                assertThat(day.partTwo()).isEqualTo(answers.second)
            }
        }

    private val EFGERURE = """
        ####.####..##..####.###..#..#.###..####.
        #....#....#..#.#....#..#.#..#.#..#.#....
        ###..###..#....###..#..#.#..#.#..#.###..
        #....#....#.##.#....###..#..#.###..#....
        #....#....#..#.#....#.#..#..#.#.#..#....
        ####.#.....###.####.#..#..##..#..#.####.""".trimIndent()
}