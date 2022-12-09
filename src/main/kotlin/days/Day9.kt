package days

import kotlin.math.sign

@AdventOfCodePuzzle(
    name = "Rope Bridge",
    url = "https://adventofcode.com/2022/day/9",
    date = Date(day = 9, year = 2022)
)
class Day9(val input: List<String>) : Puzzle {

    override fun partOne(): Int = input
        .map { line -> line.first() to line.substringAfter(' ').toInt() }
        .path()
        .followMe()
        .toSet()
        .size

    override fun partTwo(): Int = input
        .map { line -> line.first() to line.substringAfter(' ').toInt() }
        .path()
        .followKnots()
        .toSet()
        .size

    data class Point(val x: Int, val y: Int) {

        infix fun adjacent(other: Point) =
            other.x in (this.x - 1..this.x + 1) &&
                    other.y in (this.y - 1..this.y + 1)

        operator fun plus(other: Point) = Point(x + other.x, y + other.y)
        operator fun minus(other: Point) = Point(x - other.x, y - other.y)

        fun follow(other: Point): Point {
            val diff = this - other
            val move = Point(diff.x.sign, diff.y.sign)
            return other + move
        }

        fun move(dir: Char) = when (dir) {
            'L' -> copy(x = x - 1)
            'R' -> copy(x = x + 1)
            'U' -> copy(y = y + 1)
            'D' -> copy(y = y - 1)
            else -> error("Check your input $dir")
        }

        override fun toString(): String {
            return "P($x, $y)"
        }

        companion object {
            val ORIGIN = Point(0, 0)
        }
    }

    private fun List<Pair<Char, Int>>.path() = sequence() {
        var current = Point.ORIGIN
        yield(current)
        this@path.forEach { (dir, steps) ->
            repeat(steps) {
                current = current.move(dir)
                yield(current)
            }
        }
    }

    private fun Sequence<Point>.followMe(): Sequence<Point> = sequence {
        var tail = this@followMe.first()
        this@followMe.drop(1).forEach { head ->
            tail = when {
                tail adjacent head -> tail
                else -> head.follow(tail)
            }
            yield(tail)
        }
    }

    private fun Sequence<Point>.followKnots(): Sequence<Point> = sequence {
        val knots = MutableList(10) { this@followKnots.first() }
        this@followKnots.drop(1).forEach { head ->
            knots[0] = head
            for ((headIndex, tailIndex) in knots.indices.zipWithNext()) {
                val h = knots[headIndex]
                val t = knots[tailIndex]
                knots[tailIndex] = when {
                    t adjacent h -> t
                    else -> h.follow(t)
                }
            }
            yield(knots.last())
        }
    }

}

