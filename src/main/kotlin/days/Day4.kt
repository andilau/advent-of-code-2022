package days

@AdventOfCodePuzzle(
    name = "Camp Cleanup",
    url = "https://adventofcode.com/2022/day/4",
    date = Date(day = 4, year = 2022)
)
class Day4(input: List<String>) : Puzzle {

    private val assignments = input.map { line -> Assignment.from(line) }

    override fun partOne(): Int = assignments.count { it.overlapCompletely }

    override fun partTwo(): Int = assignments.count { it.overlapAtAll }

    data class Assignment(val elf1: IntRange, val elf2: IntRange) {

        val overlapCompletely get() =
            (elf1.first in elf2 && elf1.last in elf2) || (elf2.first in elf1 && elf2.last in elf1)
        val overlapAtAll get() =
            (elf1.first in elf2 || elf1.last in elf2) || (elf2.first in elf1 || elf2.last in elf1)

        companion object {
            private val linePattern = Regex("""(\d+)-(\d+),(\d+)-(\d+)""")
            fun from(line: String): Assignment {
                val find = linePattern.find(line) ?: throw IllegalArgumentException("Check your input: $line")
                val (f1, l1, f2, l2) = find.groupValues.drop(1).map(String::toInt)
                return Assignment(f1..l1, f2..l2)
            }
        }
    }
}
