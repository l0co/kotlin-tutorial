package tutorial

/**
 * Returns and Jumps: https://kotlinlang.org/docs/reference/returns.html
 *
 * @author Lukasz Frankowski
 */
fun main(args: Array<String>) {

    println("simple break")
    run {
        for (i in 1..5) { // 1 2
            if (i==3)
                break;
            println(i);
        }
    }

    println("\nbreak to label")
    run {
        myloop@ for (i in 1..5) { // 1 2
            if (i==3)
                break@myloop;
            println(i);
        }
    }

    println("\nreturn from outer function in lambda") // 1 2
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

    println("\nreturn from lambda") // 1 2 4 5 this point is unreachable
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