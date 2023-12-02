import java.io.InputStream

data class Part(
    val day: Day,
    var sampleResult: Any? = null,
    var solution: Solution? = null,
    var solutionResult: Any? = null,
) {

    fun solve(): Any? = solution?.run {
        if (solve(day.sample?.byteInputStream() ?: return null) == sampleResult) {
            val result = solve(javaClass.getResourceAsStream(fileName)!!)
            if (solutionResult != null && solutionResult != result) error("Solution did not produce expected solution result!")
            if (solutionResult == null) println(result)
            return result
        }
        return null
    }

    private fun Solution.solve(
        inputStream: InputStream
    ) = inputStream.bufferedReader().lineSequence().solve()

    private val fileName = "/day${day.number.toString().padStart(2, '0')}"
}