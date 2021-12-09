package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day9Test {

    private val day = Day9()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(15))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(1134))
    }
}
