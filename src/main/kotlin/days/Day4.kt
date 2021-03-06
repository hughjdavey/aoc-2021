package days

class Day4 : Day(4) {

    private val numbers = inputList.first().split(",").map { it.toInt() }

    override fun partOne(): Any {
        return winningBoardSet(true).find { it.winningNumber != null }?.score() ?: 0
    }

    override fun partTwo(): Any {
        return winningBoardSet(false).maxByOrNull { numbers.indexOf(it.winningNumber) }?.score() ?: 0
    }

    private fun winningBoardSet(firstWinner: Boolean): List<BingoCard> {
        val boards = inputList.drop(2).filter { it != "" }.chunked(5).map { BingoCard(it) }
        return numbers.asSequence()
            .map { n -> boards.map { board -> board.check(n) } }
            .takeWhile { if (firstWinner) it.all { it.winningNumber == null } else it.any { it.winningNumber == null } }
            .last()
    }

    data class BingoCard(val grid: List<String>) {

        private val rows = grid.map { it.replace(Regex("\\s+"), " ").trim().split(" ").map { BingoNumber(it.toInt()) } }
        private val columns = (0..(rows.first().lastIndex)).map { rows.map { row -> row[it] } }

        var winningNumber: Int? = null

        fun check(n: Int): BingoCard {
            rows.flatten().find { it.number == n }?.marked = true
            val wins = rows.any { row -> row.all { it.marked } } || columns.any { col -> col.all { it.marked } }
            if (wins && winningNumber == null) {
                winningNumber = n
            }
            return this
        }

        fun score(): Int {
            return rows.flatten().filterNot { it.marked }.sumOf { it.number } * winningNumber!!
        }

        override fun toString(): String {
            return rows.map { row -> row.map { if (it.marked) "(${it.number})" else it.number  } }
                .joinToString("\n").plus("\n")
        }
    }

    data class BingoNumber(val number: Int, var marked: Boolean = false)
}
