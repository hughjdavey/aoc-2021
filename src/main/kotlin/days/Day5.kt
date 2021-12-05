package days

import util.Utils
import kotlin.math.max
import kotlin.math.min

class Day5 : Day(5) {

    private val vents = inputList.flatMap { it.split(" -> ") }.flatMap { it.split(",") }
        .map { it.toInt() }.chunked(4).map { Vent(Utils.Coord(it[0], it[1]), Utils.Coord(it[2], it[3])) }

    override fun partOne(): Any {
        return atLeastTwoOverlaps(vents.filterNot(Vent::isDiagonal))
    }

    override fun partTwo(): Any {
        return atLeastTwoOverlaps(vents)
    }

    private fun atLeastTwoOverlaps(vents: List<Vent>): Int {
        return vents.flatMap(Vent::getPoints).fold(mutableMapOf<Utils.Coord, Int>()) { diagram, coord ->
            diagram.compute(coord) { k, v -> if (v == null) 1 else v + 1 }
            diagram
        }.count { it.value >= 2 }
    }

    data class Vent(val start: Utils.Coord, val end: Utils.Coord) {

        private fun isVertical() = start.x == end.x

        private fun isHorizontal() = start.y == end.y

        fun isDiagonal() = !isVertical() && !isHorizontal()

        fun getPoints(): List<Utils.Coord> {
            val (minX, minY, maxX, maxY) = listOf(min(start.x, end.x), min(start.y, end.y), max(start.x, end.x), max(start.y, end.y))
            val points = (minX..maxX).flatMap { xx -> (minY..maxY).map { yy -> Utils.Coord(xx, yy) } }
            return if (!isDiagonal()) points else {
                val sets = points.chunked(maxX - (minX - 1))
                val index = sets.first().indexOfFirst { it == start || it == end }
                (if (index == 0) sets else sets.reversed()).mapIndexed { idx, coords -> coords[idx] }
            }
        }
    }
}
