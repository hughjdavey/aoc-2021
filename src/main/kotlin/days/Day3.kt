package days

import util.Utils.map
import util.Utils.mul

class Day3 : Day(3) {

    private val bitLists = inputList.map { it.toCharArray().map { it.toString().toInt() } }

    override fun partOne(): Any {
        return bitLists
            .reduce { sums, list -> sums.mapIndexed { index, i -> i + list[index] } }
            .map { sum -> sum > inputList.size / 2 }
            .fold("" to "") { (g, e), oneIsMostCommon -> if (oneIsMostCommon) g + 1 to e + 0 else g + 0 to e + 1 }
            .map { it.toInt(2) }
            .mul()
    }

    override fun partTwo(): Any {
        return Pair(getRating(1), getRating(0))
            .map { it.first().joinToString("").toInt(2) }
            .mul()
    }

    // todo make this functional/immutable
    private fun getRating(keepIfEqual: Int): List<List<Int>> {
        var bit = 0
        var bits = bitLists
        while (bits.size > 1) {
            val ones = bits.map { it[bit] }.count { it == 1 }
            val zeroes = bits.size - ones

            bits = if (ones == zeroes) {
                bits.filter { it[bit] == keepIfEqual }
            }
            else {
                val mostCommon = if (ones > zeroes) 1 else 0
                bits.filter { if (keepIfEqual == 1) it[bit] == mostCommon else it[bit] != mostCommon }
            }
            bit++
        }
        return bits
    }
}
