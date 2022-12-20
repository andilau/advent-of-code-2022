package days

@AdventOfCodePuzzle(
    name = "Distress Signal",
    url = "https://adventofcode.com/2022/day/13",
    date = Date(day = 13, year = 2022)
)
class Day13(val input: List<String>) : Puzzle {

    private val pairs = input.chunked(3).map { it[0] to it[1] }

    override fun partOne(): Int {
        return pairs.mapIndexed { ix, p -> (ix + 1) * if (p.first < p.second) 1 else 0 }.sum()
    }

    override fun partTwo(): Int {
        return 0
    }

    data class Packet(val content: List<Int>) {
        companion object {
            fun from(line: String) =
                if (line[0] == '[')
                    line.removePrefix("[").removeSuffix("]").split(",").map(String::toInt).let { Packet(it) }
                else TODO()

        }
    }
}
