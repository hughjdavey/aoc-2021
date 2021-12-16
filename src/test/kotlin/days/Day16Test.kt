package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day16Test {

    private val daySixteen = Day16()

    @Test
    fun testPartOne() {
        assertThat(daySixteen.partOne(), `is`(0))
    }

    @Test
    fun testPartTwo() {
        assertThat(daySixteen.partTwo(), `is`(0))
    }

    @Test
    fun testParsePacket() {
        val packet = daySixteen.parsePacket("D2FE28")
        System.err.println(packet)
        assertThat(packet.version, `is`(6))
        assertThat(packet.typeId, `is`(4))
        assertThat(packet.content, `is`("101111111000101000"))
        assertThat(packet.getValue(), `is`(2021))
    }
}
