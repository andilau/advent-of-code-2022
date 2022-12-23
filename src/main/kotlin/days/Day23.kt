package days

@AdventOfCodePuzzle(
    name = "Unstable Diffusion",
    url = "https://adventofcode.com/2022/day/23",
    date = Date(day = 23, year = 2022)
)
class Day23(input: List<String>) : Puzzle {

    private val elfs = input.extract('#')
    private val dirs = listOf("N", "E", "S", "W")

    override fun partOne() =
        generateSequence(elfs to dirs) { (set, dirs) -> move(set, dirs) to dirs.rotate() }.drop(10).first().first
            .emptyGround()

    override fun partTwo() =
        generateSequence(elfs to dirs) { (set, dirs) -> move(set, dirs) to dirs.rotate() }
            .zipWithNext().indexOfFirst { (one, two) -> one.first == two.first } + 1

    private fun move(set: Set<Point>, dirs: List<String>): Set<Point> {
        val elfs = set.toMutableSet()

        val to = mutableMapOf<Point, List<Point>>()
        for (elf in elfs) {
            if (elf.neighboursAll().none { it in elfs }) {
                continue
            }
            var moved = false
            for (dir in dirs) {
                if (dir == "N" && !moved && listOf(elf.north, elf.northwest, elf.northeast).none { it in elfs }
                ) to.compute(elf.north) { _, l -> if (l == null) listOf(elf) else l + elf }
                    .also { moved = true }
                else if (dir == "E" && !moved && listOf(
                        elf.south,
                        elf.southwest,
                        elf.southeast
                    ).none { it in elfs }
                ) to.compute(elf.south) { _, l -> if (l == null) listOf(elf) else l + elf }
                    .also { moved = true }
                else if (dir == "S" && !moved && listOf(
                        elf.west,
                        elf.northwest,
                        elf.southwest
                    ).none { it in elfs }
                ) to.compute(elf.west) { _, l -> if (l == null) listOf(elf) else l + elf }.also { moved = true }
                else if (dir == "W" && !moved && listOf(
                        elf.east,
                        elf.northeast,
                        elf.southeast
                    ).none { it in elfs }
                ) to.compute(elf.east) { _, l -> if (l == null) listOf(elf) else l + elf }.also { moved = true }
            }
        }
        to.filterValues { it.size == 1 }.forEach { (new, from) ->
            elfs += new
            elfs -= from.first()
        }
        return elfs.toSet()
    }

    private fun Set<Point>.area() = (maxOf { it.x } - minOf { it.x } + 1) * (maxOf { it.y } - minOf { it.y } + 1)
    private fun Set<Point>.emptyGround() = area() - size
    private fun List<String>.rotate() = this.drop(1) + this.first()
}
