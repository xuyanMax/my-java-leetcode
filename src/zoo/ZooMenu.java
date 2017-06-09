/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

/**
 *
 * @author Chris.Trathen
 */
public class ZooMenu {

    static Zoo theZoo = new Zoo("Suzhou Zoo");
    static Location initialLocation = new Location(0, 0);

    public static void menuRun() {

        System.out.println("Welcome to " + theZoo.getName());
        boolean exit = false;
        String select = "";

        // keep processing menu requests until exit
        while (!exit) {
            System.out.println("0= exit, 1=add animal, 2=remove animal, 3=add cage, 4=display animals : ");
            select = DataInput.inputString();
            System.out.println();
            switch (select) {
                case "0":
                    exit = true;
                    break;
                case "1":
                    addAnimal();
                    break;
                case "2":
                    removeAnimal();
                    break;
                case "3":
                    addCage();
                    break;
                case "4":
                    displayAnimals();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }

    // enter details about a new cage.
    // add that cage to the zoo
    public static void addCage() {
        System.out.println("Enter cage description:");
        String n = DataInput.inputString();
        int x, y;
        System.out.println("Enter cage X coordinate:");
        x = DataInput.inputInteger();
        System.out.println("Enter cage Y coordinate:");
        y = DataInput.inputInteger();
        Location k = new Location(x, y);
        Cage c = new Cage(n, k);
        theZoo.addCage(c);
    }

    // enter details about a new animal.
    // add that animal to a cage in the zoo
    public static void addAnimal() {
        Animal a;
        System.out.println("Enter animal name:");
        String name = DataInput.inputString();

        System.out.println("Enter animal type:");
        String type = DataInput.inputString();
        switch (type) {
            case "dog":
                a = new Dog(name, initialLocation);
                break;
            case "cat":
                a = new Cat(name, initialLocation);
                break;
            case "bird":
                a = new Bird(name, initialLocation);
                break;
            case "panda":
                a = new Panda(name, initialLocation);
                break;
            default:
                System.out.println("Invalid animal type");
                return;
        }
        theZoo.addAnimal(a);
    }

    // remove an animal from the zoo (and its cage)
    public static void removeAnimal() {
        System.out.println("Enter name of animal to remove :");
        String name = DataInput.inputString();
        boolean removeResult = theZoo.removeAnimalWithName(name);
        if (removeResult) {
            System.out.println(name + " was successfully remove");
        } else {
            System.out.println(name + " was not found");
        }
    }

    // show all animals in all cages
    public static void displayAnimals() {
        System.out.println(theZoo.ShowAllAnimals());
    }

}
