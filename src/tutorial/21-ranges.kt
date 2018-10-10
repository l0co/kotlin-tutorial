package tutorial

/**
 * Ranges: https://kotlinlang.org/docs/reference/ranges.html
 *
 * @author Lukasz Frankowski
 */
@Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
fun main(args: Array<String>) {

    println("range basics")
    run {
        var range: IntRange = 1..10 // represents integers from 1 to 10
        range = 1.rangeTo(10) // internal implementation uses "rangeTo" operator function

        if (1 in range) // in range check
            println("1 in range") // 1 in range
        if (15 !in range) // not in range check
            println("15 not in range") // 15 not in range
    }

    println("\nrange interations")
    run {
        // default
        for (i in 1..4)
            print("$i ") // 1 2 3 4
        println()

        // reversed order using infix "downTo" function
        for (i in 4 downTo 1)
            print("$i ") // 4 3 2 1
        println()

        // step using infix "step" function
        for (i in 1..4 step 2)
            print("$i ") // 1 3
        println()

        // downTo + step
        for (i in 4 downTo 1 step 2)
            print("$i ") // 4 2
        println()

        // excluded last element
        for (i in 1 until 4)
            print("$i ") // 1 2 3
        println()
    }

}