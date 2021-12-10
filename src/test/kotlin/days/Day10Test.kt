package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day10Test {

    private val dayTen = Day10()

    @Test
    fun testPartOne() {
        assertThat(dayTen.partOne(), `is`(26397))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTen.partTwo(), `is`(288957L))
    }
}
