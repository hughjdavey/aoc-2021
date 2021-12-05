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

    data class Coord(val x: Int, val y: Int)
}
