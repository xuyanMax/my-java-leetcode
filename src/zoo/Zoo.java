package zoo;

// author Chris.Trathen
import java.util.ArrayList;
import java.util.Iterator;

public class Zoo {

    private String name;
    private ArrayList<Cage> listOfCages;

    public Zoo(String name) {
        this.name = name;
        this.listOfCages = new ArrayList<Cage>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCage(Cage c) {
        this.listOfCages.add(c);
    }

    public void removeCage(Cage c) {
        this.listOfCages.remove(c);
    }

    // create a String show all animals in the zoo
    // get this information from  each cage
    public String ShowAllAnimals() {
        String desc = "------ Zoo " + this.getName() + "\n";
        Iterator<Cage> it = listOfCages.iterator();
        Cage c;
        while (it.hasNext()) {
            c = it.next();
            desc = desc + c.contents();
        };
        return desc;
    }

    // Add an animal to the zoo.
    // Try and add to all cages. Try non empty cages first. 
    // Stop when a cage accepts the animal.
    public boolean addAnimal(Animal someAnimal) {
        Cage c;

        //  look in non empty cages first
        Iterator<Cage> it = listOfCages.iterator();
        while (it.hasNext()) {
            c = it.next();
            if (!c.isEmpty()) {
                if (c.addAnimal(someAnimal)) {
                    return true;
                }
            }
        }

        // now try empty cages
        it = listOfCages.iterator();
        while (it.hasNext()) {
            c = it.next();
            if (c.isEmpty()) {
                if (c.addAnimal(someAnimal)) {
                    return true;
                }
            }
        }
        return false;
    }

    // try and remove an animal with a certain name
    public boolean removeAnimalWithName(String name) {
        Iterator<Cage> it = listOfCages.iterator();
        Cage c;
        while (it.hasNext()) {
            c = it.next();
            if (c.removeAnimalNamed(name)) {
                return true;
            }
        }
        return false;
    }

}
