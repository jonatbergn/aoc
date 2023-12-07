import Day.Companion.day

fun main() = day(7) {

    sample = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
    """.trimIndent()

    part01 {
        sampleResult = 6440
        solution = { solve(cards = "23456789TJQKA") }
        solutionResult = 250370104
    }

    part02 {
        sampleResult = 5905
        solution = { solve(cards = "23456789TQKA") }
        solutionResult = 251735672
    }
}

private val handComparator = compareBy<List<Int>> { hand ->
    val jokers = hand.count { it < 0 }
    val counts = hand.filter { it >= 0 }.groupingBy { it }.eachCount().values.sortedDescending()
    val highest = counts.getOrElse(0) { 0 }
    val secondHighest = counts.getOrElse(1) { 0 }
    when {
        5 == highest + jokers -> 6
        4 == highest + jokers -> 5
        5 == highest + secondHighest + jokers -> 4
        3 == highest + jokers -> 3
        4 == highest + secondHighest + jokers -> 2
        2 == highest + jokers -> 1
        else -> 0
    }
}.thenComparator { a, b -> a.zip(b).first { it.first != it.second }.run { first compareTo second } }

private fun Sequence<String>.solve(
    cards: String,
) = map { it.split(' ') }
    .map { (hand, bid) -> hand.map(cards::indexOf) to bid.toInt() }
    .sortedWith(compareBy(handComparator) { (hand, _) -> hand })
    .map { (_, bid) -> bid }
    .withIndex()
    .sumOf { (i, bid) -> (i + 1) * bid }