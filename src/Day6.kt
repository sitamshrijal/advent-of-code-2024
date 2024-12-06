fun main() {
    fun part1(input: List<String>): Int {
        val map = mutableListOf<Position>()

        input.forEachIndexed { i, row ->
            row.forEachIndexed { j, c ->
                map += Position(c, j, i)
            }
        }

        val maxX = map.maxOf { it.x }
        val maxY = map.maxOf { it.y }

        var guard = map.find { it.char == '^' }!!

        // Initial direction: up
        var direction = Direction.UP

        val visited = mutableSetOf<Position>(guard)

        while (guard.x != 0 && guard.x != maxX && guard.y != 0 && guard.y != maxY) {
            val (x, y) = guard.x + direction.dx to guard.y + direction.dy
            val nextChar = map.find { it.x == x && it.y == y }!!.char

            // Encountered an obstacle
            if (nextChar == '#') {
                direction = when (direction) {
                    Direction.UP -> Direction.RIGHT
                    Direction.DOWN -> Direction.LEFT
                    Direction.LEFT -> Direction.UP
                    Direction.RIGHT -> Direction.DOWN
                }
            }
            guard = guard.move(direction)
            visited += guard
        }

        return visited.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("input6")
    part1(input).println()
    part2(input).println()
}

data class Position(val char: Char, val x: Int, val y: Int) {
    fun move(direction: Direction): Position = Position(char, x + direction.dx, y + direction.dy)
}

enum class Direction(val dx: Int, val dy: Int) {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0)
}
