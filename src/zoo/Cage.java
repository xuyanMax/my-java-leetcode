package zoo;

import java.util.ArrayList;
import java.util.Iterator;

//author Chris.Trathen
public class Cage {

    private String description;
    private Location cageLocation;
    private ArrayList<Animal> occupants;

    // constructor
    public Cage(String d, Location x) {
        this.description = d;
        this.cageLocation = x;
        this.occupants = new ArrayList<Animal>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Add an animal to the cage. 
    // It must not be different type of animal to existing animals in cage
    public boolean addAnimal(Animal a) {
        Iterator<Animal> it = occupants.iterator();
        Animal an;
        while (it.hasNext()) {
            an = it.next();
            if (!a.typeOfAnimal.equals(an.typeOfAnimal)) {
                return false;
            }
        }
        this.occupants.add(a);
        a.setLoc(this.cageLocation);
        return true;
    }

    public void removeAnimal(Animal a) {
        this.occupants.remove(a);
    }

    // try and remove an animal with a certain name
    public boolean removeAnimalNamed(String name) {
        boolean result = false;
        Iterator<Animal> it = occupants.iterator();
        Animal an;
        Animal animalToremove = null;
        while (it.hasNext()) {
            an = it.next();
            if (an.getName().equals(name)) {
                animalToremove = an;
            }
        }
        if (animalToremove != null) {
            this.occupants.remove(animalToremove);
            result = true;
        }
        return result;
    }

    public boolean isEmpty() {
        return this.occupants.isEmpty();
    }

    // create a String showing all the animals in tha cage
    public String contents() {
        String desc = "------ Cage " + this.getDescription() + " at: " + this.cageLocation.toString() + "\n";
        Iterator<Animal> it = occupants.iterator();
        Animal a;
        while (it.hasNext()) {
            a = it.next();
            desc = desc + a.getTypeOfAnimal() + "\t" + a.getName() + "\n";
        }

        return desc;
    }

}
