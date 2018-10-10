package tutorial

/**
 * Lambdas: https://kotlinlang.org/docs/reference/lambdas.html
 *
 * @author Lukasz Frankowski
 */
@Suppress("MoveLambdaOutsideParentheses")
fun main(args: Array<String>) {

    println("\nfunction types")
    run {
        var operation: (Int, Int) -> Int // this type accepts values of function with two ints as arguments and returning int

        operation = fun(x: Int, y: Int): Int { // assigning block function to variable
            return x*y
        }
        println(operation(2, 3)) // 6

        operation = { x, y -> x * y} // lambda equivalent
        println(operation(2, 3)) // 6
        println(operation.invoke(2, 4)) // 8: alternative function invocatio method using .invoke()
    }

    println("\nassigning object method to functions")
    run {
        // the instance method assigned from object to function variable gets the original object in first parameter
        val operation: (String, Int, Int) -> CharSequence = String::subSequence
        println(operation("abc", 1, 2)) // b
    }

    println("\nlambda syntax")
    run {
        // this function gets function as the second argument
        fun operate(x: Int, operation: (Int) -> Int): Int = operation(x)

        // we can call it this way:
        println(operate(1, { x -> x+1 })) // 2
        println(operate(1, { it+1 })) // 2: the only lamnda argument can be omitted and gets "it" name
        println(operate(1) { it + 1 }) // 2: last lambda argument can be moved after function invocation

        // please notice in above examples, that last expression becomes lambda return value, however lambda can also return value using
        // qualified return
        println(operate(1, {
            return@operate it+1
        })) // 2
    }

    println("\nfunction literals with receiver")
    run {
        class A {
            fun hello() {
                println("hello")
            }
        }

        fun a(init: A.() -> Unit): A { // A.()->Unit is NOT an extension, it's a function ()->Unit which gets A instance as "this"
            val a = A()
            a.init() // "init" function from method arguments is executed against "a" object
            return a
        }

        a {
            hello() // hello: here the A instance is "this" and we can operate on it
        }

    }

}