package com.lifeinide.kia.c2

@Suppress("UNUSED_VARIABLE", "RedundantExplicitType", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE", "UNUSED_VALUE")
fun main(args: Array<String>) {

    println("type of function and argument")
    run {
        // this is an block body function
        fun max(a: Int, b: Int): Int {
            return if (a > b) a else b // "if" can return value (expression, not statement)
        }
        println(max(1, 2))

        // a counterpart expression function
        fun max2(a: Int, b: Int) = if (a > b) a else b // expression function can infer the return type
        println(max2(1, 2))
    }

    println("\nval = final, var = variable")
    run {
        val val1: Int = 1; // explicite Int
        // val1 = 2; // Error:(18, 5) Kotlin: Val cannot be reassigned
        var var1 = 1; // inferred Int
        var1 = 2; // can be reassigned
        // var1 = "Hello"; // Error:(21, 12) Kotlin: Type mismatch: inferred type is String but Int was expected
    }

    println("\nstring template")
    run {
        val s = "world";
        println("Hello $s! (${s.length})");
    }

    println("\n")

}

