data class Part(
    val number: Number,
    var sampleResult: Any? = null,
    var solution: (Sequence<String>.() -> Any)? = null,
    var solutionResult: Any? = null,
)