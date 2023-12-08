fun main() = Day(2) {

    sample = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
            """.trimIndent()

    part01 {
        sampleResult = 8
        solution = {
            sumOf {
                val (id, game) = it.split(':')
                val possible = game
                    .trim()
                    .split(';')
                    .map { set -> set.split(',') }
                    .all { draws ->
                        draws.none { cubes ->
                            val (value, code) = cubes.trim().split(' ')
                            val count = value.toInt()
                            code[0] == 'r' && count > 12 || code[0] == 'g' && count > 13 || code[0] == 'b' && count > 14
                        }
                    }
                if (possible) id.substringAfter(' ').toInt() else 0
            }
        }
        solutionResult = 2101
    }

    part02 {
        sampleResult = 2286
        solution = {
            sumOf { game ->
                game.substringAfter(':')
                    .trim()
                    .split(';')
                    .flatMap { set -> set.split(',') }
                    .map { cubes ->
                        val (value, code) = cubes.trim().split(' ')
                        code to value.toInt()
                    }.groupingBy {
                        it.first
                    }.aggregate<Pair<String, Int>, String, Int> { _, acc, pair, _ ->
                        maxOf(acc ?: pair.second, pair.second)
                    }.values.fold(1) { acc, min ->
                        acc * min
                    }.toInt()
            }
        }
        solutionResult = 58269
    }
}