import java.io.InputStream

data class Day(
    val number: Number
) {

    var sample: String? = null
    private var part01: Part? = null
    private var part02: Part? = null
    private val fileName = "/day${number.toString().padStart(2, '0')}"

    fun part01(block: Part.() -> Unit) {
        part01 = Part(number = 1).apply(block)
    }

    fun part02(block: Part.() -> Unit) {
        part02 = Part(number = 2).apply(block)
    }

    private fun solve(part: Part) {
        val (number, sampleResult, solution, solutionResult) = part
        if (sampleResult == null) return
        if (solution == null) error("Solution missing")
        val sampleInput = sample?.byteInputStream()?.lines() ?: return
        val realInput = javaClass.getResourceAsStream(fileName)?.lines() ?: return
        val solvedSampleResult = sampleInput.run(solution)
        val solvedSolutionResult = realInput.run(solution)
        check(sampleResult == solvedSampleResult) { "Part $number failed" }
        check(solutionResult == null || solutionResult == solvedSolutionResult) { "Part $number failed" }
        println(solvedSolutionResult)
    }

    private fun InputStream.lines() = bufferedReader().lineSequence()

    companion object {

        fun day(number: Number, block: Day.() -> Unit): Unit = with(Day(number).apply(block)) {
            listOfNotNull(part01, part02).forEach(::solve)
        }
    }
}