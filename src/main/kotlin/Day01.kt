import Day.Companion.newDay

fun main() = newDay(1) {
    sample = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet
                """.trimIndent()
    part01 {
        sampleResult = 142
        solution = SolutionImpl(candidates = digits)
        solutionResult = 56506
    }

    part02 {
        sampleResult = 142
        solution = SolutionImpl(candidates = digits + numbers)
        solutionResult = 56017
    }
}

private val digits = (1..9).map { it.toString() }
private val numbers = "one two three four five six seven eight nine".split(' ')

private class SolutionImpl(
    private val candidates: List<String>,
) : Solution {

    override fun Sequence<String>.solve() = sumOf {
        val first = it.findAnyOf(candidates)?.second?.digit()
        val second = it.findLastAnyOf(candidates)?.second?.digit()
        "$first$second".toInt()
    }

    private fun String.digit() = toIntOrNull() ?: (candidates.indexOf(this) % 9 + 1)
}