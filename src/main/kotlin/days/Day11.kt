package days

import util.Utils

class Day11 : Day(11) {

    private fun octos() = inputList.flatMapIndexed { y, s -> s.mapIndexed { x, c -> Octopus(c.toString().toInt(), Utils.Coord(x, y)) } }

    override fun partOne(): Any {
        return (1..100).fold(0 to octos()) { (flashes, octos), _ ->
            flashes + step(octos) to octos }.first
    }

    override fun partTwo(): Any {
        return generateSequence(1 to octos()) { (step, octos) -> step(octos); step + 1 to octos }
            .takeWhile { (step, octos) -> octos.any { it.energy != 0 } }.last().first
    }

    data class Octopus(var energy: Int, val coord: Utils.Coord, var hasFlashed: Boolean = false)

    companion object {

        // todo make this functional/immutable
        fun step(octos: List<Octopus>): Int {
            var flashes = 0
            octos.forEach { it.energy++ }
            while (octos.any { it.energy > 9 && !it.hasFlashed }) {
                val flashing = octos.filter { it.energy > 9 && !it.hasFlashed }
                flashes += flashing.size
                val adjacent = flashing.flatMap { it.coord.getAdjacent(true) }.mapNotNull { octos.find { o -> o.coord == it } }
                adjacent.forEach { it.energy++ }
                flashing.forEach { it.hasFlashed = true }
            }
            octos.forEach { if (it.hasFlashed) { it.energy = 0; it.hasFlashed = false } }
            return flashes
        }

        fun toString(octos: List<Octopus>): String {
            val len = octos.maxOf { it.coord.x + 1 }
            return octos.chunked(len).joinToString("\n") { it.joinToString("") { it.energy.toString() } }
        }
    }
}
