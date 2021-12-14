package days

import util.Utils.toPair

class Day14 : Day(14) {

    private val template = inputList.first()

    private val rules = inputList.drop(2).map { it.split(" -> ").toPair() }

    override fun partOne(): Any {
        val polymer = pairInsertion(10)
        return polymer.maxOf { polymer.count { c -> c == it } } - polymer.minOf { polymer.count { c -> c == it } }
    }

    override fun partTwo(): Any {
        val letterCounts = pairInsertionMap(40).filterKeys { it.length == 1 }
        return letterCounts.values.maxOf { it } - letterCounts.values.minOf { it }
    }

    fun pairInsertion(steps: Int, template: String = this.template, rules: List<Pair<String, String>> = this.rules): String {
        return (1..steps).fold(template) { polymer, _ ->
            polymer.first() + polymer.windowed(2).map { pair ->
                val maybeRule = rules.find { it.first == pair }
                if (maybeRule != null) "${maybeRule.second}${pair[1]}" else pair[1]
            }.joinToString("")
        }
    }

    fun pairInsertionMap(steps: Int, template: String = this.template, rules: List<Pair<String, String>> = this.rules): Map<String, Long> {
        val initial = toPolymerMap(template)
        template.forEach { initial.merge(it.toString(), 1, Long::plus) }
        return (1..steps).fold(toPolymerMap(template)) { map, _ ->
            map.filterKeys { map.getOrDefault(it, 0) > 0 }.forEach { (k, v) ->
                val rule = rules.find { it.first == k }
                if (rule != null) {
                    map[rule.first] = map[rule.first]?.minus(v) ?: 0
                    map.merge("${rule.first[0]}${rule.second}", v, Long::plus)
                    map.merge("${rule.second}${rule.first[1]}", v, Long::plus)
                    map.merge(rule.second, v, Long::plus)
                }
            }

            map
        }
    }

    // map containing count of all pairs but also counts of all individual letters
    // e.g. the NNCB example would yield {NN=1, NC=1, CB=1, N=2, C=1, B=1}
    fun toPolymerMap(template: String): MutableMap<String, Long> {
        val map = mutableMapOf<String, Long>()
        template.windowed(2).forEach { map.merge(it, 1, Long::plus) }
        template.forEach { map.merge(it.toString(), 1, Long::plus) }
        return map
    }
}
