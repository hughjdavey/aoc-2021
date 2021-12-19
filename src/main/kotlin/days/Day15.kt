package days

import util.Utils
import java.util.PriorityQueue

class Day15 : Day(15) {

    private val floor = inputList.flatMapIndexed { y, row -> row.mapIndexed { x, c -> Position(Utils.Coord(x, y), c.digitToInt()) } }

    override fun partOne(): Any {
        return leastRiskyPath(floor)
    }

    override fun partTwo(): Any {
        val origX = inputList.first().length
        val origY = inputList.size
        val fullFloor = (0 until origY * 5).flatMap { y -> (0 until origX * 5).map { x ->
            val origRisk = inputList[y % origY][x % origX]
            val computedRisk = (origRisk.digitToInt() + x / origX + y / origY)
            Position(Utils.Coord(x, y), if (computedRisk < 10) computedRisk else computedRisk % 10 + 1)
        } }
        return leastRiskyPath(fullFloor)
    }

    data class Position(val coord: Utils.Coord, val risk: Int) {

        var distance: Int = Int.MAX_VALUE

        var prev: Position? = null
    }

    private fun leastRiskyPath(floor: List<Position>): Int {
        val floorArr = floor.chunked(floor.maxOf { it.coord.x } + 1).map { it.toTypedArray() }.toTypedArray()
        val maxX = floorArr.first().size
        val maxY = floorArr.size
        dijkstra(floorArr, maxX, maxY)
        return generateSequence(floor.last()) { it.prev }.sumOf { it.risk } - floor.first().risk
    }

    // see https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Pseudocode
    // and https://en.wikipedia.org/wiki/Dijkstra's_algorithm#Using_a_priority_queue
    private fun dijkstra(floor: Array<Array<Position>>, maxX: Int, maxY: Int) {
        val positions = PriorityQueue<Position>(Comparator.comparingInt { it.distance })
        floor[0][0].distance = 0
        positions.add(floor[0][0])

        while (positions.isNotEmpty()) {
            val u = positions.poll()
            val neighbours = u.coord.getAdjacent(false)
                .filter { (x, y) -> x in 0 until maxX && y in 0 until maxY }
                .map { (x, y) -> floor[y][x] }

            neighbours.forEach { v ->
                val alt = u.distance + v.risk
                if (alt < v.distance) {
                    v.distance = alt
                    v.prev = u
                    positions.offer(v)
                }
            }
        }
    }
}
