fun main() = Day(9) {

    sample = """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45
    """.trimIndent()

    part {
        sampleResult = 114
        solution = {
            fun List<Int>.next(): Int {
                return if (all { it == 0 }) 0 else last() + zipWithNext { a, b -> b - a }.next()
            }
            map { it.split(' ').map(String::toInt) }.sumOf { it.next() }
        }
        solutionResult = 1647269739
    }

    part {
        sampleResult = 2
        solution = {
            fun List<Int>.next(): Int {
                return if (all { it == 0 }) 0 else first() + zipWithNext { a, b -> a - b }.next()
            }
            map { it.split(' ').map(String::toInt) }.sumOf { it.next() }
        }
        solutionResult = 864
    }
}