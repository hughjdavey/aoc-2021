package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day4Test {

    private val dayFour = Day4()

    @Test
    fun testPartOne() {
        assertThat(dayFour.partOne(), `is`(4512))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFour.partTwo(), `is`(1924))
    }
}
