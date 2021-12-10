package days

import java.util.Stack

class Day10 : Day(10) {

    override fun partOne(): Any {
        return inputList.mapNotNull { isCorrupted(it) }.sumOf { it.getCorruptScore() }
    }

    override fun partTwo(): Any {
        val missing = inputList.filter { isCorrupted(it) == null }
            .map { getMissing(it).fold(0L) { score, c -> score * 5 + c.getIncompleteScore() } }
        return missing.sorted()[(missing.size / 2)]
    }

    private fun Char.getCorruptScore(): Int {
        return when (this) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> 0
        }
    }

    private fun Char.getIncompleteScore(): Long {
        return when (this) {
            ')' -> 1
            ']' -> 2
            '}' -> 3
            '>' -> 4
            else -> 0
        }
    }

    private fun Char.getOpposite(): Char {
        return when (this) {
            '(' -> ')'
            ')' -> '('
            '[' -> ']'
            ']' -> '['
            '{' -> '}'
            '}' -> '{'
            '<' -> '>'
            '>' -> '<'
            else -> this
        }
    }

    private fun getMissing(s: String): String {
        val unmatched = Stack<Char>()
        for (char in s) {
            if (listOf('(', '[', '{', '<').contains(char)) {
                unmatched.push(char)
                continue
            }

            val lastUnmatched = unmatched.peek()
            if (lastUnmatched == '(' && char == ')' || lastUnmatched == '[' && char == ']' || lastUnmatched == '{' && char == '}' || lastUnmatched == '<' && char == '>') {
                unmatched.pop()
            }
        }
        return unmatched.toList().map { it.getOpposite() }.joinToString("").reversed()
    }

    private fun isCorrupted(s: String): Char? {
        val unmatched = Stack<Char>()
        for (char in s) {
            if (listOf('(', '[', '{', '<').contains(char)) {
                unmatched.push(char)
                continue
            }

            if (listOf(')', ']', '}', '>').contains(char) && unmatched.empty()) {
                return char
            }

            val lastUnmatched = unmatched.peek()
            if (lastUnmatched == '(' && char == ')' || lastUnmatched == '[' && char == ']' || lastUnmatched == '{' && char == '}' || lastUnmatched == '<' && char == '>') {
                unmatched.pop()
            }
            else {
                return char
            }
        }
        return null
    }
}
