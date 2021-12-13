package days

import util.Utils

class Day13 : Day(13) {

    private val dots = inputList.takeWhile { it != "" }.map { it.split(",").map { it.toInt() } }.map { Utils.Coord(it[0], it[1]) }

    private val folds = inputList.takeLastWhile { it != "" }.map { it.dropWhile { it != 'x' && it != 'y' }.split("=") }.map { it[0] to it[1].toInt() }

    override fun partOne(): Any {
        return fold(listOf(folds.first())).count { it == '#' }
    }

    // todo improve performance as this takes over 2s on my real input
    override fun partTwo(): Any {
        return "\n${fold(folds)}\n"
    }

    fun fold(folds: List<Pair<String, Int>>, dots: List<Utils.Coord> = this.dots): String {
        return folds.fold(getPaper(dots)) { paper, fold -> fold(fold, paper) }
    }

    fun fold(fold: Pair<String, Int>, paper: String): String {
        val paperList = paper.split("\n")
        val (side1, side2) = if (fold.first == "y") {
            paperList.subList(0, fold.second) to paperList.subList(fold.second + 1, paperList.size).reversed()
        }
        else {
            paperList.map { it.substring(0, fold.second) } to paperList.map { it.substring(fold.second + 1).reversed() }
        }
        return side1.mapIndexed { y, row -> row.mapIndexed { x, c -> if (side2[y][x] == '#') '#' else c }.joinToString("") }.joinToString("\n")
    }

    fun getPaper(dots: List<Utils.Coord> = this.dots): String {
        val maxX = dots.maxOf { it.x }
        val maxY = dots.maxOf { it.y }
        return (0..maxY).flatMap { y -> (0..maxX).map { x ->
            val maybeDot = dots.find { it.x == x && it.y == y }
            val char = if (maybeDot != null) "#" else "."
            if (x == maxX && y != maxY) char + "\n" else char
        } }.joinToString("")
    }
}
