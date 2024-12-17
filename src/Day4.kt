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
        val wordSearch = mutableListOf<Position>()

        input.forEachIndexed { i, row ->
            row.forEachIndexed { j, c ->
                wordSearch += Position(c, j, i)
            }
        }

        val aPositions = wordSearch.filter { it.char == 'A' }

        val diagonals = listOf(
            WordSearchDirection.DIAGONAL_1,
            WordSearchDirection.DIAGONAL_2,
            WordSearchDirection.DIAGONAL_3,
            WordSearchDirection.DIAGONAL_4
        )

        var count = 0
        aPositions.forEach { aPosition ->
            var diagonalChars = ""
            for (direction in diagonals) {
                val (dx, dy) = direction.dx to direction.dy
                val (x, y) = aPosition.x + dx to aPosition.y + dy
                val nextChar = wordSearch.find { it.x == x && it.y == y }
                if (nextChar != null) {
                    diagonalChars += nextChar.char
                }
            }
            if (diagonalChars in setOf("MMSS", "MSMS", "SSMM", "SMSM")) {
                count += 1
            }
        }
        return count
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
