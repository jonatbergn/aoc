import java.io.InputStream

class Day private constructor(number: Number) {

    var sample: String? = null
    private val fileName = "/day${number.toString().padStart(2, '0')}"

    fun part(block: Part.() -> Unit) = solve(Part(number = 2).apply(block))


    private fun solve(part: Part) {
        val (number, sampleResult, solution, solutionResult) = part
        if (sampleResult == null) return
        if (solution == null) error("Solution missing")
        val sampleInput = sample?.byteInputStream()?.lines() ?: return
        val realInput = javaClass.getResourceAsStream(fileName)?.lines() ?: return
        val solvedSampleResult = sampleInput.run(solution)
        check(sampleResult == solvedSampleResult) { "Part $number failed" }
        val solvedSolutionResult = realInput.run(solution)
        check(solutionResult == null || solutionResult == solvedSolutionResult) { "Part $number failed" }
        println(solvedSolutionResult)
    }

    private fun InputStream.lines() = bufferedReader().lineSequence()

    companion object {
        @Deprecated("")
        fun day(number: Number, block: Day.() -> Unit): Unit = Day(number).run(block)
        operator fun invoke(number: Number, block: Day.() -> Unit): Unit = Day(number).run(block)
    }
}