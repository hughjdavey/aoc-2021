package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day8Test {

    private val dayEight = Day8()

    @Test
    fun testPartOne() {
        assertThat(dayEight.partOne(), `is`(26))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayEight.partTwo(), `is`(61229))
    }

    @Test
    fun testPartTwoSmallerExample() {
        val signal = Day8.Signal("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split(" "), "cdfeb fcadb cdfeb cdbaf".split(" "))
        assertThat(signal.deduce(), `is`(5353))
    }
}
