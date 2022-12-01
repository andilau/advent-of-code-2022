package days

@AdventOfCodePuzzle(
    name = "Calorie Counting",
    url = "https://adventofcode.com/2022/day/1",
    date = Date(day = 1, year = 2022)
)
class Day1(input: String) : Puzzle {

    private val caloriesByElf: List<Int> = input
        .split("\n\n")
        .map { caloriesString -> caloriesString.split("\n").sumOf { it.toInt() } }

    override fun partOne(): Int = caloriesByElf.max()

    override fun partTwo(): Int = caloriesByElf.sortedDescending().take(3).sum()
}