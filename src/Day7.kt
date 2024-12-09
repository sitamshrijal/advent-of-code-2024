fun main() {
    fun part1(input: List<String>): Long {
        val equations = input.map { Equation.parse(it) }

        var sum = 0L

        equations.forEach { (testValue, numbers) ->
            if (numbers.operate(numbers[0], 1, testValue)) {
                sum += testValue
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("input7")
    part1(input).println()
    part2(input).println()
}

data class Equation(val testValue: Long, val numbers: List<Long>) {
    companion object {
        fun parse(input: String): Equation {
            val testValue = input.substringBefore(":").toLong()
            val numbers = input.substringAfter(": ").split(" ").map { it.toLong() }

            return Equation(testValue, numbers)
        }
    }
}

fun List<Long>.operate(current: Long, index: Int, testValue: Long): Boolean {
    if (index >= size) {
        return current == testValue
    }

    if (operate(current + this[index], index + 1, testValue)) {
        return true
    }

    if (operate(current * this[index], index + 1, testValue)) {
        return true
    }
    return false
}
