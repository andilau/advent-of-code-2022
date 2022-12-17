package days

@AdventOfCodePuzzle(
    name = "Regolith Reservoir",
    url = "https://adventofcode.com/2022/day/14",
    date = Date(day = 14, year = 2022)
)
class Day14(walls: List<String>) : Puzzle {
    private val cave = createCave(walls)

    private val start = Point.from("500,0")
    private val abyssBegins = cave.maxOf { it.y }

    override fun partOne() = cave.fillSand(abyssBegins)

    override fun partTwo() = cave.fillSand(abyssBegins + 3) { p -> p.y > abyssBegins }

    private fun createCave(walls: List<String>) = walls.flatMap {
        it.split(" -> ")
            .map { Point.from(it) }
            .zipWithNext()
            .flatMap { (from, to) -> from.lineto(to) }
    }.toSet()

    private fun Set<Point>.fillSand(abyss: Int, alsoStop: (Point) -> Boolean = { false }): Int {
        val occ = this.toMutableSet()
        var sandAtRest = 0
        while (true) {
            var current = start
            while (true) {
                val next = sequenceOf(current.down(), current.downLeft(), current.downRight())
                    .firstOrNull { it !in occ }

                when {
                    next == null || alsoStop(current) -> {
                        occ += current
                        sandAtRest++
                        if (current == start) return sandAtRest
                        break
                    }

                    next.y >= abyss -> {
                        return sandAtRest
                    }

                    else -> current = next
                }
            }
        }
    }

    private fun Set<Point>.draw() {
        val minY = minOf { it.y }
        val maxY = maxOf { it.y }
        val minX = minOf { it.x }
        val maxX = maxOf { it.x }
        (minY..maxY).forEach { y ->
            (minX..maxX).map { x -> if (Point(x, y) in this) '#' else '.' }.joinToString("").also { println(it) }
        }
    }

    private fun Point.down(): Point = copy(y = y + 1)
    private fun Point.downLeft(): Point = copy(x = x - 1, y = y + 1)
    private fun Point.downRight(): Point = copy(x = x + 1, y = y + 1)
}
