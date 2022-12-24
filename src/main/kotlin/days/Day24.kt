package days

import days.Point.Companion.EAST
import days.Point.Companion.NORTH
import days.Point.Companion.SOUTH
import days.Point.Companion.WEST
import java.util.*
import kotlin.collections.ArrayDeque

@AdventOfCodePuzzle(
    name = "Blizzard Basin",
    url = "https://adventofcode.com/2022/day/2",
    date = Date(day = 24, year = 2022)
)
class Day24(input: List<String>) : Puzzle {

    private val walls = input.extract('#')
    val max = Point(walls.maxOf { it.x }, walls.maxOf { it.y })

    private val blizzardsWest = input.extract('<')
    private val blizzardsEast = input.extract('>')
    private val blizzardsNorth = input.extract('^')
    private val blizzardsSouth = input.extract('v')
    private val start = (0..walls.maxOf { it.x }).map { x -> Point(x, 0) }.firstOrNull() { it !in walls }
        ?: error("No start found")

    private val exit =
        (0..walls.maxOf { it.x }).map { x -> Point(x, walls.maxOf { it.y }) }.firstOrNull() { it !in walls }
            ?: error("No exit found")

    private fun Set<Point>.move(round: Int, vector: Point, max: Point) =
        map { it + round * vector }
            .map { p ->
                Point(((p.x - 1).mod(max.x - 1)) + 1, ((p.y - 1).mod(max.y - 1)) + 1)
            }.toSet()

    private val WALLS = List((max.x - 2) * (max.y - 2), ::computeWalls)
    override fun partOne() = partOne(listOf(exit))

    override fun partTwo() = partOne(listOf(exit, start, exit))

    fun partOne(dest: List<Point>): Int {
        data class State(val current: Point, val time: Int, val rem: List<Point>)

        val remains = dest.toMutableList()
        var exit = remains.removeAt(0)

        val queue = ArrayDeque<List<Point>>() //{ a, b ->
            //a.size.compareTo(b.size)
            //a.distanceToExit(exit).compareTo(b.distanceToExit(exit)) }
            .apply { add(listOf(start)) }
        walls.draw(blizzardsWest, blizzardsEast, blizzardsNorth, blizzardsSouth)

        val seen = mutableSetOf<State>()
        while (queue.isNotEmpty()) {
            val route: List<Point> = queue.removeFirst()

            if (route.last() == exit && remains.isEmpty()) return route.size - 1
            if (route.last() == exit && remains.isNotEmpty()) {
                exit = remains.removeAt(0)
                queue.clear()
                seen.clear()
                println("exit = $exit $remains -> ${route.size}")
            }

            val state = State(route.last(), route.size % ((max.x - 2) * (max.y - 2)), remains)
            if (state in seen) continue
            seen += state

            val time = route.size // 1

            val wall = WALLS[time % ((max.x - 2) * (max.y - 2))]

            route.last().neighboursAndSelf()
                .filter { it !in wall }
                .filter { it.y in 0..max.y }
                .forEach { queue.add(route + it) }
        }
        error("Path not found")
    }

    private fun computeWalls(time: Int): Set<Point> {
        val moveW = blizzardsWest.move(time, WEST, max)
        val moveE = blizzardsEast.move(time, EAST, max)
        val moveN = blizzardsNorth.move(time, NORTH, max)
        val moveS = blizzardsSouth.move(time, SOUTH, max)
        //walls.draw(moveW, moveE, moveN, moveS)
        return walls + moveW + moveE + moveN + moveS
    }

    private fun Set<Point>.area() =
        (maxOf { it.x } - minOf { it.x } + 1) * (maxOf { it.y } - minOf { it.y } + 1)

    private operator fun Point.minus(other: Point) = Point(x - other.x, y - other.y)

    private fun Set<Point>.draw(west: Set<Point>, east: Set<Point>, north: Set<Point>, south: Set<Point>) {
        (minOf { it.y }..maxOf { it.y }).forEach { y ->
            (minOf { it.x }..maxOf { it.x }).map { x ->
                if (Point(x, y) in west) '<'
                else if (Point(x, y) in east) '>'
                else if (Point(x, y) in north) '^'
                else if (Point(x, y) in south) 'v'
                else if (Point(x, y) in this) '#'
                else '.'
            }.joinToString("")
                .also { println(it) }
        }
    }

}

