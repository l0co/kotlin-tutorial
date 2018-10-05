package tutorial

/**
 * Classes and inheritance: https://kotlinlang.org/docs/reference/classes.html
 *
 * @author Lukasz Frankowski
 */
@Suppress("JoinDeclarationAndAssignment", "ProtectedInFinal", "UNUSED_PARAMETER", "USELESS_IS_CHECK", "RedundantModalityModifier", "ConvertSecondaryConstructorToPrimary")
fun main(args: Array<String>) {

    println("\nstandard class")
    run {

        // default constructor contains no name and no body
        class Person(firstName: String) {
            var theFirstName: String = firstName; // you can use constructor arguments in property initializers

            var originalFirstName: String;

            init {
                originalFirstName = "Originally: $firstName"; // you can use constructor arguments in property initializers
            }
        }
        println(Person("Tom").theFirstName) // Tom: no "new" keyword for class instantiation

        // default constructor with access modifiers
        class Person2 protected constructor(firstName: String) {
        }
        // Person2("Tom") // Error:(24, 9) Kotlin: Cannot access '<init>': it is protected in 'Person2'

        // automatically created properties
        class Person3(val firstName: String, var lastName: String) { // val = final, var = variable
        }
        println(Person3("Tom", "Smith").firstName) // Tom

        // modifier in auto property
        class Person4(protected val firstName: String) {
        }
        // Person4("Tom").firstName // Error:(34, 24) Kotlin: Cannot access 'firstName': it is protected in 'Person4'

        // secondary constructor
        class Person5(val firstName: String) {

            constructor() : this("Tom") { // calls: this("Tom")
            }

        }
        println(Person5().firstName) // Tom

    }

    println("\nis (instanceof) and Any (Object)")
    run {
        class Person;
        println(Person() is Any) // true: is = instanceof, Any = Object (root class)
    }

    println("\ninheritance")
    run {
        // classes are final by default, need "open" keyword to open them
        open class Person(x: Int)
        class Customer(x: Int): Person(x) // ":" = extends + needs to call super constructor

        // calling secondary constructor in case of no default one
        open class Person1 {
            constructor(x:Int) {}
            constructor(x:Int, y:Int): this(x) {} // calls other secondary constructor
        }

        // calling super secondary constructor
        class Customer2: Person1 {
            constructor(x:Int): super(x) {} // calls super secondary constructor
            constructor(x:Int, y:Int): super(x, y) {}
        }
    }

    println("\noverriding methods")
    run {
        open class Person {
            fun nonOverridable() {}
            open fun overridable() {}
        }

        open class Customer: Person() {
            // override fun nonOverridable() { super.nonOverridable() } // Error:(84, 13) Kotlin: 'nonOverridable' in 'Person' is final and cannot be overridden
            override fun overridable() { super.overridable() } // this is still "open" for the next subclasses
        }

        // disallowing of further overriding open method
        class FatCustomer: Customer() {
            final override fun overridable() { super.overridable() } // using final
        }

    }

    println("\noverriding properties")
    run {
        open class Person {
            val nonOverridable: Int get() { return 1; }
            open val overridable: Int get() { return 1; }
        }

        open class Customer: Person() {
            // override val nonOverridable: Int get() = super.nonOverridable // Error:(100, 13) Kotlin: 'nonOverridable' in 'Person' is final and cannot be overridden
            override val overridable: Int get() = super.overridable
        }

    }

    println("\ninitialization order")
    run {
        open class Person {
            constructor() {
                println("pc: person constructor")
            }

            init {
                println("pi: person init")
            }
        }

        class Customer: Person {
            constructor(): super() {
                println("cc: customer constructor")
            }

            init {
                println("ci: customer init")
            }
        }

        Customer() // pi pc ci cc

    }

    println("\nabstract class")
    run {
        abstract class Person {
            abstract fun kill();
        }

        class Customer: Person() {
            override fun kill() {
                println("killing customer")
            }
        }
    }

}