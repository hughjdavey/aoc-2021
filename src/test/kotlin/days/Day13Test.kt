package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day13Test {

    private val dayThirteen = Day13()

    @Test
    fun testPartOne() {
        assertThat(dayThirteen.partOne(), `is`(17))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayThirteen.partTwo(), `is`("""
            
            #####
            #...#
            #...#
            #...#
            #####
            .....
            .....
            
        """.trimIndent()))
    }

    @Test
    fun testGetPaper() {
        assertThat(dayThirteen.getPaper(), `is`("""
            ...#..#..#.
            ....#......
            ...........
            #..........
            ...#....#.#
            ...........
            ...........
            ...........
            ...........
            ...........
            .#....#.##.
            ....#......
            ......#...#
            #..........
            #.#........
        """.trimIndent()))
    }

    @Test
    fun testFoldExamples() {
        val foldedOnce = dayThirteen.fold(listOf("y" to 7))
        assertThat(foldedOnce, `is`("""
            #.##..#..#.
            #...#......
            ......#...#
            #...#......
            .#.#..#.###
            ...........
            ...........
        """.trimIndent()))

        val foldedTwice = dayThirteen.fold(listOf("y" to 7, "x" to 5))
        assertThat(foldedTwice, `is`("""
            #####
            #...#
            #...#
            #...#
            #####
            .....
            .....
        """.trimIndent()))
    }
}
