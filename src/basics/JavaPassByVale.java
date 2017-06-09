package basics;

/**
 * 
 * @author xu
 *
 * http://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value
 */
public class JavaPassByVale {

	public static void main(String[] args) {
		
		JavaPassByVale jpbv = new JavaPassByVale();
		
		// aDog is a pointer, not an actual dog, say it points to memory address 42
		Dog aDog = jpbv.new Dog("Max");
		foo(aDog,jpbv);
		
		if (aDog.getName().equals("Max")) 
			System.out.println("Java passed by value.");// true
		else if (aDog.getName().equals("Fiwi"))
			System.out.println("Java passed by reference.");
			
	}
	class Dog{
		String name;
		Dog(String n) {
			name = n;
		} 
		String getName() {
			return name;
		}
		void setName(String n) {
			name = n;
		}
	}
	static void foo(Dog d, JavaPassByVale jpbv){
		d.getName().equals("Max");
		
		d =  jpbv.new Dog("Fiwi");
		d.getName().equals("Fiwi");
	}

}
