fun main() {
    fun part1(input: List<String>): Int {
        val wordSearch = mutableListOf<Position>()

        input.forEachIndexed { i, row ->
            row.forEachIndexed { j, c ->
                wordSearch += Position(c, j, i)
            }
        }

        val xPositions = wordSearch.filter { it.char == 'X' }

        var count = 0
        xPositions.forEach { xPosition ->
            for (direction in WordSearchDirection.entries) {
                val (dx, dy) = direction.dx to direction.dy
                var next3Chars = ""
                repeat(3) {
                    val (x, y) = xPosition.x + dx * (it + 1) to xPosition.y + dy * (it + 1)
                    val nextChar = wordSearch.find { it.x == x && it.y == y }
                    if (nextChar != null) {
                        next3Chars += nextChar.char
                    }
                }
                if (next3Chars == "MAS") {
                    count += 1
                }
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("input4")
    part1(input).println()
    part2(input).println()
}

enum class WordSearchDirection(val dx: Int, val dy: Int) {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    DIAGONAL_1(-1, -1),
    DIAGONAL_2(1, -1),
    DIAGONAL_3(-1, 1),
    DIAGONAL_4(1, 1)
}
