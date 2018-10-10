package tutorial

/**
 * Basic Syntax: https://kotlinlang.org/docs/reference/basic-syntax.html
 * 
 * @author Lukasz Frankowski
 */
@Suppress("JoinDeclarationAndAssignment", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE", "UNUSED_VALUE", "ConstantConditionIf", "ConvertCallChainIntoSequence")
fun main(args: Array<String>) {

    println("function arguments and return types")
    run {
        // block body function
        fun sum(a: Int, b: Int): Int {
            return a + b
        }
        println("1 + 2 = ${sum(1, 2)}") // 1 + 2 = 3: this is a string template, widely used in this tutorial

        // expression body function can infer type
        fun sum2(a: Int, b: Int) = a + b // == fun sum2(a: Int, b: Int): Int
        println("1 + 2 = ${sum2(1, 2)}") // 1 + 2 = 3

        // function with no returning value
        fun void(): Unit {
            println("void function")
        }
        void() // void function
        fun void2() { // Unit can be omitted
            println("void function")
        }
        void2() // void function
    }

    println("\nval = final, var = mutuable")
    run {
        val x: Int
        x = 1
        // x = 2 // error

        var y: Int
        y = 1
        y = 2
    }

    println("\nfunction scope")
    run {
        var x = 1

        fun incX() {
            x += 1 // has access to scope
        }

        println(x) // 1
        incX()
        println(x) // 2
    }

    println("\nif is an expression")
    run {
        val x = if (true) 1 else 2
        println(x) // 1
    }

    println("\nnull check")
    run {
        fun returnInt(): Int {
            // return null // Error:(67, 20) Kotlin: Null can not be a value of a non-null type Int
            return 1
        }

        fun returnInt2(): Int? { // ? = "nullable" modifier
            return null
        }

        println(returnInt()) // 1
        println(returnInt2()) // null
    }

    println("\ninstanceof")
    run {
        fun printStringLen(s: Any?) { // ? = optional argument
            if (s is String) // is = instanceof
                println("string length: ${s.length}") // after "is" check "s" is automatically cast to string
            else
                println("$s is not a string")
        }
        printStringLen("abc") // string length: 3
        printStringLen(1) // 1 is not a string
        printStringLen(null) // null is not a string

        // not instanceof variation
        fun printStringLen2(s: Any?) {
            if (s !is String) // !is = not instanceof
                println("$s is not a string") // after "is" check "s" is automatically cast to string
            else
                println("string length: ${s.length}")
        }
    }

    println("\nfor loop")
    run {
        val items = listOf("apple", "banana", "kiwifruit")

        for (item in items) { // apple, banana, kiwifriut
            println(item)
        }
        for (item in items.indices) { // 0, 1, 2
            println(item)
        }
    }

    println("\nwhile loop")
    run {
        val items = listOf("apple", "banana", "kiwifruit")
        var index = 0

        while (index++ < items.size) // item at 0 is apple, item at 1 is banana ...
            println("item at ${index-1} is ${items[index-1]}")
    }

    println("\nwhen expression = switch")
    run {
        fun describe(obj: Any): String =
                when (obj) { // "when" is an expression (as "if") and the result is implicitly returned here
                    1          -> "One"
                    "Hello"    -> "Greeting"
                    is Long    -> "Long"
                    !is String -> "Not a string"
                    else       -> "Unknown"
                }
        println(describe(1)) // One
        println(describe("Hello")) // Greeting
        println(describe(1L)) // Long
        println(describe(Object())) // Not a string
        println(describe("Other string")) // Unknown
    }

    println("\nranges")
    run {
        // in range check
        val x = 10
        val y = 9
        if (x in 1..y+1)
            println("fits in range") // executes ok
        if (x+1 !in 1..y+1) // not in
            println("doesn't fit in range") // executes ok

        // iterating
        for (z in 1..5)
            println(z) // 1 2 3 4 5
        for (z in 1..5 step 2) // with step
            println(z) // 1 3 5
    }

    println("\ncollections")
    run {
        val items = listOf("apple", "banana", "kiwifruit")
        for (i in items) // the same "in" works for iterables
            println(i) // apple, banana, kiwifriut

        // we can check any expression in when, even with "in"
        when {
            "apple" in items -> println("has apple")
            "banana" in items -> println("has banana") // won't be executed, because first "when" wins
        }

        // sequences ("streams") and lambdas
        items
            .filter { it.startsWith("a") } // this is lambda with one explicit "it" argument
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) } // APPLE
    }

}