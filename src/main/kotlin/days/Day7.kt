package days

import kotlin.math.abs

class Day7 : Day(7) {

    private val crabs = inputString.split(",").map { it.replace(Regex("\\s+"), "").toInt() }

    override fun partOne(): Any {
        // simplification of calculateMinFuel { fuel, distance -> fuel + distance }
        return calculateMinFuel(Int::plus)
    }

    override fun partTwo(): Any {
        return calculateMinFuel { fuel, distance ->
            (0 until distance).fold(fuel to 1) { (fuelSpent, fuelCost), _ ->
                fuelSpent + fuelCost to fuelCost + 1
            }.first
        }
    }

    private fun calculateMinFuel(consumptionFunction: (fuel: Int, distance: Int) -> Int): Int {
        val (min, max) = crabs.minOf { it } to crabs.maxOf { it }
        return (min..max).map { alignPos ->
            crabs.fold(0) { fuel, pos -> consumptionFunction(fuel, abs(pos - alignPos)) }
        }.minOf { it }
    }
}
