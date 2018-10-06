package tutorial

@Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE", "UNUSED_VALUE")
/**
 * Generics: https://kotlinlang.org/docs/reference/generics.html
 *
 * @author Lukasz Frankowski
 */

open class A {
    fun hello() {
        println("hello")
    }
}

open class B: A()
open class C: B()

class Wrapper<T>(var value: T)

@Suppress("CanBeVal")
fun main(args: Array<String>) {

    println("generic basics")
    run {
        // the simples example of generic (of Any type)
        class Box<T>(val value: T)

        // create instance of Box class
        var box1: Box<Int> = Box(1)

        // type inferring
        val box2 = Box(2) // box2 is of type Box<Int>
        box1 = box2 // which is compatible with box1
    }

    println("\nvariance (producer and consumer)")
    run {
        // first see how generic producer and consumer works in java in java/GenericsCheck.java file
        var wrapperA = Wrapper<A>(A())
        var wrapperB = Wrapper<B>(B())
        var wrapperC = Wrapper<C>(C())

        // on producer example the task we want to achieve is to be able to manipulate wrappers of A, B, C classes in our function
        fun sayHello(w: Wrapper<A>) {
            w.value.hello()
        }

        sayHello(wrapperA)
        // sayHello(wrapperB) // not allowed, because Wrapper<B> is not the same type as Wrapper<A>
        // sayHello(wrapperC) // not allowed, for above reasons

        // NOTE: Wrapper<A> is not compatible this way with Wrapper<B>, because if it was, the following will be allowed:
        // wrapperA = wrapperB // if types were compatible, this would be allowed
        // wrapperA.value = A() // now we set on wrapperB element of type A

        // how to do it properly?
        fun properSayHello(w: Wrapper<out A>) {
            w.value.hello() // because of <out A> this getter (of "value" property) returns object of type A, what is expected behavior

            // however, with <out A> pattern, because "w" can be a wrapper of any A subtype (and we don't know which one),
            // this is prohibited here:
            // w.value = A()
            // w.value = B()
            // w.value = C()
        }

        properSayHello(wrapperA)
        properSayHello(wrapperB)
        properSayHello(wrapperC)

        // on consumer example the task is different - we want to be able to replace wrapper value with C instance
        fun changeValue(w: Wrapper<C>) {
            w.value = C()
        }

        // changeValue(wrapperA) // not allowed, because Wrapper<A> is not the same type as Wrapper<C>
        // changeValue(wrapperB) // not allowed, for above reasons

        // how to do it properly?
        fun properChangeValue(w: Wrapper<in C>) {
            w.value = C()

            // however, with <in C> pattern, we know we can set C value on the wrapper, but we don't know what we will get from it,
            // so following expressions are prohibited:
            // val a: A = w.value
            // val b: B = w.value
            // val c: C = w.value

            // the only working and last known type of value here is Any:
            val any: Any? = w.value
        }

        properChangeValue(wrapperA)
        properChangeValue(wrapperB)
        properChangeValue(wrapperC)

    }

    // TODOLF Type projections
    
}