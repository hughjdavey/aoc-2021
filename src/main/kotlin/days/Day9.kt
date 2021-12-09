package days

import util.Utils

class Day9 : Day(9) {

    private val points = inputList.flatMapIndexed { y, s -> s.mapIndexed { x, c -> Point(c.toString().toInt(), Utils.Coord(x, y)) } }

    override fun partOne(): Any {
        return points.filter { it.isLowPoint(points) }.map(Point::riskLevel).sum()
    }

    override fun partTwo(): Any {
        return findBasins(points).map { it.size }.sorted().takeLast(3).reduce { a, e -> a * e }
    }

    private fun findBasins(points: List<Point>): List<Set<Point>> {
        return points.filter { it.isLowPoint(points) }.map { findBasin(points, it) }
    }

    private fun findBasin(allPoints: List<Point>, point: Point, basin: Set<Point> = setOf(point)): Set<Point> {
        val adjacentAndHigher = point.getAdjacent(allPoints).filter { it.height != 9 && it.height > point.height }
        if (adjacentAndHigher.isEmpty()) {
            return basin
        }
        return basin.plus(adjacentAndHigher).plus(adjacentAndHigher.flatMap { findBasin(allPoints, it) })
    }

    data class Point(val height: Int, val location: Utils.Coord) {

        val riskLevel = height + 1

        fun isLowPoint(points: List<Point>): Boolean {
            return getAdjacent(points).all { it.height > height }
        }

        fun getAdjacent(points: List<Point>): List<Point> {
            val minX = points.minOf { it.location.x }
            val minY = points.minOf { it.location.y }
            return location.getAdjacent(false).filter { it.x >= minX && it.y >= minY }
                .mapNotNull { loc -> points.find { it.location == loc } }
        }
    }
}
