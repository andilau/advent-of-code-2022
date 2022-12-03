package days

@AdventOfCodePuzzle(
    name = "Rucksack Reorganization",
    url = "https://adventofcode.com/2022/day/3",
    date = Date(day = 3, year = 2022)
)
class Day3(input: List<String>) : Puzzle {

    private val rucksacks = input.asSequence().map { line -> Rucksack.from(line) }

    override fun partOne(): Int = rucksacks.sumOf { it.priority() }

    override fun partTwo(): Int = rucksacks.chunked(3)
        .map { it.map(Rucksack::content).reduce { a, b -> a intersect b }.single().priority() }
        .sum()

    companion object {
        private val PRIORITY = ('a'..'z') + ('A'..'Z')
        fun Char.priority() = PRIORITY.indexOf(this).plus(1)
    }

    data class Rucksack(val line: String) {
        fun content(): Set<Char> = line.toSet()

        fun priority(): Int = line
            .chunked(line.length / 2)
            .map { it.toSet() }
            .reduce { a, b -> a intersect b }
            .sumOf { it.priority() }

        companion object {
            fun from(line: String): Rucksack = Rucksack(line)
        }
    }
}

