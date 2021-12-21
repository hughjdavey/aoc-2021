package util

object Utils {

    fun <T, R> Pair<T, T>.map(mapper: (t: T) -> R): Pair<R, R> {
        return mapper(this.first) to mapper(this.second)
    }

    fun Pair<Int, Int>.mul(): Int = this.first * this.second

    fun <T> List<T>.toPair(): Pair<T, T> {
        if (this.size != 2) {
            throw IllegalStateException("List must be of size 2")
        }
        return this.first() to this.last()
    }

    data class Coord(val x: Int, val y: Int) {

        fun getAdjacent(includeDiagonals: Boolean): List<Coord> {
            return (-1..1).flatMap { dy -> (-1..1).map { dx -> copy(x = x + dx, y = y + dy) } }
                .filterNot { it == this }
                .filter { includeDiagonals || it.x == this.x || it.y == this.y }
        }
    }

    // credit: https://jivimberg.io/blog/2018/06/02/implementing-takewhileinclusive-in-kotlin/
    inline fun <T> Iterable<T>.takeWhileInclusive(predicate: (T) -> Boolean): List<T> {
        var shouldContinue = true
        return takeWhile {
            val result = shouldContinue
            shouldContinue = predicate(it)
            result
        }
    }
}
