import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    fun part1(input: String): Int {
        val regex = """mul\((\d+),(\d+)\)""".toRegex()
        return regex.findAll(input)
            .sumOf { result ->
                val (a, b) = result.destructured
                a.toInt() * b.toInt()
            }
    }

    fun part2(input: String): Int {
        val regex = """mul\((\d+),(\d+)\)""".toRegex()
        return regex.findAll(input)
            .sumOf { result ->
                val substringBefore = input.substringBefore(result.value)
                val lastDo = substringBefore.lastIndexOf("do()")
                val lastDont = substringBefore.lastIndexOf("don't()")

                if (lastDo > lastDont || lastDo == -1) {
                    val (a, b) = result.destructured
                    a.toInt() * b.toInt()
                } else {
                    0
                }
            }
    }

    val input = Path("src/input3.txt").readText()
    part1(input).println()
    part2(input).println()
}
