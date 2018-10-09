package tutorial

@Suppress("UNUSED_VALUE")
    /**
 * Type Checks and Casts: https://kotlinlang.org/docs/reference/typecasts.html
 *
 * @author Lukasz Frankowski
 */
fun main(args: Array<String>) {

    println("type check with \"is\"")
    run {
        fun printDetails(v: Any?) {
            if (v is String) // is = instanceof
                println("string with ${v.length} length") // v is smart-cast to string here (length property is available)
            if (v !is String) // !is = not instanceof
                println("not a string")
        }

        printDetails("text") // string with 4 length
        printDetails(1) // not a string
    }

    println("\ntype case with \"as\"")
    run {
        var something: Any = "text"
        var str: String? = something as String
        println(str) // text

        // when cast is not possible ClassCastException is thrown
        something = 1
        try {
            str = something as String // throws exception
        } catch (e: Exception) {
            println("${e::class.simpleName}: ${e.message}") // ClassCastException: java.lang.Integer cannot be cast to java.lang.String
        }

        // to avoid it use safe as? operator
        str = something as? String
        println(str) // null
    }

}