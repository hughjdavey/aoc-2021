package days

class Day12 : Day(12) {

    private val caveMap = parseInput(inputList)

    override fun partOne(): Any {
        return getPaths(caveMap, true).size
    }

    override fun partTwo(): Any {
        return getPaths(caveMap, false).size
    }

    data class Path(val onlyVisitSmallCavesOnce: Boolean, val path: List<String> = listOf("start")) {

        fun isEnded() = currentLocation() == "end"

        fun fork(caveMap: Map<String, List<String>>): List<Path> {
            return (caveMap[currentLocation()] ?: emptyList())
                .filter(::canVisit)
                .map { copy(path = path.plus(it)) }
        }

        private fun canVisit(location: String): Boolean {
            if (onlyVisitSmallCavesOnce) {
                return !isSmallCave(location) || pastVisits(location) < 1
            }

            if (location == "start") {
                return false
            }
            else if (isSmallCave(location)) {
                return if (path.any { isSmallCave(it) && pastVisits(it) == 2 }) pastVisits(location) < 1 else pastVisits(location) < 2
            }
            return true
        }

        private fun isSmallCave(location: String) = location == location.lowercase()

        private fun currentLocation() = path.last()

        private fun pastVisits(location: String) = path.count { it == location }
    }

    fun getPaths(caveMap: Map<String, List<String>>, onlyVisitSmallCavesOnce: Boolean): Set<Path> {
        return getPaths(caveMap, Path(onlyVisitSmallCavesOnce))
    }

    private fun getPaths(caveMap: Map<String, List<String>>, path: Path, paths: Set<Path> = setOf(path)): Set<Path> {
        if (path.isEnded()) {
            return setOf(path)
        }
        return path.fork(caveMap).flatMap { getPaths(caveMap, it, paths.plus(it)) }.toSet()
    }

    fun parseInput(connections: List<String>): Map<String, List<String>> {
        return connections.fold(mutableMapOf()) { map, connection ->
            val (start, end) = connection.split("-")
            map.merge(start, listOf(end), List<String>::plus)
            map.merge(end, listOf(start), List<String>::plus)
            map
        }
    }
}
