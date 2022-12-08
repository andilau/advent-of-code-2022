package days

@AdventOfCodePuzzle(
    name = "Treetop Tree House",
    url = "https://adventofcode.com/2022/day/8",
    date = Date(day = 8, year = 2022)
)
class Day8(private val trees: List<String>) : Puzzle {

    override fun partOne(): Int = trees.visibleInsideTrees() + trees.outsideTrees()

    override fun partTwo(): Int {
        return (0..trees.lastIndex).flatMap { y ->
            (0..trees[y].lastIndex).map { x -> Pair(x, y) }
        }.map { p -> trees.scenicScoreAt(p.first, p.second) }.max()
    }

    private fun List<String>.scenicScoreAt(x: Int, y: Int): Int {
        val line = get(y)
        val left = line.substring(0 until x).reversed()
        val right = line.substring(x + 1..line.lastIndex)
        val top = (y - 1 downTo 0).map { this[it][x] }
        val down = (y + 1..lastIndex).map { this[it][x] }

        val ls = left.indexOfFirst { it >= line[x] }.let { if (it == -1) left.count() else it + 1 }
        val rs = right.indexOfFirst { it >= line[x] }.let { if (it == -1) right.count() else it + 1 }
        val ts = top.indexOfFirst { it >= line[x] }.let { if (it == -1) top.count() else it + 1 }
        val ds = down.indexOfFirst { it >= line[x] }.let { if (it == -1) down.count() else it + 1 }
        return ls * rs * ts * ds
    }

    private fun List<String>.outsideTrees() = 2 * (size + first().count() - 2)
    private fun List<String>.visibleInsideTrees() =
        (1 until this.lastIndex).flatMap { dy ->
            (1 until this[dy].lastIndex).map { dx -> Pair(dx, dy) }
        }.count { (x, y) -> this.visibleAt(x, y) }

    private fun List<String>.visibleAt(x: Int, y: Int): Boolean {
        val line = get(y)
        val left = line.substring(0 until x)
        val right = line.substring(x + 1..line.lastIndex)
        val top = (0 until y).map { this[it][x] }
        val down = (y + 1..lastIndex).map { this[it][x] }

        return left.all { it < line[x] } || right.all { it < line[x] } || top.all { it < line[x] } || down.all { it < line[x] }
    }


}



