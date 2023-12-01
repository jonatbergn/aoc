import Day01.part01
import Day01.part02
import kotlin.test.Test
import kotlin.test.assertEquals


class Day01Test {

    private val input = javaClass.getResourceAsStream("/day01")!!

    @Test
    fun `part01 with sample data`() {
        val sample = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
            """.trimIndent().byteInputStream()
        assertEquals(142, part01(sample))
    }

    @Test
    fun `part02 with sample data`() {
        val sample = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
            """.trimIndent().byteInputStream()
        assertEquals(281, part02(sample))
    }

    @Test
    fun `part01 with actual data`() {
        assertEquals(56506, part01(input))
    }

    @Test
    fun `part02 with actual data`() {
        assertEquals(56017, part02(input))
    }
}
