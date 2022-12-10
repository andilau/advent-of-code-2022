package days

@AdventOfCodePuzzle(
    name = "Cathode-Ray Tube",
    url = "https://adventofcode.com/2022/day/10",
    date = Date(day = 10, year = 2022)
)
class Day10(val input: List<String>) : Puzzle {

    private val instructions = input
        .map { line -> line.substringBefore(' ') to (line.substringAfter(' ').toIntOrNull() ?: 0) }

    private val cycles = setOf<Int>(20, 60, 100, 140, 180, 220)

    override fun partOne():Int {
        var cycle = 1
        var regX = 1
        var sum = 0
        for ((op, v) in instructions) {
            when (op) {
                "addx" -> {
                    repeat(2) {
                        if (cycle in cycles)  sum += cycle * regX.also { println("$cycle: $regX") }
                        cycle++;
                    }
                    regX += v
                }
                "noop" -> {
                    if (cycle in cycles)  sum += cycle * regX.also { println("$cycle: $regX") }
                    cycle += 1}
            }
        }
        return sum
    }

    override fun partTwo(): Int = 0
}
