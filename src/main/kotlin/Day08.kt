fun main() = Day(8) {

class Pouch(input: List<String>) {

    constructor(input: Sequence<String>) : this(input.filter { it.isNotEmpty() }.toList())

    val instructions = input.first()
    val map = input.drop(1)
        .mapNotNull { """^(.*) = \((.*), (.*)\)$""".toRegex().find(it)?.groupValues?.drop(1) }
        .associate { (p, l, r) -> p to (l to r) }

    val fromAAAtoZZZ by lazy { count("AAA") { indexOf("ZZZ") } }
    val ghosts by lazy {
        map.keys.filter { node ->
            node.endsWith('A')
        }.map { start ->
            count(start) { indexOfFirst { node -> node.endsWith('Z') } }.toLong()
        }.reduce { acc, i ->
            lcm(acc, i)
        }
    }

    fun count(
        from: String,
        find: Sequence<String>.() -> Int,
    ) = generateSequence(from) {
        instructions.fold(it) { node, nav -> if (nav == 'L') map[node]!!.first else map[node]!!.second }
    }.run(find) * instructions.length

    fun lcm(a: Long, b: Long): Long = a * (b / gcd(a, b))

    fun gcd(a: Long, b: Long): Long {
        return if (b == 0L) a else gcd(b, a % b)
    }
}

    part {
        sample = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
            """.trimIndent()
        sampleResult = 6
        solution = { Pouch(this).fromAAAtoZZZ }
        solutionResult = 12361
    }

    part {
        sample = """
            LR

            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """.trimIndent()
        sampleResult = 6L
        solution = { Pouch(this).ghosts }
        solutionResult = 18215611419223
    }
}