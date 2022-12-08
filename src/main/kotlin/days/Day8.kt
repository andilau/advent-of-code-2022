package days

@AdventOfCodePuzzle(
    name = "Treetop Tree House",
    url = "https://adventofcode.com/2022/day/8",
    date = Date(day = 8, year = 2022)
)
class Day8(input: List<String>) : Puzzle {

    private val trees = input.map { line -> line.map { it.digitToInt() } }
    override fun partOne(): Int = trees.visibleOutsideTrees() + trees.visibleInsideTrees()

    override fun partTwo(): Int =
        (0..trees.lastIndex).flatMap { y ->
            (0..trees[y].lastIndex).map { x -> Pair(x, y) }
        }.maxOf { p -> trees.scenicScoreAt(p.first, p.second) }

    private fun List<List<Int>>.scenicScoreAt(x: Int, y: Int): Int {
        val line = get(y)
        val left = (x - 1 downTo 0).map { line[it] }
        val right = (x + 1..line.lastIndex).map { line[it] }
        val top = (y - 1 downTo 0).map { this[it][x] }
        val down = (y + 1..lastIndex).map { this[it][x] }

        val ls = left.indexOfFirst { it >= line[x] }.let { if (it == -1) left.count() else it + 1 }
        val rs = right.indexOfFirst { it >= line[x] }.let { if (it == -1) right.count() else it + 1 }
        val ts = top.indexOfFirst { it >= line[x] }.let { if (it == -1) top.count() else it + 1 }
        val ds = down.indexOfFirst { it >= line[x] }.let { if (it == -1) down.count() else it + 1 }
        return ls * rs * ts * ds
    }

    private fun List<List<Int>>.visibleOutsideTrees() = 2 * (size + first().count() - 2)

    private fun List<List<Int>>.visibleInsideTrees() =
        (1 until this.lastIndex).flatMap { dy ->
            (1 until this[dy].lastIndex).map { dx -> Pair(dx, dy) }
        }.count { (x, y) -> this.visibleAt(x, y) }

    private fun List<List<Int>>.visibleAt(x: Int, y: Int) =
        (0 until x).map { get(y)[it] }.all { it < get(y)[x] }
                || (x + 1..get(y).lastIndex).map { get(y)[it] }.all { it < get(y)[x] }
                || (0 until y).map { this[it][x] }.all { it < get(y)[x] }
                || (y + 1..lastIndex).map { this[it][x] }.all { it < get(y)[x] }
}
