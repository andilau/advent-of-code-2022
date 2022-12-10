package days

@AdventOfCodePuzzle(
    name = "No Space Left On Device",
    url = "https://adventofcode.com/2022/day/7",
    date = Date(day = 7, year = 2022)
)
class Day7(val input: List<String>) : Puzzle {

    private val dirSizes = dirSizes()

    override fun partOne(): Int = dirSizes.values.filter { it <= 100_000 }.sum()

    override fun partTwo(): Int {
        val total = dirSizes.getValue("/")
        return dirSizes.values.filter { 70_000_000 - (total - it) >= 30_000_000 }.min()
    }

    private fun dirSizes(): Map<String, Int> {
        val map = mutableMapOf<String, Int>("/" to 0)
        var current = ""
        input.forEach { line ->
            when {
                line.startsWith("$ cd ") -> {
                    val dir = line.substringAfter("$ cd ")
                    current = when (dir) {
                        "/" -> "/"
                        ".." -> current.substringBeforeLast('/')
                        else -> {
                            val s = if (current == "/") "/$dir" else "$current/$dir"
                            map[s] = 0
                            s
                        }
                    }
                }

                line.substringBefore(' ').all { it.isDigit() } -> {
                    val fileSize = line.substringBefore(' ').toInt()
                    //map.putIfAbsent(current, 0)
                    map.replaceAll { key, totalSize -> if (current.startsWith(key)) totalSize + fileSize else totalSize }

                }
            }
        }
        return map
    }

}
