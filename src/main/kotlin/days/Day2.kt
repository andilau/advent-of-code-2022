package days

import java.lang.IllegalArgumentException

@AdventOfCodePuzzle(
    name = "Rock Paper Scissors",
    url = "https://adventofcode.com/2022/day/2",
    date = Date(day = 2, year = 2022)
)
class Day2(input: List<String>) : Puzzle {

    private val rounds = input.asSequence().map { line -> Match.from(line) }

    override fun partOne(): Int = rounds.fold(0) { total, match -> total + match.points() }

    override fun partTwo(): Int = rounds.fold(0) { total, match -> total + match.pointsAlternative() }

    data class Match(val other: RPS, val me: RPS) {

        fun points(): Int = me.value + when (me) {
            other -> 3
            other.winsOver() -> 6
            other.loosesOver() -> 0
            else -> error("Illegal state!")
        }

        fun pointsAlternative(): Int = when (me) {
            RPS.Rock -> 0 + other.loosesOver().value // loose
            RPS.Paper -> 3 + other.value // draw
            RPS.Scissors -> 6 + other.winsOver().value // win
        }

        companion object {
            fun from(line: String): Match = Match(RPS.from(line.first()), RPS.from(line.last()))
        }
    }

    enum class RPS(val value: Int) {
        Rock(1),
        Paper(2),
        Scissors(3);

        fun winsOver() = when (this) {
            Rock -> Paper
            Paper -> Scissors
            Scissors -> Rock
        }

        fun loosesOver() = when (this) {
            Rock -> Scissors
            Paper -> Rock
            Scissors -> Paper
        }

        companion object {
            fun from(char: Char) = when (char) {
                in "AX" -> Rock
                in "BY" -> Paper
                in "CZ" -> Scissors
                else -> throw IllegalArgumentException()
            }
        }
    }
}