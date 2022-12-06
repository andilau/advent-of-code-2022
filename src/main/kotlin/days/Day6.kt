package days

@AdventOfCodePuzzle(
    name = "Tuning Trouble",
    url = "https://adventofcode.com/2022/day/6",
    date = Date(day = 6, year = 2022)
)
class Day6(val input: String) : Puzzle {


    override fun partOne(): Int = input.positionOfToken(4)

    override fun partTwo(): Int = input.positionOfToken(14)

    private fun String.positionOfToken(characterLength: Int) = characterLength + windowed(characterLength, 1, true)
        .indexOfFirst { ivs -> ivs.toSet().size == characterLength }
}