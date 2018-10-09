@file:Suppress("NOTHING_TO_INLINE")

package tutorial

/**                                                   -*
 * Inline Functions: https://kotlinlang.org/docs/reference/inline-functions.html
 *
 * @author Lukasz Frankowski
 */

private fun doLoop(a: Array<String>, p: (String) -> Unit) {
    for (i in a)
        p(i)
}

private inline fun inlineLoop(a: Array<String>, p: (String) -> Unit) {
    for (i in a)
        p(i)
}

private inline fun inlineLoop2(a: Array<String>, noinline p: (String) -> Unit) {
    for (i in a)
        p(i)
}

private fun <T> printClass(clazz: Class<T>) {
    println(clazz.simpleName)
}

private inline fun <reified T> reifiedPrintClass() {
    println(T::class.simpleName)
}

fun main(args: Array<String>) {

    println("inline function basics")
    run {
        // inline means putting the code in-place instead on calling a function
        val a = arrayOf("one", "two", "three")

        doLoop(a, ::println) // this compiles to exact doLoop implementation

        inlineLoop(a, ::println) // this compiles to (both inlineLoop and passed lambda are inlined in output code here):
                                 // for (i in a)
                                 //   println(i)

        inlineLoop2(a, ::println) // marking lambda argument as "noinline" means this lanbda won't be inlined, so only the main function
                                  // is inlined here:
                                  // val func = ::println
                                  // for (i in a)
                                  //   func(i)
    }

    println("\nreified generic types")
    run {
        // inline functions can have "reified" generic types, for which it's possible to discovery actual generic type binding in function body
        // this is possible because for function calls all generic type info is lost in runtime, but here we don't make any function calls at all

        printClass(String::class.java) // String
        reifiedPrintClass<String>() // String
    }

    println("\ninline properties")
    run {
        // you can mark getter and/or setter as inline to make it inlined in every invocation
        class Person(val name: String, val lastName: String) {
            val fullName: String
                inline get() = "$name $lastName"; // inline properties can't have backing fields
        }

        // you can also mark the whole property
        class Person2(val name: String, val lastName: String) {
            inline val fullName: String
                inline get() = "$name $lastName";
        }
    }

}