package tutorial

@Suppress("DIVISION_BY_ZERO", "UNUSED_VARIABLE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE", "UNUSED_VALUE")
/**
 * Exceptions: https://kotlinlang.org/docs/reference/exceptions.html
 *
 * @author Lukasz Frankowski
 */
fun main(args: Array<String>) {

    println("exception basics")
    run {
        // basically this is the same as in java
        try {
            throw Throwable("Basic exception") // Throwable is the base class for exceptions
        } catch (e: Throwable) {
            println("${e::class.simpleName}: ${e.message}")
        }

        // the only difference is not checked exceptions, and no "throws" clause in functions and methods
    }

    println("\ntry..catch is an expression")
    run {
        val x: Int = try { 1/0 } catch (e: Exception) { -1 }
        println(x) // -1, because expression above throws division by zero exception
    }

    println("\nthrow is an expression")
    run {
        val s: String? = null

        val x: Int
        try {
            x = s?.length ?: throw Exception("No value")
        } catch (e: Exception) {
        }

        // after such scenario "x" is not of special Nothing type
        // you can also use Nothing type to mark functions that never returns

        fun throwEx(): Nothing {
            throw Exception()
        }
    }
}