import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val leftNumbers = mutableListOf<Int>()
        val rightNumbers = mutableListOf<Int>()

        input.forEach { line ->
            val splits = line.split("   ")
            val left = splits[0].toInt()
            val right = splits[1].toInt()
            leftNumbers += left
            rightNumbers += right
        }

        val zipped = leftNumbers.sorted() zip rightNumbers.sorted()

        return zipped.sumOf { (left, right) ->
            (left - right).absoluteValue
        }
    }

    fun part2(input: List<String>): Int {
        val leftNumbers = mutableListOf<Int>()
        val rightNumbers = mutableListOf<Int>()
        input.forEach { line ->
            val splits = line.split("   ")
            val left = splits[0].toInt()
            val right = splits[1].toInt()
            leftNumbers += left
            rightNumbers += right
        }

        return leftNumbers.sumOf { number ->
            val count = rightNumbers.count { it == number }
            val similarityScore = number * count
            similarityScore
        }
    }

    val input = readInput("input1")
    part1(input).println()
    part2(input).println()
}
