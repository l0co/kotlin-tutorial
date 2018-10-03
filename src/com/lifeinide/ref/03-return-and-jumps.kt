package com.lifeinide.ref

fun main(args: Array<String>) {

    println("break example")
    run {
        for (i in 1..5) {
            if (i==3)
                break;
            println(i);
        }
    }

    println("\nbreak to label example")
    run {
        myloop@ for (i in 1..5) {
            if (i==3)
                break@myloop;
            println(i);
        }
    }

    println("\nreturn from outer function in lambda")
    run {
        fun foo() {
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return // non-local return directly to the caller of foo()
                println(it)
            }
            println("this point is unreachable")
        }
        foo()
    }

    println("\nreturn from lambda")
    run {
        fun foo() {
            listOf(1, 2, 3, 4, 5).forEach { // each block has implicit label with function name
                if (it == 3) return@forEach // non-local return directly to the caller of foo() (equivalent of "continue")
                println(it)
            }
            println("this point is unreachable")
        }
        foo()
    }
}