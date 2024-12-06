fun main() {
    fun part1(input: List<String>): Int {
        val (rules, pages) = Instruction.parse(input)

        val correctOrderings = mutableListOf<List<Int>>()

        for (page in pages) {
            var correctOrdering = true
            val cartesianPairs = page.cartesianPairs()
            for (pair in cartesianPairs) {
                for (rule in rules) {
                    if (pair.first == rule.second && pair.second == rule.first) {
                        correctOrdering = false
                        break
                    }
                }
                if (!correctOrdering) {
                    break
                }
            }
            if (correctOrdering) {
                correctOrderings += page
            }
        }
        return correctOrderings.sumOf { numbers -> numbers[numbers.size / 2] }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("input5")
    part1(input).println()
    part2(input).println()
}

data class Instruction(val rules: List<Pair<Int, Int>>, val pages: List<List<Int>>) {
    companion object {
        fun parse(input: List<String>): Instruction {
            val index = input.indexOfFirst { line -> line.isEmpty() }
            val first = input.subList(0, index - 1)
            val second = input.subList(index + 1, input.size)

            val rules = buildList {
                first.forEach { rule ->
                    val (a, b) = rule.split("|")
                    add(a.toInt() to b.toInt())
                }
            }

            val pages = buildList {
                second.forEach { pages ->
                    val splits = pages.split(",")
                    add(splits.map { it.toInt() })
                }
            }
            return Instruction(rules, pages)
        }
    }
}

fun List<Int>.cartesianPairs(): List<Pair<Int, Int>> {
    val pairs = mutableListOf<Pair<Int, Int>>()
    for (i in indices) {
        for (j in i + 1..lastIndex) {
            pairs += this[i] to this[j]
        }
    }
    return pairs
}
