fun main() = Day(6) {

    sample = """
        Time:      7  15   30
        Distance:  9  40  200
    """.trimIndent()

    part01 {
        sampleResult = 288
        solution = {
            val (times, records) = map { it.numbers() }.toList()
            times.zip(records).map { (time, record) -> wins(time, record) }.reduce(Int::times)
        }
        solutionResult = 2612736
    }

    part02 {
        sampleResult = 71503
        solution = {
            val (time, record) = map { it.numbers().joinToString("").toLong() }.toList()
            wins(time, record)
        }
        solutionResult = 29891250
    }
}

private fun String.numbers() = split(' ').mapNotNull(String::toLongOrNull)
private fun wins(time: Long, record: Long) = (1 until time).count { (time - it) * it > record }