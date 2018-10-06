package tutorial.java;

/**
 * Additionally to {@code 10-generics.kt} we show here how generic producer and consumer works in java.
 *
 * @author Lukasz Frankowski
 */
public class GenericsCheck {

	public static class A {}
	public static class B extends A {}
	public static class C extends B {}

	public static class Wrapper<T> {

		private T value;

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

	}

	public static void main(String [] args) {
		// on producer <? extend ...> you can get value, but never set

		Wrapper<? extends A> producer; // this wrapper will accept all subclasses of A (including A)
		producer = new Wrapper<A>(); // ok
		producer = new Wrapper<B>(); // ok
		producer = new Wrapper<>(); // let's use
		
		// producer.setValue(new A()); // can't set, because producer can point to Wrapper<B>
		// producer.setValue(new B()); // can't set, because producer can point to Wrapper<A>
		A a = producer.getValue();
		// B b = w.getValue(); // can't get, because it can only guarantee A instances to be returned

		// on consumer <? super ...> you can set value but you always get object

		Wrapper<? super B> consumer; // this wrapper will accept all superclasses of B (including B)
		consumer = new Wrapper<A>(); // ok
		consumer = new Wrapper<B>(); // ok
		// consumer = new Wrapper<C>(); // C is not super of B
		consumer = new Wrapper<Object>(); // also ok
		consumer = new Wrapper<>(); // let's use this

		// consumer.setValue(new A()); // can't set, because consumer can point to Wrapper<B>
		consumer.setValue(new B()); // ok
		consumer.setValue(new C()); // ok
		// B b = consumer.getValue(); // can't get, because consumer can point to Wrapper<A>, Wrapper<Object>
		// a = consumer.getValue(); // can't get, because consumer can point to Wrapper<Object>
		Object o = consumer.getValue(); // you can only get Object from consumer
	}

}
