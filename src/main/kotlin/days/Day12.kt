package days

@AdventOfCodePuzzle(
    name = "Hill Climbing Algorithm",
    url = "https://adventofcode.com/2022/day/12",
    date = Date(day = 12, year = 2022)
)
class Day12(val input: List<String>) : Puzzle {

    private val heightMap = buildMap<Point, Int> {
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                when (c) {
                    in 'a'..'z' -> put(Point(x, y), c - 'A')
                    'S' -> println("S")
                    'E' -> println("E")
                }
            }
        }
    }

    override fun partOne(): Int {
        heightMap.forEach(System.out::println)
        return 0
    }

    override fun partTwo(): Int = 0

}
