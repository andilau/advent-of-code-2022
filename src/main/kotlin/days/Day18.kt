package days

import java.util.ArrayDeque

@AdventOfCodePuzzle(
    name = "Boiling Boulders",
    url = "https://adventofcode.com/2022/day/18",
    date = Date(day = 18, year = 2022)
)
class Day18(input: List<String>) : Puzzle {

    private val droplets = input.map(Point3D::from).toSet()

    override fun partOne() = droplets.sumOf { (it.neighbors() subtract droplets).count() }

    override fun partTwo(): Int {
        var outsideFacing = 0
        val rangeX = droplets.minOf { it.x } - 1..droplets.maxOf { it.x } + 1
        val rangeY = droplets.minOf { it.y } - 1..droplets.maxOf { it.y } + 1
        val rangeZ = droplets.minOf { it.z } - 1..droplets.maxOf { it.z } + 1

        val inspect = ArrayDeque<Point3D>().apply { add(Point3D(rangeX.first, rangeY.first, rangeZ.first)) }
        val seen = mutableSetOf<Point3D>()
        while (inspect.isNotEmpty()) {
            val pop = inspect.pop()
            if (pop in seen) continue

            seen += pop
            pop.neighbors().filter { it.x in rangeX && it.y in rangeY && it.z in rangeZ }
                .forEach { side -> if (side in droplets) outsideFacing++ else inspect.add(side) }
        }
        return outsideFacing
    }

    data class Point3D(val x: Int, val y: Int, val z: Int) {

        fun neighbors() = setOf(
            copy(x = x - 1), copy(x = x + 1),
            copy(y = y - 1), copy(y = y + 1),
            copy(z = z - 1), copy(z = z + 1)
        )

        companion object {
            fun from(line: String) =
                line.split(",").map(String::toInt).let { (x, y, z) -> Point3D(x, y, z) }
        }
    }
}
