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
        return input.length
    }

    val input = Path("src/input3.txt").readText()
    part1(input).println()
    part2(input).println()
}
