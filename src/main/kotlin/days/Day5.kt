package days

@AdventOfCodePuzzle(
    name = "Supply Stacks",
    url = "https://adventofcode.com/2022/day/5",
    date = Date(day = 5, year = 2022)
)
class Day5(input: List<String>) : Puzzle {

    private val stacks = input
        .takeWhile { it.isNotBlank() }
        .reversed().drop(1)
        .map { line ->
            line.indices.filter { (it - 1) % 4 == 0 }.map { line[it] }
        }
        .toStacks()

    private val procedures = input
        .dropWhile { it.isNotBlank() }.drop(1)
        .map { line -> Procedure.from(line) }

    override fun partOne(): String = stacks.deepCopy().moveElements { it }.also { println(it) }

    override fun partTwo(): String = stacks.deepCopy().moveElements { it.reversed() }.also { println(it) }

    private fun List<List<Char>>.toStacks() =
        (0..this.maxOf { it.lastIndex })
            .map { ix -> this.mapNotNull { it.getOrNull(ix) }.dropLastWhile { !it.isLetter() }.toArrayDeque() }

    private fun List<ArrayDeque<Char>>.moveElements(transform: (List<Char>) -> List<Char>) =
        procedures.fold(this) { stacks, p ->
            val (quantity, from, to) = p
            val mapNotNull: List<Char> = (1..quantity).mapNotNull { stacks[from].removeLastOrNull() }
            stacks[to].addAll(transform(mapNotNull))
            stacks
        }.joinToString("") { it.last().toString() }

    data class Procedure(val quantity: Int, val from: Int, val to: Int) {
        companion object {
            private val linePattern = Regex("""move (\d+) from (\d+) to (\d+)""")
            fun from(line: String): Procedure {
                val find = linePattern.find(line) ?: throw IllegalArgumentException("Check your input: $line")
                val (move, from, to) = find.groupValues.drop(1).map(String::toInt)
                return Procedure(move, from - 1, to - 1)
            }
        }
    }

    private fun <T> List<T>.toArrayDeque() = ArrayDeque(this)
    private fun <T> List<ArrayDeque<T>>.deepCopy() = this.map { it.toArrayDeque() }
}
