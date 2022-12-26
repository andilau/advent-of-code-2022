package days

import days.Point.Companion.EAST
import days.Point.Companion.NORTH
import days.Point.Companion.SOUTH
import days.Point.Companion.WEST
import java.util.concurrent.ConcurrentHashMap

@AdventOfCodePuzzle(
    name = "Blizzard Basin",
    url = "https://adventofcode.com/2022/day/2",
    date = Date(day = 24, year = 2022)
)
class Day24(input: List<String>) : Puzzle {
    private val walls = input.extract('#')
    private val max = Point(walls.maxOf { it.x }, walls.maxOf { it.y })
    private val cycles = (max.x - 1) * (max.y - 1)
    private val start = openingsIn(0) ?: error("No start found")

    private val exit = openingsIn(max.y) ?: error("No exit found")

    private fun openingsIn(y: Int) = (0..max.x).map { x -> Point(x, y) }.firstOrNull { it !in walls }
    private val blizzardsWest = input.extract('<')
    private val blizzardsEast = input.extract('>')
    private val blizzardsNorth = input.extract('^')
    private val blizzardsSouth = input.extract('v')

    private val obstacles = ConcurrentHashMap<Int, Set<Point>>()

    override fun partOne() = solve(listOf(start), exit).lastIndex

    override fun partTwo() = solve(solve(solve(listOf(start), exit), start), exit).lastIndex

    private fun solve(path: List<Point>, goal: Point): List<Point> {
        data class State(val current: Point, val time: Int)

        val queue = ArrayDeque<List<Point>>().apply { add(path) }
        val seen = mutableSetOf<State>()
        while (queue.isNotEmpty()) {
            val route: List<Point> = queue.removeFirst()
            if (route.last() == goal) return route

            val state = State(route.last(), route.size % cycles)
            if (state in seen) continue
            seen += state

            val obstacle = obstacles.getOrPut(route.size % cycles) { computeObstacles(route.size) }
            route.last().neighboursAndSelf()
                .filter { it.y in 0..max.y }
                .filter { it !in obstacle }
                .forEach { queue += (route + it) }
        }
        error("Path not found")
    }

    private fun computeObstacles(time: Int): Set<Point> =
        walls + blizzardsWest.move(time, WEST, max) +
                blizzardsEast.move(time, EAST, max) +
                blizzardsNorth.move(time, NORTH, max) +
                blizzardsSouth.move(time, SOUTH, max)

    private fun Set<Point>.move(round: Int, vector: Point, max: Point) =
        map { it + round * vector }
            .map { Point((it.x - 1).mod(max.x - 1) + 1, (it.y - 1).mod(max.y - 1) + 1) }
            .toSet()
}
