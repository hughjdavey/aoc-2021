package days

import java.math.BigInteger

class Day6 : Day(6) {

    private val fish = inputString.split(",").map { it.replace(Regex("\\s+"), "").toInt() }

    override fun partOne(): Any {
        return slowWay(80)
    }

    override fun partTwo(): Any {
        return fastWay(256)
    }

    private fun slowWay(daysToSimulate: Int): Int {
        return generateSequence(0 to fish) { (day, state) ->
            day + 1 to state.map { if (it == 0) 6 else it - 1 }.plus(List(state.count { it == 0 }) { 8 })
        }.find { it.first == daysToSimulate }?.second?.size ?: 0
    }

    private fun fastWay(daysToSimulate: Int): Long {
        var fishMap = (0..8).associateWith { daysToLive -> BigInteger.valueOf(fish.count { it == daysToLive }.toLong()) }
        (0 until daysToSimulate).forEach { fishMap = simulateDay(fishMap) }
        return fishMap.values.reduce { a, e -> a.add(e) }.toLong()
    }

    private fun simulateDay(prevDay: Map<Int, BigInteger>): Map<Int, BigInteger> {
        val currDay = (0..8).associateWith { BigInteger.ZERO }.toMutableMap()
        (0..8).forEach { daysToLive ->
            val prevNum = prevDay[daysToLive]!!
            if (daysToLive == 0) {
                currDay[6] = prevNum
                currDay[8] = prevNum
            }
            else {
                currDay[daysToLive - 1] = currDay[daysToLive - 1]!! + prevNum
            }
        }
        return currDay
    }
}
