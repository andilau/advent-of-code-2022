package days

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

data class Point(val x: Int, val y: Int)
