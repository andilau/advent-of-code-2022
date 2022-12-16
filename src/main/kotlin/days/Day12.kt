package days

import java.util.PriorityQueue

@AdventOfCodePuzzle(
    name = "Hill Climbing Algorithm",
    url = "https://adventofcode.com/2022/day/12",
    date = Date(day = 12, year = 2022)
)
class Day12(val input: List<String>) : Puzzle {
    lateinit var start: Point
    lateinit var end: Point

    private val heightMap = buildMap {
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                val p = Point(x, y)
                val height: Int = when (c) {
                    in 'a'..'z' -> c - 'a'
                    'S' -> 0.also { start = p }
                    'E' -> 25.also { end = p }
                    else -> error("Unhandled character: $c")
                }
                put(p, height)
            }
        }
    }

    override fun partOne(): Int {
        val queue = PriorityQueue<Pair<Point, Int>> { a, b -> a.second.compareTo(b.second) }
        queue.add(start to 0)
        val seen = mutableSetOf<Point>()

        while (queue.isNotEmpty()) {
            val (next, cost) = queue.poll()

            if (next in seen) continue
            seen += next
            if (next == end) return cost
            next.neighbours()
                .filter { it in heightMap }
                .filter { heightMap[it]!! - heightMap[next]!! <= 1 }
                .forEach { queue.add(it to cost + 1) }
        }
        error("No path found!")
    }

    override fun partTwo(): Int {
        val queue = PriorityQueue<Pair<Point, Int>> { a, b -> a.second.compareTo(b.second) }
        queue.add(end to 0)
        val seen = mutableSetOf<Point>()

        while (queue.isNotEmpty()) {
            val (next, cost) = queue.poll()

            if (next in seen) continue
            seen += next
            if (heightMap[next] == 0) return cost
            next.neighbours()
                .filter { it in heightMap }
                .filter { heightMap[next]!! - heightMap[it]!! <= 1 }
                .forEach { queue.add(it to cost + 1) }
        }
        error("No path found!")
    }
}
