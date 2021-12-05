fun main() {
    fun countOfIncreasingSlidingWindows(measurements: List<Int>, slidingWindowSize: Int): Int {

       return measurements.filterIndexed { index, measurement ->
            index>= slidingWindowSize && measurement > measurements[index-slidingWindowSize]
        }.count()

    }

    fun part1(input: List<Int>): Int {

        return countOfIncreasingSlidingWindows(input,1)
    }

    fun part2(input: List<Int>): Int {

        return countOfIncreasingSlidingWindows(input,3)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInputasInt("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInputasInt("Day01")
    println(part1(input))
    println(part2(input))
}
