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
    fun neighbours() = setOf(
        copy(x = x + 1),
        copy(y = y + 1),
        copy(x = x - 1),
        copy(y = y - 1),
    )

    fun lineto(to: Point): Sequence<Point> {
        val dx = (to.x - x).sign
        val dy = (to.y - y).sign

        return generateSequence(this) {
            if (it == to) null
            else it + Point(dx, dy)
        }
    }

    private operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    override fun toString(): String {
        return "P($x, $y)"
    }

    companion object {
        fun from(line: String) = line
            .split(",")
            .map(String::toInt)
            .let { (x, y) -> Point(x, y) }
    }
}

fun List<String>.extract(char: Char): Set<Point> {
    return this.flatMapIndexed() { y, row -> row.mapIndexedNotNull { x, c -> if (c == char) Point(x, y) else null } }.toSet()
}
