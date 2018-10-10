package tutorial

/**
 * Delegation: https://kotlinlang.org/docs/reference/delegation.html
 *
 * @author Lukasz Frankowski
 */
fun main(args: Array<String>) {

    println("delegation basics")
    run {

        // this is manual delegate example
        class ManualGreetableDelegate(val g: Greetable): Greetable {

            override fun hello() {
                g.hello()
            }
            
        }

        // which can be written using "by" clause this way (this is semantically the same):
        class GreetableDelegate(val g: Greetable): Greetable by g {}

        ManualGreetableDelegate(NamedGreetable("manual")).hello() // hello, manual
        GreetableDelegate(NamedGreetable("auto")).hello() // hello, auto

    }

}