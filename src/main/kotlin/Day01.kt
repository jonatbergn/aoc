import java.io.InputStream

object Day01 {

    fun part01(input: InputStream) = input.solve { parseInt(value = it, candidates = digits) }
    fun part02(input: InputStream) = input.solve { parseInt(value = it, candidates = digits + numbers) }

    private fun InputStream.solve(number: (String) -> Int) = bufferedReader().lineSequence().sumOf(number)
    private fun parseInt(value: String, candidates: List<String>) = buildString {
        fun String.digit() = toIntOrNull() ?: (candidates.indexOf(this) % 9 + 1)
        append(value.findAnyOf(candidates)?.second?.digit())
        append(value.findLastAnyOf(candidates)?.second?.digit())
    }.toInt()

    private val digits = (1..9).map { it.toString() }
    private val numbers = "one two three four five six seven eight nine".split(' ')
}
