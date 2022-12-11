package days

@AdventOfCodePuzzle(
    name = "Monkey in the Middle",
    url = "https://adventofcode.com/2022/day/11",
    date = Date(day = 11, year = 2022)
)
class Day11(val input: String) : Puzzle {

    private val monkeys1 = input.split("\n\n").map { Monkey.from(it.lines()) }
    private val monkeys2 = input.split("\n\n").map { Monkey.from(it.lines()) }

    override fun partOne(): Int {
        monkeys1.toList().run(20, 3)
        return monkeys1.map { it.inspected }.sortedByDescending { it }.take(2).also { println(it) }.let { it[0] * it[1] }
    }

    override fun partTwo(): Long {
        monkeys2.toList().run(10_000, 1)
        return monkeys2.map { it.inspected.toLong() }.sortedByDescending { it }.take(2).also { println(it) }
            .let { it[0] * it[1] }
    }

    private fun List<Monkey>.run(times: Int, i: Int) {
        val map = this.map { it.test }
        val lcm2 = lcm(map[0].toLong(), map[1].toLong(), map.drop(2).map { it.toLong() })
        println("lcm2 = $lcm2")

        repeat(times) { round ->
            for (monkey in this) {
                //println("Monkey ${monkey.id}:")
                while (true) {
                    val item = monkey.items.removeFirstOrNull() ?: break
                    monkey.inspected++
                    //println("  Monkey inspects an item with a worry level of $item")
                    val opItem = monkey.op(item)
                    val reducedItem = if (i > 1) opItem / i else opItem
                    val modItem: Long = reducedItem.rem(lcm2)

                    //println("    Monkey gets bored with item. Worry level is divided by 3 to $itemNew.")
                    val index = if (modItem % monkey.test == 0L) monkey.throwTrue else monkey.throwFalse
                    //assert(index in 0..7)
                    this[index].items.addLast(modItem)
                }
            }
            if (round in listOf(0, 19, 999, 1999, 2999, 3999, 4999, 5999, 6999, 7999, 8999, 9999)) {
                println("== After round ${round + 1} ==")
                this.forEach { monkey -> println("Monkey ${monkey.id} inspected items ${monkey.inspected} times.") }
                println()
            }
        }
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
