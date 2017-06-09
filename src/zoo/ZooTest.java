package zoo;

//author Chris.Trathen
// This class is for testing
public class ZooTest {

    public static void test() {

        Zoo z = new Zoo("Tooronga Zoo");

        // create 2 location objects
        Location t1 = new Location(10, 20);
        Location t2 = new Location(100, 25);
        Location t3 = new Location(100, 250);

        // create 2 animals
        Dog x = new Dog("Fred", t1);
        Cat y = new Cat("Chairman meow", t2);
        Bird a1 = new Bird("Birdie 1", t1);
        Bird a2 = new Bird("Birdie 2", t1);
        Panda p1 = new Panda("Peter", t1);

        double dis = x.walk(t2);
        System.out.println(x.name + " walked " + dis);

        // objects of different classes respond to same message = polymorphism
        x.speak();
        y.speak();

        // make cages
        Cage c1 = new Cage("cage 1", t1);
        Cage c2 = new Cage("cage 2", t2);
        Cage c3 = new Cage("cage 3", t3);
        z.addCage(c1);
        z.addCage(c2);
        z.addCage(c3);

        z.addAnimal(a1);
        z.addAnimal(x);
        z.addAnimal(y);
        z.addAnimal(a2);
        z.addAnimal(p1);

        // show all animals in the zoo
        System.out.println(z.ShowAllAnimals());

    }

}
