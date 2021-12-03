package util

object Utils {

    fun <T, R> Pair<T, T>.map(mapper: (t: T) -> R): Pair<R, R> {
        return mapper(this.first) to mapper(this.second)
    }

    fun Pair<Int, Int>.mul(): Int = this.first * this.second
}
