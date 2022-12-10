package days

@AdventOfCodePuzzle(
    name = "",
    url = "https://adventofcode.com/2022/day/7",
    date = Date(day = 7, year = 2022)
)
class Day7(val input: List<String>) : Puzzle {

    override fun partOne(): Int = dirSizes().values.filter { it <= 100_000 }.sum()

    override fun partTwo(): Int = 0

    private fun dirSizes(): Map<String, Int> {
        val map = mutableMapOf<String, Int>()
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
                    val filesize = line.substringBefore(' ').toInt()
                    //map.putIfAbsent(current, 0)
                    map.replaceAll { key, v -> if (current.startsWith(key)) v + filesize else v }

                }
            }
        }
        return map
    }

}
