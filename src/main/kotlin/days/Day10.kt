package days

import java.util.Stack

class Day10 : Day(10) {

    override fun partOne(): Any {
        return inputList.mapNotNull { getFirstIllegalChar(it) }.sumOf { it.getIllegalScore() }
    }

    override fun partTwo(): Any {
        val missing = inputList.filter { getFirstIllegalChar(it) == null }
            .map { getMissing(it).fold(0L) { score, c -> score * 5 + c.getIncompleteScore() } }
        return missing.sorted()[(missing.size / 2)]
    }

    private fun getMissing(s: String): String {
        return s.fold(Stack<Char>()) { unmatched, char ->
            if (listOf('(', '[', '{', '<').contains(char)) unmatched.push(char) else unmatched.pop()
            unmatched
        }.toList().map { it.getMirror() }.joinToString("").reversed()
    }

    private fun getFirstIllegalChar(s: String): Char? {
        val unmatched = Stack<Char>()
        for (char in s) {
            when {
                listOf('(', '[', '{', '<').contains(char) -> unmatched.push(char)
                unmatched.isNotEmpty() && listOf("()", "[]", "{}", "<>").contains("${unmatched.peek()}$char") -> unmatched.pop()
                else -> return char
            }
        }
        return null
    }

    private fun Char.getIllegalScore(): Int = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137).getOrDefault(this, 0)

    private fun Char.getIncompleteScore(): Long = mapOf(')' to 1L, ']' to 2L, '}' to 3L, '>' to 4L).getOrDefault(this, 0)

    private fun Char.getMirror(): Char = mapOf('(' to ')', ')' to '(', '[' to ']', ']' to '[', '{' to '}', '}' to '{', '<' to '>', '>' to '<').getOrDefault(this, this)
}
