import Day.Companion.day

fun main() = day(5) {

    sample = """
        seeds: 79 14 55 13

        seed-to-soil map:
        50 98 2
        52 50 48

        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15

        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4

        water-to-light map:
        88 18 7
        18 25 70

        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13

        temperature-to-humidity map:
        0 69 1
        1 0 69

        humidity-to-location map:
        60 56 37
        56 93 4
    """.trimIndent()

    part01 {
        sampleResult = 35
        solution = {
            val almanac = map { it.split(' ').mapNotNull(String::toLongOrNull) }.iterator()
            val seeds = almanac.next().toLongArray()
            val hit = BooleanArray(seeds.size)
            for (map in almanac) if (map.isEmpty()) {
                hit.fill(false)
            } else {
                val (dest, source, range) = map
                for ((i, seed) in seeds.withIndex()) {
                    if (hit[i] || seed < source || seed > source + range) continue
                    hit[i] = true
                    seeds[i] = dest + seed - source
                }
            }
            seeds.min().toInt()
        }
        solutionResult = 3374647
    }

    part02 {
        sampleResult = null
        solution = null
        solutionResult = null
    }
}