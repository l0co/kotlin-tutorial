package tutorial

@Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE", "UNUSED_VALUE", "CanBeVal", "UNUSED_VARIABLE")
/**
 * Generics: https://kotlinlang.org/docs/reference/generics.html
 *
 * @author Lukasz Frankowski
 */
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
        // declarations required for variance examples
        open class A {
            fun hello() {
                println("hello")
            }
        }

        open class B: A()
        open class C: B()

        class Wrapper<T>(var value: T)

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

        // how to do it properly? java equivalent is: <? extends A> - accept all wrappers for A subclasses (including A)
        // this is the producer, because you can read data from the wrapper and it's ensured you will get (at least) A
        // however, you cannot write value because it doesn't know of which exactly type is the wrapper passed (A, B or C)
        fun properSayHello(w: Wrapper<out A>) {
            w.value.hello() // because of <out A> type, this getter (of "value" property) returns object of type A, what is expected behavior

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

        // how to do it properly? java equivalent: <? super C> - accept all wrappers for C superclasses (including C)
        // this is the consumer, because it can set the value to C and it will be accepted (for all possible wrapper: A, B and C)
        // however, you cannot read generic value because the type of value can be C, B, A or even Any
        fun properChangeValue(w: Wrapper<in C>) { //
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

    println("\ngeneric functions")
    run {
        fun <T: Number> toInt(number: T): Int {
            return number.toInt();
        }

        println(toInt<Int>(1)); // function can be called with explicite generic type
        println(toInt(2)); // generic type can also be inferred (as <Int> here)
    }

    println("\nupper bounds")
    run {
        // narrowing possible generic types to subclasses of given class with : operator
        class Box<T: Number>(val value: T)
        val box = Box(2) // inferring Box<Int> type
        // val box = Box("text") // Error:(39, 20) Kotlin: Type parameter bound for T in constructor Box2<T : Number>(value: T)
                                 // is not satisfied: inferred type String is not a subtype of Number

        // narrowing possible generic types to multiple interfaces
        class Box2<T>(val value: T) where T: CharSequence, T: Comparable<T>
    }

    println("\ntype erasure")
    run {
        // generic types are checked only at compile time and are erased in runtime, so "is" check cannod be performed against them

        class Box<T: Number>(val value: T)
        val box: Any? = Box(1)

        // if (box is Box<Int>) println("Box<Int>") // Error:(130, 20) Kotlin: Cannot check for instance of erased type: Box<Int>

        // you can only check if box is of general Box type
        if (box is Box<*>) println("Box<*>") // Box<*>: * wildcard (star projection) replaces any type here
    }
    
}