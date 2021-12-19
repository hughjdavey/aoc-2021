package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day15Test {

    private val dayFifteen = Day15()

    @Test
    fun testPartOne() {
        assertThat(dayFifteen.partOne(), `is`(40))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFifteen.partTwo(), `is`(315))
    }
}
