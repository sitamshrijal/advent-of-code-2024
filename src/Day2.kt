import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val reports = input.map { line ->
            val splits = line.split(" ")
            splits.map { it.toInt() }
        }
        return reports.count { report ->
            val zipped = report.zipWithNext()

            val allIncreasing = zipped.all { (first, second) -> first < second }
            val allDecreasing = zipped.all { (first, second) -> first > second }
            val diffs = zipped.map { (first, second) -> (first - second).absoluteValue }

            (allIncreasing || allDecreasing) && diffs.all { it in 1..3 }
        }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("input2")
    part1(input).println()
    part2(input).println()
}
