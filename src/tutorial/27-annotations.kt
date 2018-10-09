package tutorial

/**
 * Annotations: https://kotlinlang.org/docs/reference/annotations.html
 *
 * @author Lukasz Frankowski
 */

// annotations are create with "annotation class"
@Target(AnnotationTarget.CLASS) // defines possible targets for annotation
@Retention // allows to remove information about annotation from runtime and keep it only in source or make it invisible for reflection
@Repeatable // defines whether the annotation can be repeatable
@MustBeDocumented // adds the annotation to generated documentation
private annotation class SomeAnnot // and annotation class itself

private annotation class NameAnnot(val name: String) // annotations can have normal properties with values

@Suppress("UNUSED_VARIABLE")
fun main(args: Array<String>) {

    println("annotation basics")
    run {
        @NameAnnot("person") class Person { // usage of annotation on class
            @NameAnnot("hello") fun hello(@NameAnnot("subject") subject: String) { // usage on function, parameter
                println("hello, $subject")
            }
        }
    }

    println("\nannotations can be used on lambdas")
    run {
        val f = @NameAnnot("my-lambda") { x: Int -> x*x }
    }

    println("\nannotations on constructor params and properties")
    run {
        // annotations placed on constructor params can be placed on field, getter and/or setter what can be point with following syntax:
        class Person(@field:NameAnnot("name") val name: String, // annot is put on field
                     @get:NameAnnot("last-name") val lastName: String, // annot is put on getter
                     @set:NameAnnot("age") var age: Int) { // annot is put on setter

            // same for member property
            @field:NameAnnot("bank-account")
            @get:NameAnnot("bank-account")
            @set:NameAnnot("bank-account")
            var bankAccount: String = ""

        }
    }

}