class Day(
    val number: Number
) {

    var sample: String? = null
    private var part01: Part? = null
    private var part02: Part? = null

    fun part01(block: Part.() -> Unit) {
        part01 = Part(day = this).apply(block)
    }

    fun part02(block: Part.() -> Unit) {
        part02 = Part(day = this).apply(block)
    }

    companion object {

        fun newDay(number: Number, block: Day.() -> Unit): Unit = with(Day(number).apply(block)) {
            part01?.solve()
            part02?.solve()
        }
    }
}