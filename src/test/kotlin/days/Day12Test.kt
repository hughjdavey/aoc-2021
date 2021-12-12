package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.jupiter.api.Test

class Day12Test {

    private val dayTwelve = Day12()

    private val smallExample = listOf(
        "start-A",
        "start-b",
        "A-c",
        "A-b",
        "b-d",
        "A-end",
        "b-end"
    )

    private val largerExample = listOf(
        "dc-end",
        "HN-start",
        "start-kj",
        "dc-start",
        "dc-HN",
        "LN-dc",
        "HN-end",
        "kj-sa",
        "kj-HN",
        "kj-dc"
    )

    @Test
    fun testPartOne() {
        assertThat(dayTwelve.partOne(), `is`(226))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwelve.partTwo(), `is`(3509))
    }

    @Test
    fun testParseInput() {
        val parsed = dayTwelve.parseInput(smallExample)
        assertThat(parsed, notNullValue())
        assertThat(parsed.entries.map { it.key to it.value }, containsInAnyOrder(
            "start" to listOf("A", "b"),
            "A" to listOf("start", "c", "b", "end"),
            "b" to listOf("start", "A", "d", "end"),
            "c" to listOf("A"),
            "d" to listOf("b"),
            "end" to listOf("A", "b"),
        ))
    }

    @Test
    fun testPartOneSmallExample() {
        val caveMap = dayTwelve.parseInput(smallExample)
        val paths = dayTwelve.getPaths(caveMap, true)
        assertThat(paths, notNullValue())
        assertThat(paths, hasSize(10))
    }

    @Test
    fun testPartOneLargerExample() {
        val caveMap = dayTwelve.parseInput(largerExample)
        val paths = dayTwelve.getPaths(caveMap, true)
        assertThat(paths, notNullValue())
        assertThat(paths, hasSize(19))
    }

    @Test
    fun testPartTwoSmallExample() {
        val caveMap = dayTwelve.parseInput(smallExample)
        val paths = dayTwelve.getPaths(caveMap, false)
        assertThat(paths, notNullValue())
        assertThat(paths, hasSize(36))
    }

    @Test
    fun testPartTwoLargerExample() {
        val caveMap = dayTwelve.parseInput(largerExample)
        val paths = dayTwelve.getPaths(caveMap, false)
        assertThat(paths, notNullValue())
        assertThat(paths, hasSize(103))
    }
}
