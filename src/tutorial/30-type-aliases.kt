package tutorial

/**
 * Type Aliases: https://kotlinlang.org/docs/reference/type-aliases.html
 *
 * @author Lukasz Frankowski
 */

open class MyVeryLongNamedPersonClass(val name: String) {

    class PersonWithLastName(name: String, val lastName: String): MyVeryLongNamedPersonClass(name) {
        override fun toString(): String {
            return "$name $lastName"
        }
    }

}

// aliasing class
private typealias P = MyVeryLongNamedPersonClass.PersonWithLastName

// aliasing function declaration
private typealias BiConsumer = (i: Int, j: Int) -> Int

fun main(args: Array<String>) {

    println("aliasing class")
    run {
        println(P("Tom", "Smith")) // Tom Smith
    }

    println("\naliasing function declaration")
    run {
        val f: BiConsumer
        f = {x, y -> x*y}
        println(f(2, 3)) // 6
    }

}