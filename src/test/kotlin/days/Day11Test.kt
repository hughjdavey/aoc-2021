package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test
import util.Utils

class Day11Test {

    private val dayEleven = Day11()

    @Test
    fun testPartOne() {
        assertThat(dayEleven.partOne(), `is`(1656))
    }

    @Test
    fun testPartOneSmallerExample() {
        val octos = listOf("11111", "19991", "19191", "19991", "11111").flatMapIndexed { y, s -> s.mapIndexed { x, c -> Day11.Octopus(c.toString().toInt(), Utils.Coord(x, y)) } }
        assertThat(Day11.toString(octos), `is`("""
            11111
            19991
            19191
            19991
            11111
        """.trimIndent()))

        Day11.step(octos)
        assertThat(Day11.toString(octos), `is`("""
            34543
            40004
            50005
            40004
            34543
        """.trimIndent()))

        Day11.step(octos)
        assertThat(Day11.toString(octos), `is`("""
            45654
            51115
            61116
            51115
            45654
        """.trimIndent()))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayEleven.partTwo(), `is`(195))
    }
}
