package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder
import org.hamcrest.core.Is.`is`
import org.hamcrest.text.CharSequenceLength.hasLength
import org.junit.jupiter.api.Test

class Day14Test {

    private val dayFourteen = Day14()

    @Test
    fun testPartOne() {
        assertThat(dayFourteen.partOne(), `is`(1588))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFourteen.partTwo(), `is`(2188189693529))
    }

    @Test
    fun testPairInsertionNaiveMethod() {
        assertThat(dayFourteen.pairInsertion(1), `is`("NCNBCHB"))
        assertThat(dayFourteen.pairInsertion(2), `is`("NBCCNBBBCBHCB"))
        assertThat(dayFourteen.pairInsertion(3), `is`("NBBBCNCCNBBNBNBBCHBHHBCHB"))
        assertThat(dayFourteen.pairInsertion(4), `is`("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"))
        assertThat(dayFourteen.pairInsertion(5), hasLength(97))
        assertThat(dayFourteen.pairInsertion(10), hasLength(3073))
    }

    @Test
    fun testToPairsMap() {
        assertThat(dayFourteen.toPolymerMap("NNCB").map { it.key to it.value },
            containsInAnyOrder("NN" to 1L, "NC" to 1L, "CB" to 1L, "N" to 2L, "C" to 1L, "B" to 1L))

        assertThat(dayFourteen.toPolymerMap("NNNN").map { it.key to it.value },
            containsInAnyOrder("NN" to 3L, "N" to 4L))
    }

    @Test
    fun testPairInsertionMapWithPairs() {
        assertThat(dayFourteen.pairInsertionMap(1).map { it.key to it.value }.filter { it.first.length == 2 && it.second > 0 }, containsInAnyOrder(
            *dayFourteen.toPolymerMap("NCNBCHB").filterKeys { it.length == 2 }.map { it.key to it.value }.toTypedArray()
        ))

        assertThat(dayFourteen.pairInsertionMap(2).map { it.key to it.value }.filter { it.first.length == 2 && it.second > 0 }, containsInAnyOrder(
            *dayFourteen.toPolymerMap("NBCCNBBBCBHCB").filterKeys { it.length == 2 }.map { it.key to it.value }.toTypedArray()
        ))

        assertThat(dayFourteen.pairInsertionMap(3).map { it.key to it.value }.filter { it.first.length == 2 && it.second > 0 }, containsInAnyOrder(
            *dayFourteen.toPolymerMap("NBBBCNCCNBBNBNBBCHBHHBCHB").filterKeys { it.length == 2 }.map { it.key to it.value }.toTypedArray()
        ))

        assertThat(dayFourteen.pairInsertionMap(4).map { it.key to it.value }.filter { it.first.length == 2 && it.second > 0 }, containsInAnyOrder(
            *dayFourteen.toPolymerMap("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB").filterKeys { it.length == 2 }.map { it.key to it.value }.toTypedArray()
        ))
    }

    @Test
    fun testPairInsertionMapWithIndividualLetters() {
        assertThat(dayFourteen.pairInsertionMap(1).map { it.key to it.value }.filter { it.first.length == 1 && it.second > 0 }, containsInAnyOrder(
            "N" to 2L, "C" to 2L, "B" to 2L, "H" to 1L
        ))

        assertThat(dayFourteen.pairInsertionMap(2).map { it.key to it.value }.filter { it.first.length == 1 && it.second > 0 }, containsInAnyOrder(
            "N" to 2L, "C" to 4L, "B" to 6L, "H" to 1L
        ))

        assertThat(dayFourteen.pairInsertionMap(3).map { it.key to it.value }.filter { it.first.length == 1 && it.second > 0 }, containsInAnyOrder(
            "N" to 5L, "C" to 5L, "B" to 11L, "H" to 4L
        ))

        assertThat(dayFourteen.pairInsertionMap(4).map { it.key to it.value }.filter { it.first.length == 1 && it.second > 0 }, containsInAnyOrder(
            "N" to 11L, "C" to 10L, "B" to 23L, "H" to 5L
        ))
    }
}
