package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day6Test {

    private val daySix = Day6()

    @Test
    fun testPartOne() {
        assertThat(daySix.partOne(), `is`(5934))
    }

    @Test
    fun testPartTwo() {
        assertThat(daySix.partTwo(), `is`(26984457539))
    }
}
