package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day7Test {

    private val daySeven = Day7()

    @Test
    fun testPartOne() {
        assertThat(daySeven.partOne(), `is`(37))
    }

    @Test
    fun testPartTwo() {
        assertThat(daySeven.partTwo(), `is`(168))
    }
}
