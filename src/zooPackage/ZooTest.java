package zooPackage;

//author Chris.Trathen
// This class is for testing
public class ZooTest {

    public static void test() {

        Zoo z = new Zoo("Tooronga Zoo");

        // create 2 location objects
        Location t1 = new Location(10, 20);
        Location t2 = new Location(100, 25);

        // create 2 animals
        Dog x = new Dog("Fred", t1);
        Cat y = new Cat("Chairman meow", t2);

        // objects of different classes respond to same message = polymorphism
        x.speak();
        y.speak();

        // add 2 animals to a cage
        Cage c = new Cage("x");
        z.addCage(c);
        c.addAnimal(x);
        c.addAnimal(y);

        // show all animals in the zoo
        System.out.println(z.ShowAllAnimals());

    }

}
