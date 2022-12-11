package days

@AdventOfCodePuzzle(
    name = "Monkey in the Middle",
    url = "https://adventofcode.com/2022/day/11",
    date = Date(day = 11, year = 2022)
)
class Day11(val input: String) : Puzzle {

    private val monkeys1 = input.split("\n\n").map { Monkey.from(it.lines()) }
    private val monkeys2 = input.split("\n\n").map { Monkey.from(it.lines()) }

    override fun partOne(): Int = monkeys1.toList().run(20, 3).twoMostActiveMnkeysInspectedMultiplied().toInt()

    override fun partTwo(): Long = monkeys2.toList().run(10_000, 1).twoMostActiveMnkeysInspectedMultiplied()

    private fun List<Monkey>.twoMostActiveMnkeysInspectedMultiplied() =
        map { it.inspected.toLong() }.sortedByDescending { it }.take(2).reduce { a, b -> a * b }

    private fun List<Monkey>.run(times: Int, relief: Int): List<Monkey> {
        val monkeys = this
        val lcm = this.map { it.test.toLong() }.let { divisors->
            lcm(divisors[0], divisors[1], divisors.drop(2))
        }
        repeat(times) { _ ->
            for (monkey in monkeys) {
                with(monkey) {
                    while (true) {
                        val worry = items.removeFirstOrNull() ?: break
                        inspected++
                        val opWorry = op(worry)
                        val reducedWorry = if (relief > 1) opWorry / relief else opWorry
                        val modWorry = reducedWorry % lcm

                        val index = if (modWorry % test == 0L) throwTrue else throwFalse
                        monkeys[index].items.addLast(modWorry)
                    }
                }
            }
        }
        return monkeys
    }

    data class Monkey(
        val id: Int,
        val items: ArrayDeque<Long>,
        val op: (Long) -> Long,
        val test: Int,
        val throwTrue: Int,
        val throwFalse: Int,
        var inspected: Int = 0
    ) {

        companion object {
            fun from(it: List<String>): Monkey {
                assert(it.size == 6)
                val id = it[0].substringAfter("Monkey ").substringBefore(":").toInt()
                val items: ArrayDeque<Long> =
                    it[1].substringAfter("Starting items: ").split(", ").map { it.toLong() }.let {
                        ArrayDeque(it.toList())
                    }
                val stringOp = it[2].substringAfter("Operation: new = ")
                val op: (Long) -> Long = when {
                    stringOp.startsWith("old * old") -> { a -> a * a }
                    stringOp.startsWith("old * ") -> { a -> stringOp.substringAfter("old * ").toLong() * a }
                    stringOp.startsWith("old + ") -> { a -> stringOp.substringAfter("old + ").toLong() + a }
                    else -> error("Input?: $stringOp")
                }
                val test = it[3].substringAfter("Test: divisible by ").toInt()
                val throwTrue = it[4].substringAfter("If true: throw to monkey ").toInt()
                val throwFalse = it[5].substringAfter("If false: throw to monkey ").toInt()
                return Monkey(id, items, op, test, throwTrue, throwFalse)
            }
        }
    }
}
