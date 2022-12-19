package days

@AdventOfCodePuzzle(
    name = "Boiling Boulders",
    url = "https://adventofcode.com/2022/day/18",
    date = Date(day = 18, year = 2022)
)
class Day18(input: List<String>) : Puzzle {

    private val droplets = input.map(Point3D::from).toSet()

    override fun partOne() = droplets.sumOf { (it.neighbors() subtract droplets).count() }

    override fun partTwo(): Int {
        return 0
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
