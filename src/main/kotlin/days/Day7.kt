package days

@AdventOfCodePuzzle(
    name = "",
    url = "https://adventofcode.com/2022/day/7",
    date = Date(day = 7, year = 2022)
)
class Day7(val input: List<String>) : Puzzle {
    /*
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst

     */

    override fun partOne(): Int {
        val map = mutableMapOf<String, Int>()
        var current = ""
        input.forEach() { line ->
            when {
                line.startsWith("$ cd ") -> {
                    val dir = line.substringAfter("$ cd ")
                    println("dir = ${dir}")
                    current = when (dir) {
                        "/" -> "/"
                        ".." -> current.substringBeforeLast('/')
                        else -> if (current == "/") "$current$dir" else "$current/$dir"
                    }
                }

                line.substringBefore(' ').all { it.isDigit() } -> {
                    val filesize = line.substringBefore(' ').toInt()
                    println("filezize = ${filesize}")
                    map.putIfAbsent(current, 0) //{ a, b -> a + b }
                    map.replaceAll { key, v -> if (current.startsWith(key)) v + filesize else v }

                    map.filterKeys { current.startsWith(it) } .forEach{println("${it.key} ${it.value}")}
                }
            }
        }

        //map.forEach { (k, v) -> println("$k $v") }
        return map.values.filter { it <= 100_000 }.sum()
    }

    override fun partTwo(): Int = 0

}
