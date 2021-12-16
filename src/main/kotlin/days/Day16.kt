package days

class Day16 : Day(16) {

    override fun partOne(): Any {
        return 0
    }

    override fun partTwo(): Any {
        return 0
    }

    fun parsePacket(hexString: String): Packet {
        val binString = hexString.toInt(16).toString(2)
        return Packet(binString.substring(0, 3).toInt(2), binString.substring(3, 6).toInt(2), binString.substring(6))
    }

    data class Packet(val version: Int, val typeId: Int, val content: String) {

        fun getValue(): Int {
            if (typeId == 4) {
                val number = content.chunked(5).fold("") { groups, group ->
                    groups + group.drop(1) + (if (group.startsWith('0')) ';' else "")
                }.takeWhile { it != ';' }
                System.err.println(number)
                System.err.println(number.toInt(2))
                return number.toInt(2)
            }
            return 0
        }
    }
}
