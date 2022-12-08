package days

@AdventOfCodePuzzle(
    name = "Treetop Tree House",
    url = "https://adventofcode.com/2022/day/8",
    date = Date(day = 8, year = 2022)
)
class Day8(private val trees: List<String>) : Puzzle {


    override fun partOne(): Int {
        println("trees = ${trees.size}")
        println("count = ${trees.first().count()}")

        val inside = (1 until trees.lastIndex).flatMap { dy ->
            (1 until trees.lastIndex).map { dx -> Pair(dx, dy) }
        }.count { (x, y) -> trees.visibleAt(x, y) }

        println("inside = $inside")

        return trees.outsideTrees() + inside
    }

    override fun partTwo(): Int = 0

    private fun List<String>.outsideTrees() = 2 * (size + first().count() - 1)

    private fun List<String>.visibleAt(x: Int, y: Int): Boolean {
        println(" = $x $y")
        val line = get(y)
        val left = line.substring(0 until x)
        val right = line.substring(x + 1..line.lastIndex)

        return left.all { it < line[x] } && right.all { it < line[x] }
    }


}

