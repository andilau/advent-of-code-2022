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

    override fun partOne(): Int {
        var cycle = 1
        var regX = 1
        var sum = 0
        for ((op, v) in instructions) {
            when (op) {
                "addx" -> {
                    repeat(2) {
                        if (cycle in cycles) sum += cycle * regX.also { println("$cycle: $regX") }
                        cycle++;
                    }
                    regX += v
                }

                "noop" -> {
                    if (cycle in cycles) sum += cycle * regX.also { println("$cycle: $regX") }
                    cycle++
                }
            }
        }
        return sum
    }

    override fun partTwo(): BooleanArray {
        val crt = BooleanArray(240)
        assert(crt.size == 240)
        var cycle = 1
        var regX = 1
        for ((op, v) in instructions) {
            when (op) {
                "addx" -> {
                    repeat(2) {
                        crt[cycle - 1] = (cycle - 1) % 40 in (regX - 1)..(regX + 1)
                        cycle++
                    }
                    regX += v
                }

                "noop" -> {
                    crt[cycle - 1] = (cycle - 1) % 40 in (regX - 1)..(regX + 1)
                    cycle++
                }
            }
        }
        println("${crt.map { if (it) '#' else '.' }.joinToString("").chunked(40).joinToString("\n")}")

        return crt
    }
}
