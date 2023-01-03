package days

@AdventOfCodePuzzle(
    name = "Distress Signal",
    url = "https://adventofcode.com/2022/day/13",
    date = Date(day = 13, year = 2022)
)
class Day13(val input: List<String>) : Puzzle {
    private val divider: List<String> = listOf("[[2]]", "[[6]]")

    private val pairs = input
        .asSequence().filter(String::isNotBlank)
        .map { Packet.from(it) }
        .chunked(2)
        .map { it[0] to it[1] }

    override fun partOne(): Int =
        pairs.mapIndexed { ix, p -> (ix + 1) * if (p.first < p.second) 1 else 0 }
            .sum()

    override fun partTwo(): Int {
        return (input + divider)
            .filter(String::isNotBlank)
            .map { Packet.from(it) }
            .sorted()
            .mapIndexed { index, packet ->
                if (packet.toString() in divider) index + 1 else 1
            }
            .reduce { a, b -> a * b }
    }


    sealed class Packet : Comparable<Packet> {

        class PacketNumber(val number: Int) : Packet() {
            override fun compareTo(other: Packet): Int = when (other) {
                is PacketNumber -> number.compareTo(other.number)
                is PacketList -> PacketList(listOf(this)).compareTo(other)
            }

            override fun toString(): String = "$number"
        }

        class PacketList(val packets: List<Packet>) : Packet() {
            override fun compareTo(other: Packet): Int = when (other) {
                is PacketNumber -> this.compareTo(PacketList(listOf(other)))
                is PacketList -> packets.zip(other.packets)
                    .map { (first, second) -> first.compareTo(second) }
                    .firstOrNull { it != 0 }
                    ?: this.packets.size.compareTo(other.packets.size)
            }

            override fun toString(): String =
                packets.joinToString(",", "[", "]")
        }

        companion object {
            fun from(line: String): Packet =
                Regex("""\[|]|\d+""").findAll(line).map { it.value }.toList().let { from(ArrayDeque(it)) }

            private fun from(tokens: ArrayDeque<String>): Packet {
                val packets = mutableListOf<Packet>()
                while (tokens.isNotEmpty()) {
                    when (val symbol = tokens.removeFirst()) {
                        "]" -> return PacketList(packets)
                        "[" -> packets.add(from(tokens))
                        else -> packets.add(PacketNumber(symbol.toInt()))
                    }
                }
                return packets.first()
            }
        }
    }
}
