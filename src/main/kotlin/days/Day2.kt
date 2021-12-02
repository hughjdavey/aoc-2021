package days

import days.Day2.SubmarineDirection.DOWN
import days.Day2.SubmarineDirection.FORWARD
import days.Day2.SubmarineDirection.UP

class Day2 : Day(2) {

    override fun partOne(): Any {
        return inputList.map { SubmarineCommand(it) }.fold(SubmarinePosition()) { position, (direction, amount) ->
            when (direction) {
                FORWARD -> position.copy(h = position.h + amount)
                DOWN -> position.copy(d = position.d + amount)
                UP -> position.copy(d = position.d - amount)
            }
        }.getPosition()
    }

    override fun partTwo(): Any {
        return inputList.map { SubmarineCommand(it) }.fold(SubmarinePosition()) { position, (direction, amount) ->
            when (direction) {
                FORWARD -> position.copy(h = position.h + amount, d = position.d + (position.a * amount))
                DOWN -> position.copy(a = position.a + amount)
                UP -> position.copy(a = position.a - amount)
            }
        }.getPosition()
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
