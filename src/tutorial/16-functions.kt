package tutorial

/**
 * Functions: https://kotlinlang.org/docs/reference/functions.html
 *
 * @author Lukasz Frankowski
 */
fun main(args: Array<String>) {

    println("function basics")
    run {

        // default block function with return type and arguments
        fun power(x: Int): Int { // for block function the return type can't be inferred and needs to be specified
            return x*x
        }
        println(power(2)) // 4

        // functions can be defined also as single expressions
        fun power2(x: Int) = x*x // return type is Int and is inferred
        println(power2(4)) // 16

        // default function arguments with "="
        fun mul(x: Int = 10, y: Int = 10) { // this function is of Unit type, which is java Void
            println(x*y)
        }
        mul() // 100
        mul(2) // 20
        mul(y=4) // 40: this is example of specyfying only someargument values by name

    }

    println("\nvariable number of arguments")
    run {
        fun hello(vararg strings: String) {
            println(strings::class.simpleName) // Array
            println(strings[0]) // Tom
            println("hello ${strings.joinToString(", ")}") // hello Tom, Joe
        }

        hello("Tom", "Joe")
    }

    println("\ninfix notation")
    run {
        class Addable(val x: Int) {
            infix fun add(a: Addable): Addable { // infix functions can be used as operator between two Addable objects
                return Addable(x + a.x)
            }

            infix fun add(a: Int): Int { // can also be used as operator between Addable object and anything other, with any result type
                return x + a
            }

            override fun toString(): String {
                return "Addable($x)"
            }
        }

        println(Addable(1) add Addable(2)) // Addable(3): the example of infix function call
        println(Addable(1) add 2) // 3: the example of second infix function call
    }

    println("\nlocal functions and scope")
    run {
        var x = 1

        fun displayX() { // this is local function, because it's defined in other function (run() lambda)
            println(x) // this function has access to x value, which is located on the root scope
        }

        x = 2 // fortunately, the variable on scope doesn't have to be final

        displayX() // 2
    }

}