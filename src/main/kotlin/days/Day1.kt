package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        return inputList.map { it.toInt() }
            .windowed(2).count { it.last() > it.first() }
    }

    override fun partTwo(): Any {
        return inputList.map { it.toInt() }.windowed(3).map { it.sum() }
            .windowed(2).count { it.last() > it.first() }
    }
}
