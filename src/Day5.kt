fun main() {
    fun part1(input: List<String>): Int {
        val (rules, pages) = Instruction.parse(input)

        val comparator = Comparator<Int> { a, b ->
            when {
                a to b in rules -> -1
                b to a in rules -> 1
                else -> 0
            }
        }

        // Sort each page according to the rules
        val sortedPages = pages.map { it to it.sortedWith(comparator) }

        return sortedPages.filter { (page, sorted) -> page == sorted }
            .sumOf { (page) -> page[page.size / 2] }
    }

    fun part2(input: List<String>): Int {
        val (rules, pages) = Instruction.parse(input)

        val comparator = Comparator<Int> { a, b ->
            when {
                a to b in rules -> -1
                b to a in rules -> 1
                else -> 0
            }
        }

        // Sort each page according to the rules
        val sortedPages = pages.map { it to it.sortedWith(comparator) }

        return sortedPages.filterNot { (page, sorted) -> page == sorted }
            .sumOf { (_, sorted) -> sorted[sorted.size / 2] }
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
