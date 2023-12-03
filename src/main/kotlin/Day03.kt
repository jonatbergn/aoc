import Day.Companion.day

fun main() = day(3) {

    sample = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
        """.trimIndent()

    part01 {
        sampleResult = 4361
        solution = { solve { sumOf { it.sum() } } }
        solutionResult = 520135
    }

    part02 {
        sampleResult = 467835
        solution = { solve { filter { it.size == 2 }.sumOf { (a, b) -> a * b } } }
        solutionResult = 72514855
    }
}

private fun Sequence<String>.solve(
    sum: Sequence<List<Int>>.() -> Int,
) = windowed(3) { lines ->
    symbols.findAll(lines[1])
        .map(MatchResult::range)
        .map(IntRange::first)
        .map { symbol ->
            lines.flatMap(digits::findAll)
                .map { (it.range.first - 1)..(it.range.last + 1) to it.groupValues[1].toInt() }
                .filter { (target, _) -> symbol in target }.map { (_, number) -> number }
        }
}.flatten().run(sum)

private val digits = "([0-9]+)".toRegex()
private val symbols = "([^0-9.])".toRegex()