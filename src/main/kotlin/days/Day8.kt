package days

class Day8 : Day(8) {

    override fun partOne(): Any {
        val outputValues = inputList.map { it.split(" | ")[1] }.flatMap { it.split(" ") }
        return outputValues.count { listOf(2, 3, 4, 7).contains(it.length) }
    }

    override fun partTwo(): Any {
        val signals = inputList.map { it.split(" | ").map { it.split(" ") } }.map { Signal(it[0], it[1]) }
        return signals.fold(0) { sum, signal -> sum + signal.deduce() }
    }

    data class Signal(val inputs: List<String>, val outputs: List<String>) {

        private val digits: MutableMap<Int, String> = (0..9).associateWith { "" }.toMutableMap()

        fun deduce(): Int {
            deduceEasyDigits()

            // 0, 6 and 9 have 6 segments; 2, 3 and 5 have 5
            val zeroSixNine = inputs.filter { it.length == 6 }.filterNot(digits.values::contains)
            val twoThreeFive = inputs.filter { it.length == 5 }.filterNot(digits.values::contains)

            // 9 shares all its segments with 4
            val nine = zeroSixNine.find { d -> digits[4]!!.all { d.contains(it) } }!!
            digits[9] = nine

            // 0 has both of 1s segments whereas 6 does not
            val zero = zeroSixNine.filterNot { it == nine }.find { d -> digits[1]!!.all { d.contains(it) } }!!
            digits[0] = zero
            digits[6] = zeroSixNine.first { it != zero && it != nine }

            // 3 is the only one of 2, 3 and 5 that has both of 1s segments
            val three = twoThreeFive.find { d -> digits[1]!!.all { d.contains(it) } }!!
            digits[3] = three

            // 5 has 3 of 4s segments whereas 2 only has 2
            val five = twoThreeFive.filterNot { it == three }.first { d -> digits[4]!!.count { d.contains(it) } == 3 }
            digits[5] = five
            digits[2] = twoThreeFive.first { it != three && it != five }

            val outs = outputs.map { o -> digits.entries.find { it.value.toCharArray().sorted() == o.toCharArray().sorted() }!!.key }
            return outs.joinToString("").toInt()
        }

        private fun deduceEasyDigits() {
            for (input in inputs) {
                when (input.length) {
                    2 -> { digits[1] = input }
                    3 -> { digits[7] = input }
                    4 -> { digits[4] = input }
                    7 -> { digits[8] = input }
                }
            }
        }
    }
}
