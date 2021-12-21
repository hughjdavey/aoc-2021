package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.hamcrest.collection.IsEmptyCollection.empty
import org.hamcrest.core.Is.`is`
import org.hamcrest.text.IsEmptyString.emptyString
import org.junit.jupiter.api.Test

class Day16Test {

    private val daySixteen = Day16()

    @Test
    fun testPartOne() {
        assertThat(daySixteen.partOne(), `is`(31))
    }

    @Test
    fun testPartTwo() {
        assertThat(daySixteen.partTwo(), `is`(54L))
    }

    @Test
    fun testHexToBin() {
        assertThat(daySixteen.hex2Bin("D2FE28"), `is`("110100101111111000101000"))
        assertThat(daySixteen.hex2Bin("38006F45291200"), `is`("00111000000000000110111101000101001010010001001000000000"))
        assertThat(daySixteen.hex2Bin("EE00D40C823060"), `is`("11101110000000001101010000001100100000100011000001100000"))
    }

    @Test
    fun testParseLiteralPacket() {
        val packet = daySixteen.parse(daySixteen.hex2Bin("D2FE28"))
        assertThat(packet.version, `is`(6))
        assertThat(packet.typeId, `is`(4))
        assertThat(packet.content, `is`("101111111000101"))
        assertThat(packet.literalValue, `is`(2021))
        assertThat(packet.subPackets, empty())
    }

    @Test
    fun testParseOperatorPackets() {
        val p1 = daySixteen.parse(daySixteen.hex2Bin("38006F45291200"))
        assertThat(p1.version, `is`(1))
        assertThat(p1.typeId, `is`(6))
        assertThat(p1.content, emptyString())
        assertThat(p1.subPackets, hasSize(2))
        assertThat(p1.subPackets[0].literalValue, `is`(10))
        assertThat(p1.subPackets[1].literalValue, `is`(20))

        val p2 = daySixteen.parse(daySixteen.hex2Bin("EE00D40C823060"))
        assertThat(p2.version, `is`(7))
        assertThat(p2.typeId, `is`(3))
        assertThat(p2.content, emptyString())
        assertThat(p2.subPackets, hasSize(3))
        assertThat(p2.subPackets[0].literalValue, `is`(1))
        assertThat(p2.subPackets[1].literalValue, `is`(2))
        assertThat(p2.subPackets[2].literalValue, `is`(3))
    }

    @Test
    fun testVersionSums() {
        val p1 = daySixteen.parse(daySixteen.hex2Bin("8A004A801A8002F478"))
        assertThat(daySixteen.sumVersionNumbers(p1), `is`(16))

        val p2 = daySixteen.parse(daySixteen.hex2Bin("620080001611562C8802118E34"))
        assertThat(daySixteen.sumVersionNumbers(p2), `is`(12))

        val p3 = daySixteen.parse(daySixteen.hex2Bin("C0015000016115A2E0802F182340"))
        assertThat(daySixteen.sumVersionNumbers(p3), `is`(23))

        val p4 = daySixteen.parse(daySixteen.hex2Bin("A0016C880162017C3686B18A3D4780"))
        assertThat(daySixteen.sumVersionNumbers(p4), `is`(31))
    }

    @Test
    fun testCalculations() {
        assertThat(daySixteen.calculatePacketExpression(daySixteen.parse(daySixteen.hex2Bin("C200B40A82"))), `is`(3))
        assertThat(daySixteen.calculatePacketExpression(daySixteen.parse(daySixteen.hex2Bin("04005AC33890"))), `is`(54))
        assertThat(daySixteen.calculatePacketExpression(daySixteen.parse(daySixteen.hex2Bin("880086C3E88112"))), `is`(7))
        assertThat(daySixteen.calculatePacketExpression(daySixteen.parse(daySixteen.hex2Bin("CE00C43D881120"))), `is`(9))
        assertThat(daySixteen.calculatePacketExpression(daySixteen.parse(daySixteen.hex2Bin("D8005AC2A8F0"))), `is`(1))
        assertThat(daySixteen.calculatePacketExpression(daySixteen.parse(daySixteen.hex2Bin("F600BC2D8F"))), `is`(0))
        assertThat(daySixteen.calculatePacketExpression(daySixteen.parse(daySixteen.hex2Bin("9C005AC2F8F0"))), `is`(0))
        assertThat(daySixteen.calculatePacketExpression(daySixteen.parse(daySixteen.hex2Bin("9C0141080250320F1802104A08"))), `is`(1))
    }
}
