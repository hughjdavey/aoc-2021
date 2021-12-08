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

        // 1 = 2 seg
        // 4 = 4 seg
        // 7 = 3 seg
        // 8 = 7 seg

        // 0, 6, 9 = 6 seg
        // 2, 3, 5 = 5 seg


        fun deduce(): Int {
            // easy way
            for (input in inputs) {
                when (input.length) {
                    2 -> { digits[1] = input }
                    3 -> { digits[7] = input }
                    4 -> { digits[4] = input }
                    7 -> { digits[8] = input }
                }
            }

            val zeroThreeNine = inputs.filter { input -> digits[1]!!.all(input::contains) }.filterNot(digits.values::contains)
            val zeroSixNine = inputs.filter { it.length == 6 }.filterNot(digits.values::contains)
            val three = zeroThreeNine.filterNot(zeroSixNine::contains).first()
            val six = zeroSixNine.filterNot(zeroThreeNine::contains).first()
            digits[3] = three
            digits[6] = six

            val twoThreeFive = inputs.filter { it.length == 5 }.filterNot(digits.values::contains)
            val twoFive = twoThreeFive.filterNot { it == digits[3] }
            val five = twoFive.first { d -> digits[4]!!.count { d.contains(it) } == 3 }
            val two = twoFive.filterNot { it == five }.first()
            digits[2] = two
            digits[5] = five

            val nine = zeroThreeNine.find { d -> digits[4]!!.all { d.contains(it) } }!!
            val zero = zeroThreeNine.filterNot { it == nine || it == three }.first()
            digits[9] = nine
            digits[0] = zero

            val outs = outputs.map { o -> digits.entries.find { it.value.toCharArray().sorted() == o.toCharArray().sorted() }!!.key }
            return outs.joinToString("").toInt()
        }
    }
}
