package days

import util.Utils

class Day15 : Day(15) {

    override fun partOne(): Any {
        val floor = inputList.flatMapIndexed { y, row -> row.mapIndexed { x, c -> Position(Utils.Coord(x, y), c.digitToInt()) } }
        //System.err.println(floor)
        //System.err.println(floor.first())
        //System.err.println(floor.last())
        //System.err.println(getPaths(floor, Path(floor.first(), floor.last())))

        val path = Path(floor.first(), floor.last())
        val pathSoFar = PathSoFar(floor.first(), null, 0)
        getPaths2(floor, pathSoFar).map { it }.forEach(System.err::println)

//        val foo = path.currentPosition().coord.getAdjacent(false)
//            .mapNotNull { coord -> floor.find { it.coord == coord } }
//            .filterNot { path.hasVisited(it) || it.coord.y < path.currentPosition().coord.y }
//        System.err.println(path)
//        System.err.println(foo)
//
//        val p1 = path.visit(Position(Utils.Coord(1, 0), 1))
//        val p2 = path.visit(Position(Utils.Coord(0, 1), 1))
//
//        System.err.println(p1.currentPosition().coord.getAdjacent(false)
//            .mapNotNull { coord -> floor.find { it.coord == coord } }
//            .filterNot { path.hasVisited(it) || it.coord.y < path.currentPosition().coord.y })
//        System.err.println(p2.currentPosition().coord.getAdjacent(false)
//            .mapNotNull { coord -> floor.find { it.coord == coord } }
//            .filterNot { path.hasVisited(it) || it.coord.y < path.currentPosition().coord.y })
        return 0
    }

    override fun partTwo(): Any {
        return 0
    }

    data class Position(val coord: Utils.Coord, val risk: Int)

    data class Path(val start: Position, val end: Position, val path: List<Position> = listOf(start)) {

        fun currentPosition() = path.last()

        fun isEnded() = currentPosition() == end

        fun hasVisited(position: Position) = path.contains(position)

        fun visit(position: Position) = copy(path = path.plus(position))

//        fun fork(floor: List<Position>): List<Path> {
//            return (caveMap[currentLocation()] ?: emptyList())
//                .filter(::canVisit)
//                .map { copy(path = path.plus(it)) }
//        }
    }

    fun getPaths(floor: List<Position>, path: Path, paths: Set<Path> = setOf(path)): Set<Path> {
        val finalRow = floor.filter { pos -> pos.coord.y == floor.maxOf { it.coord.y } }
        //System.err.println(finalRow)
        //if (finalRow.contains(path.currentPosition())) {
        if (path.currentPosition().coord.y > 3) {
            return setOf(path)
        }
        return path.currentPosition().coord.getAdjacent(false)
            .mapNotNull { coord -> floor.find { it.coord == coord } }
            .filterNot { path.hasVisited(it) || it.coord.y < path.currentPosition().coord.y }
            .flatMap { getPaths(floor, path.visit(it), paths.plus(path.visit(it))) }
            .toSet()
    }

    fun getPaths2(floor: List<Position>, path: PathSoFar, paths: Set<PathSoFar> = setOf()): Set<PathSoFar> {
        val finalRow = floor.filter { pos -> pos.coord.y == floor.maxOf { it.coord.y } }
        //System.err.println(finalRow)
        if (path.position.coord.y == floor.maxOf { it.coord.y }) {
        //if (path.position.coord.y > 3) {
            return setOf(path)
        }
        return path.position.coord.getAdjacent(false)
            .mapNotNull { coord -> floor.find { it.coord == coord } }
            .filterNot { path.lastPosition == it || it.coord.y < path.position.coord.y }
            .flatMap { getPaths2(floor, path.copy(position = it, lastPosition = path.position, totalRisk = path.totalRisk + it.risk), paths) }
            .toSet()
    }

    data class PathSoFar(val position: Position, val lastPosition: Position?, val totalRisk: Int)
}
