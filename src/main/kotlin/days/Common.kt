package days

import kotlin.math.sign

fun lcm(x: Long, y: Long, vararg ints: Long): Long =
    ints.fold(x * y / gcd(x, y)) { acc, z -> lcm(acc, z) }

fun lcm(x: Long, y: Long, ints: List<Long>): Long =
    ints.fold(x * y / gcd(x, y)) { acc, z -> lcm(acc, z) }

fun gcd(a: Long, b: Long): Long {
    if (b == 0L) return a
    return gcd(b, a % b)
}

fun neighborsAndSelf(x: Int, y: Int) =
    (x..y).flatMap { dy ->
        (x..y).map { dx ->
            Pair(dx, dy)
        }
    }

data class Point(val x: Int, val y: Int) {

    val north: Point get() = this.copy(y = y - 1)
    val northwest: Point get() = this + Point(-1, -1)
    val northeast: Point get() = this + Point(1, -1)
    val south: Point get() = this + Point(0, 1)
    val southwest: Point get() = this + Point(-1, 1)
    val southeast: Point get() = this + Point(1, 1)
    val west: Point get() = this + Point(-1, 0)
    val east: Point get() = this + Point(1, 0)

    fun neighboursAndSelf(): Set<Point> = setOf(
        copy(),
        copy(x = x + 1),
        copy(y = y + 1),
        copy(x = x - 1),
        copy(y = y - 1),
    )

    fun neighbours() = setOf(
        copy(x = x + 1),
        copy(y = y + 1),
        copy(x = x - 1),
        copy(y = y - 1),
    )

    fun neighboursAll() = setOf(
        copy(x = x + 1),
        copy(x = x - 1),
        copy(x = x + 1, y = y - 1),
        copy(x = x + 1, y = y + 1),
        copy(y = y + 1),
        copy(y = y - 1),
        copy(x = x - 1, y = y - 1),
        copy(x = x - 1, y = y + 1),
    )

    fun lineto(to: Point): Sequence<Point> {
        val dx = (to.x - x).sign
        val dy = (to.y - y).sign

        return generateSequence(this) {
            if (it == to) null
            else it + Point(dx, dy)
        }
    }

    operator fun plus(other: Point) = Point(x + other.x, y + other.y)

    operator fun times(value: Int) = Point(x * value, y * value)

    override fun toString(): String {
        return "P($x,$y)"
    }

    companion object {
        val WEST = Point(-1, 0)
        val EAST = Point(1, 0)
        val NORTH = Point(0, -1)
        val SOUTH = Point(0, 1)

        fun from(line: String) = line
            .split(",")
            .map(String::toInt)
            .let { (x, y) -> Point(x, y) }
    }
}

operator fun Int.times(vector: Point) = vector.times(this)

fun List<String>.extract(char: Char): Set<Point> {
    return this.flatMapIndexed() { y, row -> row.mapIndexedNotNull { x, c -> if (c == char) Point(x, y) else null } }
        .toSet()
}

fun Set<Point>.draw() {
    (minOf { it.y }..maxOf { it.y }).forEach { y ->
        (minOf { it.x }..maxOf { it.x }).map { x -> if (Point(x, y) in this) '#' else '.' }.joinToString("")
            .also { println(it) }
    }
}
