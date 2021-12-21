package days

import util.Utils.takeWhileInclusive

class Day16 : Day(16) {

    override fun partOne(): Any {
        return sumVersionNumbers(parse(hex2Bin(inputString.trim())))
    }

    override fun partTwo(): Any {
        return calculatePacketExpression(parse(hex2Bin(inputString.trim())))
    }

    fun sumVersionNumbers(packet: Packet, sum: Int = 0): Int {
        if (packet.subPackets.isEmpty()) {
            return packet.version + sum
        }
        return packet.subPackets.fold(packet.version + sum) { s, p -> s + sumVersionNumbers(p) }
    }

    fun calculatePacketExpression(packet: Packet, sum: Long = 0): Long {
        if (packet.typeId == 4) {
            return sum + packet.literalValue
        }
        return sum + when (packet.typeId) {
            0 -> packet.subPackets.fold(0) { acc, sp -> acc + calculatePacketExpression(sp) }
            1 -> packet.subPackets.fold(1) { acc, sp -> acc * calculatePacketExpression(sp) }
            2 -> packet.subPackets.minOf { sp -> calculatePacketExpression(sp) }
            3 -> packet.subPackets.maxOf { sp -> calculatePacketExpression(sp) }
            5 -> if (calculatePacketExpression(packet.subPackets[0]) > calculatePacketExpression(packet.subPackets[1])) 1 else 0
            6 -> if (calculatePacketExpression(packet.subPackets[0]) < calculatePacketExpression(packet.subPackets[1])) 1 else 0
            7 -> if (calculatePacketExpression(packet.subPackets[0]) == calculatePacketExpression(packet.subPackets[1])) 1 else 0
            else -> 0
        }
    }

    fun parse(s: String): Packet {
        return getNextPacket(s).first
    }

    fun hex2Bin(hex: String): String {
        return hex.map { it.digitToInt(16).toString(2).padStart(4, '0') }.joinToString("")
    }

    private fun getNextPacket(s: String): Pair<Packet, String> {
        val (version, typeId) = s.substring(0, 3).toInt(2) to s.substring(3, 6).toInt(2)
        if (typeId == 4) {
            val remainder = s.substring(6)
            val content = remainder.chunked(5).takeWhileInclusive { it.startsWith('1') }.joinToString("")
            return Packet(version, typeId, content = content) to remainder.drop(content.length)
        }

        val lengthTypeId = s[6]
        return if (lengthTypeId == '0') {
            val subPacketsLength = s.substring(7, 22).toInt(2)
            val subPacketsAndRemainder = s.substring(22)
            val (extracted, remainder) = extractPacketsUntil(subPacketsAndRemainder) { length, _ -> length > subPacketsAndRemainder.length - subPacketsLength }
            Packet(version, typeId, subPackets = extracted.toMutableList()) to remainder
        }
        else {
            val subPacketsCount = s.substring(7, 18).toInt(2)
            val subPacketsAndRemainder = s.substring(18)
            val (extracted, remainder) = extractPacketsUntil(subPacketsAndRemainder) { _, count -> count < subPacketsCount }
            Packet(version, typeId, subPackets = extracted.toMutableList()) to remainder
        }
    }

    private fun extractPacketsUntil(s: String, condition: (len: Int, count: Int) -> Boolean): Pair<List<Packet>, String> {
        var counter = 0
        var remainder = s
        val extracted = mutableListOf<Packet>()
        while (condition(remainder.length, counter++)) {
            val (packet, rem) = getNextPacket(remainder)
            extracted += packet
            remainder = rem
        }
        return extracted to remainder
    }

    data class Packet(val version: Int, val typeId: Int, val content: String = "", val subPackets: MutableList<Packet> = mutableListOf(), val ty: Int = 2) {

        val literalValue: Long by lazy { content.chunked(5).joinToString("") { it.drop(1) }.toLong(2) }
    }
}
