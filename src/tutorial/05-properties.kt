package tutorial

import java.time.LocalDate

/**
 * @author Lukasz Frankowski
 */
@Suppress("RedundantGetter", "RedundantSetter", "CanBePrimaryConstructorProperty", "unused", "UNUSED_VARIABLE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE", "UNUSED_VALUE")
fun main(args: Array<String>) {

    println("Properties basics")
    run {
        class Person {
            val nonNullable: Int = 1
            var nullable: Int? = null // nullable still needs initialization
        }
        val p = Person()
        // p.nonNullable = 2; // Error:(12, 9) Kotlin: Val cannot be reassigned
        p.nullable = 12; // accessing through setter
        println("Person values: ${p.nonNullable}, ${p.nullable}") // 1, 12
    }

    println("\nGetters, setters and initializers")
    run {
        class Person(yearOfBirth: Int) {

            // "field" variable is accessible in getter and setter
            var age: Int
                get() {
                    println("Calling getter with: $field")
                    return field
                }
                set(value) {
                    println("Calling setter with: $field => $value")
                    field = value
                }

            init {
                // property can be initialized in init block
                age = LocalDate.now().year - yearOfBirth // can access constructor arguments here
            }

        }
        val p = Person(1979)
        println("Person is: ${p.age} years old") // calling setter (from init block), calling getter, Person is ...
    }

    println("\nBacking fields")
    run {
        class Person(yearOfBirth: Int) {

            val yearOfBirth: Int = yearOfBirth // this prop has default getter, so it has the backing field
            val yearOfDeath: Int? = null // this prop has non-default getter, but referencing "field" so it has the backing fields as well
                get() = field

            val yearOfBirthInTwoNumbers: Int // no backing field for this prop
                get() = yearOfBirth % 100

        }

        var p: Person? = Person(1979)
        p = null // stop with debugger here and see that "p" has only two physical fields (yearOfBirth and yearOfDeath)
    }

    println("\nLate init")
    run {
        class Person {
            lateinit var name: String // lateinit allows to skip property initialization here or in constructor

            fun setup(): Person {
                name = "Tom"
                return this
            }

            fun printName() {
                if (::name.isInitialized) // can check if is initialized, but only inside the class
                    println(name)
            }
        }
        val p = Person()
        // println("Person name: ${p.name}") // Exception in thread "main" kotlin.UninitializedPropertyAccessException: lateinit property name has not been initialized
        p.printName() // no effect
        println("Person name: ${p.setup().name}") // Person name: Tom
    }


}