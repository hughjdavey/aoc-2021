package days

import days.Day2.SubmarineDirection.DOWN
import days.Day2.SubmarineDirection.FORWARD
import days.Day2.SubmarineDirection.UP

class Day2 : Day(2) {

    private val submarine = Submarine(SubmarinePosition(), inputList)

    override fun partOne(): Any {
        return submarine.move(
            { position, amount -> position.copy(d = position.d - amount) },
            { position, amount -> position.copy(d = position.d + amount) },
            { position, amount -> position.copy(h = position.h + amount) }
        )
    }

    override fun partTwo(): Any {
        return submarine.move(
            { position, amount -> position.copy(a = position.a - amount) },
            { position, amount -> position.copy(a = position.a + amount) },
            { position, amount -> position.copy(h = position.h + amount, d = position.d + (position.a * amount)) }
        )
    }

    data class Submarine(val initialPosition: SubmarinePosition, val commands: List<String>) {

        fun move(onUp: SubmarineMovement, onDown: SubmarineMovement, onForward: SubmarineMovement): Int {
            return commands.map { SubmarineCommand(it) }.fold(initialPosition) { position, (direction, amount) ->
                when (direction) {
                    FORWARD -> onForward(position, amount)
                    DOWN -> onDown(position, amount)
                    UP -> onUp(position, amount)
                }
            }.getPosition()
        }
    }

    data class SubmarineCommand(val direction: SubmarineDirection, val amount: Int) {

        constructor(command: String) : this(
            SubmarineDirection.valueOf(command.takeWhile { it != ' ' }.uppercase()),
            command.takeLastWhile { it != ' ' }.toInt()
        )
    }

    enum class SubmarineDirection { FORWARD, DOWN, UP }

    data class SubmarinePosition(val h: Int = 0, val d: Int = 0, val a: Int = 0) {

        fun getPosition(): Int = h * d
    }
}

typealias SubmarineMovement = (p: Day2.SubmarinePosition, a: Int) -> Day2.SubmarinePosition
