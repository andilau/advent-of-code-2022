package days

@AdventOfCodePuzzle(
    name = "Regolith Reservoir",
    url = "https://adventofcode.com/2022/day/14",
    date = Date(day = 14, year = 2022)
)
class Day14(walls: List<String>) : Puzzle {

    private val cave: List<Point> = walls.flatMap {
        it.split(" -> ")
            .map { Point.from(it) }
            .zipWithNext()
            .flatMap { (from, to) -> from.lineto(to)}
            .toSet()
    }

    override fun partOne(): Int {
        println(cave.size)
        return 0
    }

    override fun partTwo(): Int {
        return 0
    }
}
