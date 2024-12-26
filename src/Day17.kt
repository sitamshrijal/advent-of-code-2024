import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): String {
        val computer = Computer.parse(input)
        return computer.runProgram().joinToString(",")
    }

    fun part2(input: List<String>): String {
        return ""
    }

    val input = readInput("input17")
    part1(input).println()
    part2(input).println()
}

data class Computer(
    var registerA: Int,
    var registerB: Int,
    var registerC: Int,
    val program: List<Int>
) {
    var instructionPointer = 0

    val output = mutableListOf<Int>()

    companion object {
        fun parse(input: List<String>): Computer {
            val registerA = input[0].substringAfter("Register A: ").toInt()
            val registerB = input[1].substringAfter("Register B: ").toInt()
            val registerC = input[2].substringAfter("Register C: ").toInt()
            val program = input[4].substringAfter("Program: ").split(",").map { it.toInt() }
            return Computer(registerA, registerB, registerC, program)
        }
    }

    fun runProgram(): List<Int> {
        var executed = true
        while (executed) {
            executed = runInstruction()
        }
        return output
    }

    /**
     * Run the instruction pointed to by the current value of [instructionPointer].
     */
    fun runInstruction(): Boolean {
        return if (instructionPointer > program.lastIndex) {
            false
        } else {
            val opcode = program[instructionPointer]
            val operand = program[instructionPointer + 1]
            val value = operand.toValue()
            when (opcode) {
                0 -> {
                    registerA = registerA / 2.0.pow(value).toInt()
                    instructionPointer += 2
                }

                1 -> {
                    registerB = registerB xor operand
                    instructionPointer += 2
                }

                2 -> {
                    registerB = value % 8
                    instructionPointer += 2
                }

                3 -> {
                    instructionPointer = if (registerA == 0) {
                        instructionPointer + 2
                    } else {
                        value
                    }
                }

                4 -> {
                    registerB = registerB xor registerC
                    instructionPointer += 2
                }

                5 -> {
                    output += value % 8
                    instructionPointer += 2
                }

                6 -> {
                    registerB = registerA / (2.0.pow(value)).toInt()
                    instructionPointer += 2
                }

                7 -> {
                    registerC = registerA / (2.0.pow(value)).toInt()
                    instructionPointer += 2
                }
            }
            true
        }
    }

    /**
     * Convert a combo operand to its value.
     */
    fun Int.toValue(): Int = when (this) {
        in 0..3 -> this
        4 -> registerA
        5 -> registerB
        6 -> registerC
        else -> this
    }
}
