package tutorial

/**
 * Extensions: https://kotlinlang.org/docs/reference/extensions.html
 *
 * @author Lukasz Frankowski
 */
@Suppress("unused", "EXTENSION_SHADOWED_BY_MEMBER")
fun main(args: Array<String>) {

    println("extending classes with extensions")
    run {
        class Person(val name: String)

        val p = Person("Tom")

        fun Person.greeting() {
            println("hello, $name") // extension gets access to "this" (same as this.name)
        }

        p.greeting() // hello, Tom
    }

    println("\nextensions are resolved statically by class name")
    run {
        open class Person(val name: String)
        class Customer(name: String): Person(name)

        fun Person.greeting() {
            println("hello, $name")
        }

        fun Customer.greeting() {
            println("hi, $name")
        }

        fun doGreeting(p: Person) {
            p.greeting()
        }

        doGreeting(Customer("Tom")) // hello, Tom
    }


    println("\nextending generic classes")
    run {
        class Person(val name: String)
        class Animal(val name: String)

        class Greeting<T>(val t: T)

        val gp = Greeting<Person>(Person("Tom"))
        val ga = Greeting<Animal>(Animal("Buddy"))

        fun Greeting<Person>.say() { // this extension only extends Greeting<Person> (not Greeting<Animal>)
            println("hello, ${t.name}")
        }

        gp.say()
        // ga.say() // Error:(38, 9) Kotlin: Type mismatch: inferred type is Greeting<Animal> but Greeting<Person> was expected

        fun <T> Greeting<T>.sayAgain() { // this extension only extends Greeting<Person> (not Greeting<Animal>)
            println("hello, $t")
        }

        gp.sayAgain() // hello, tutorial._07_extensionsKt$main$3$Person@12f40c25
        ga.sayAgain() // hello, tutorial._07_extensionsKt$main$3$Animal@3ada9e37

    }

    println("\nextensions don't cover member methods")
    run {
        class Person(val name: String) {
            fun greeting() {
                println("hello, $name")
            }
        }

        fun Person.greeting() {
            println("hi, $name")
        }

        Person("Tom").greeting() // hello, Tom
    }

    println("\nextensions can be executed on null objects")
    run {
        class Person(val name: String)

        fun Person?.greeting() { // ? allows to run extension on null object
            println("hello, ${this?.name ?: "nobody"}")
        }

        val p: Person? = null;
        p.greeting() // hello, nobody
        Person("Tom").greeting() // hello, Tom
    }

    println("\nproperties can be extension too")
    run {
        class Person(val name: String)

        class Greeting {

            // property extensions can be added only inside other class, like in Greeting class here
            val Person.bigName: String
                // get() = field // not allowed, because property extension won't have backing field, they can only be virtual properties
                get() = name.toUpperCase()


            fun say() {
                println("hello, ${Person("Tom").bigName}") // the property extension is visible within the current class
            }
        }

        Greeting().say() // hello, TOM

        // the property extension is not visible outside the defining class
        // Person("Tom").greeting() // Error:(117, 23) Kotlin: Unresolved reference: greeting
        
    }

    println("\nclass memember extensions")
    run {
        class Person(val name: String)

        open class Greeting(val p: Person) {

            open fun fancyString(): String {
                return "~";
            }

            // we can declare extension of (Person) class in another (Greeting) class and "open" it to be overridden
            open fun Person.fancyName(): String {
                return "${fancyString()} $name ${fancyString()}"; // this extension has access to Greeting class members (fancyString())
            }

            fun say() {
                println("hello, ${p.fancyName()}") // the extension is accessible within Greeting class
            }

        }

        // but the extension is not accessible outside Greeting class
        // var name =Person("Tom").fancyName() // Error:(145, 23) Kotlin: Unresolved reference: fancyName

        open class FancyGreeting(p: Person) : Greeting(p) {

            // in subclass the extension declared in superclass will use overridding method
            override fun fancyString(): String {
                return "**"
            }
            
        }

        class VeryFancyGreeting(p: Person) : FancyGreeting(p) {

            // in subclass we can also override original extension for the superclass
            override fun Person.fancyName(): String {
                return "!!!! ${fancyString()} $name ${fancyString()} !!!!"
            }

        }

        val p = Person("Tom")
        Greeting(p).say() // hello, ~ Tom ~
        FancyGreeting(p).say() // hello, ** Tom **
        VeryFancyGreeting(p).say() // hello, !!!! ** Tom ** !!!!
    }

}