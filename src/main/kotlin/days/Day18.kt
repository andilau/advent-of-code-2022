package days

@AdventOfCodePuzzle(
    name = "Boiling Boulders",
    url = "https://adventofcode.com/2022/day/18",
    date = Date(day = 18, year = 2022)
)
class Day18(input: List<String>) : Puzzle {

    private val droplets = input.map(Point3D::from)
    // 1,1,1 and 2,1,1
    // XX = 2*2 + 2*2 + 2*1 = 10

    override fun partOne(): Int {
        var sides = 0
        (0 until droplets.lastIndex).forEach { ix ->
            (ix + 1..droplets.lastIndex)
                .forEach { iy -> if (droplets[iy] in droplets[ix].neighbors()) sides++ }
        }
        return 6 * droplets.size - sides * 2
    }

    override fun partTwo() = 0

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
