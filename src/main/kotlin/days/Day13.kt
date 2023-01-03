package days

@AdventOfCodePuzzle(
    name = "Distress Signal",
    url = "https://adventofcode.com/2022/day/13",
    date = Date(day = 13, year = 2022)
)
class Day13(val input: List<String>) : Puzzle {

    private val pairs = input.filter(String::isNotBlank)
        .map { line -> Regex("""\[|]|\d+""").findAll(line).map { it.value }.toList() }
        .chunked(2)
        .map { Packet.from(it[0]) to Packet.from(it[1]) }

    override fun partOne(): Int {
        return pairs.mapIndexed { ix, p -> (ix + 1) * if (p.first < p.second) 1 else 0 }.sum()
    }

    override fun partTwo(): Int {
        return 0
    }

    sealed class Packet : Comparable<Packet> {

        class PacketNumber(val number: Int) : Packet() {
            override fun compareTo(other: Packet): Int = when (other) {
                is PacketNumber -> number.compareTo(other.number)
                is PacketList -> PacketList(listOf(this)).compareTo(other)
            }
        }

        class PacketList(val packets: List<Packet>) : Packet() {
            override fun compareTo(other: Packet): Int = when (other) {
                is PacketNumber -> this.compareTo(PacketList(listOf(other)))
                is PacketList -> packets.zip(other.packets)
                    .map { (first, second) -> first.compareTo(second) }
                    .firstOrNull { it != 0 }
                    ?: this.packets.size.compareTo(other.packets.size)
            }
        }

        companion object {
            fun from(line: List<String>): Packet {
                val packets = mutableListOf<Packet>()
                for (token in line) {
                    when (token) {
                        "]" -> return PacketList(packets)
                        "[" -> packets.add(from(line))
                        else -> packets.add(PacketNumber(token.toInt()))
                    }
                }
                return packets.first()
            }
        }
    }

}
