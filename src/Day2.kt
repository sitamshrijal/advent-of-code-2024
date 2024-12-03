import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val reports = input.map { line ->
            val splits = line.split(" ")
            splits.map { it.toInt() }
        }
        return reports.count { report ->
            report.isSafe()
        }
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { line ->
            val splits = line.split(" ")
            splits.map { it.toInt() }
        }
        return reports.count { report ->
            report.isSafeWithDampener()
        }
    }

    val input = readInput("input2")
    part1(input).println()
    part2(input).println()
}

fun List<Int>.isSafe(): Boolean {
    val zipped = zipWithNext()

    val allIncreasing = zipped.all { (first, second) -> first < second }
    val allDecreasing = zipped.all { (first, second) -> first > second }
    val diffs = zipped.map { (first, second) -> (first - second).absoluteValue }

    return (allIncreasing || allDecreasing) && diffs.all { it in 1..3 }
}

fun List<Int>.isSafeWithDampener(): Boolean = indices.any { toRemove ->
    filterIndexed() { index, _ -> index != toRemove }.isSafe()
}
