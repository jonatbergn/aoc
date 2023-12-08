fun main() = Day(1) {

    sample = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
        """.trimIndent()

    part01 {
        sampleResult = 142
        solution = { solve(candidates = digits) }
        solutionResult = 56506
    }

    part02 {
        sampleResult = 142
        solution = { solve(candidates = digits + numbers) }
        solutionResult = 56017
    }
}

private fun Sequence<String>.solve(candidates: List<String>) = sumOf {
    fun String.digit() = toIntOrNull() ?: (candidates.indexOf(this) % 9 + 1)
    val first = it.findAnyOf(candidates)?.second?.digit()
    val second = it.findLastAnyOf(candidates)?.second?.digit()
    "$first$second".toInt()
}

private val digits = (1..9).map { it.toString() }
private val numbers = "one two three four five six seven eight nine".split(' ')