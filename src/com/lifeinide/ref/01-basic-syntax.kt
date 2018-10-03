package com.lifeinide.ref

@Suppress("JoinDeclarationAndAssignment", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE", "UNUSED_VALUE", "ConstantConditionIf", "ConvertCallChainIntoSequence")
fun main(args: Array<String>) {

    println("function arguments and return types")
    run {
        // block body function
        fun sum(a: Int, b: Int): Int {
            return a + b
        }
        println("1 + 2 = ${sum(1, 2)}")

        // expression body function can infer type
        fun sum2(a: Int, b: Int) = a + b
        println("1 + 2 = ${sum2(1, 2)}")

        // function with no returning value
        fun void(): Unit {
            println("void function")
        }
        void()
        fun void2() { // Unit can be omitted
            println("void function")
        }
        void2()
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

        println(x)
        incX()
        println(x)
    }

    println("\nif can be an expression")
    run {
        val x = if (true) 1 else 2
        println(x)
    }

    println("\nnull check")
    run {
        fun returnInt(): Int {
            // return null // can't return null here
            return 1
        }

        fun returnInt2(): Int? { // ? = "nullable" modifier
            return null
        }

        println(returnInt())
        println(returnInt2())
    }

    println("\ninstanceof")
    run {
        fun printStringLen(s: Any?) {
            if (s is String) // is = instanceof
                println("string length: ${s.length}") // after "is" check "s" is automatically cast to string
            else
                println("$s is not a string")
        }
        printStringLen("abc")
        printStringLen(1)
        printStringLen(null)

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
        items.indices
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
        while (index++ < items.size) {
            println("item at ${index-1} is ${items[index-1]}")
        }
    }

    println("\nwhen expression = switch")
    run {
        fun describe(obj: Any): String =
                when (obj) { // when is an expression (as if) and the result is implicitly returned here
                    1          -> "One"
                    "Hello"    -> "Greeting"
                    is Long    -> "Long"
                    !is String -> "Not a string"
                    else       -> "Unknown"
                }
        println(describe(1))
        println(describe("Hello"))
        println(describe(1L))
        println(describe(Object()))
        println(describe("Other string"))
    }

    println("\nranges")
    run {
        // in range check
        val x = 10
        val y = 9
        if (x in 1..y+1) {
            println("fits in range")
        }
        if (x+1 !in 1..y+1) { // not in
            println("doesn't fit in range")
        }

        // iterating
        for (z in 1..5)
            println(z)
        for (z in 1..5 step 2) // with step
            println(z)
    }

    println("\ncollections")
    run {
        val items = listOf("apple", "banana", "kiwifruit")
        for (i in items) // the same "in" works for iterables
            println(i)

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
            .forEach { println(it) }
    }

}