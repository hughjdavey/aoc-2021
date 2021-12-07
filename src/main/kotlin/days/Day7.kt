package days

import kotlin.math.abs

class Day7 : Day(7) {

    private val crabs = inputString.split(",").map { it.replace(Regex("\\s+"), "").toInt() }

    private val minPos = crabs.minOf { it }

    private val maxPos = crabs.maxOf { it }

    override fun partOne(): Any {
        return (minPos..maxPos).map { alignPos ->
            crabs.fold(0) { fuel, pos -> fuel + abs(pos - alignPos) }
        }.minOf { it }
    }

    override fun partTwo(): Any {
        return (minPos..maxPos).map { alignPos ->
            crabs.fold(0) { fuel, pos ->
                val fuelConsumed = abs(pos - alignPos)
                var cost = 1
                var realFuel = 0
                (0 until fuelConsumed).forEach {
                    realFuel += cost++
                }
                fuel + realFuel
            }
        }.minOf { it }
    }
}
