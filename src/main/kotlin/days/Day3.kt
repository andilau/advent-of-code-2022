package days

@AdventOfCodePuzzle(
    name = "Rucksack Reorganization",
    url = "https://adventofcode.com/2022/day/3",
    date = Date(day = 3, year = 2022)
)
class Day3(input: List<String>) : Puzzle {

    private val rucksacks = input.asSequence().map { line -> Rucksack.from(line) }

    override fun partOne(): Int = rucksacks.sumOf { it.priority() }

    override fun partTwo(): Int = 0

    data class Rucksack(val line: String) {

        fun priority(): Int = line
            .chunked(line.length / 2).map { it.toSet() }
            .reduce { a, b -> a intersect b }
            .map { a -> DICT.indexOf(a) + 1 }
            .sum()

        companion object {
            fun from(line: String): Rucksack = Rucksack(line)

            val DICT = ('a'..'z') + ('A'..'Z')
        }
    }
}