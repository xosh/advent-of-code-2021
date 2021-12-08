package day03

import readInput

fun main() {

    fun mostCommonDigitAtIndex(input: List<String>, index: Int): Char {

        return if (input.count { binaryNumber -> binaryNumber[index] == '1' } *2 >= input.size) {
            '1'
        } else
            '0'
    }

    fun leastCommonDigitAtIndex(input: List<String>, index: Int): Char{

        return if (input.count { binaryNumber -> binaryNumber[index] == '1' } *2 >= input.size) {
            '0'
        } else
            '1'
    }

    fun part1(input: List<String>): Int {

        var gammaRate = ""
        var epsilonRate = ""

        for (i in 0 until input[0].length) {
            gammaRate+=mostCommonDigitAtIndex(input,i)
            epsilonRate+=leastCommonDigitAtIndex(input,i)
        }

        return gammaRate.toInt(2)*epsilonRate.toInt(2)
    }




    fun part2(input: List<String>): Int {

        var oxyInput = input
        var oxyIndex = 0
        var co2Input = input
        var co2Index = 0

        while (oxyInput.size > 1) {

            oxyInput = oxyInput.filter { binaryNumber ->
                binaryNumber[oxyIndex] == mostCommonDigitAtIndex(oxyInput, oxyIndex)
            }
            oxyIndex++
        }

        while (co2Input.size > 1) {

            co2Input = co2Input.filter { binaryNumber ->
                binaryNumber[co2Index] == leastCommonDigitAtIndex(co2Input, co2Index)
            }
            co2Index++
        }
        
        return oxyInput[0].toInt(2)*co2Input[0].toInt(2)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("day03/Day03")
    println(part1(input))
    println(part2(input))
}
