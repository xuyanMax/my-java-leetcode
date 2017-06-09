package zooPackage;

import java.util.ArrayList;
import java.util.Iterator;

//author Chris.Trathen
public class Cage {

    String description;
    ArrayList<Animal> occupants;

    public Cage(String d) {
        this.description = d;
        this.occupants = new ArrayList<Animal>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
        return true;
    }

    public void removeAnimal(Animal a) {
        this.occupants.remove(a);
    }

    public String contents() {
        String desc = "------ Cage " + this.getDescription() + "\n";
        Iterator<Animal> it = occupants.iterator();
        Animal a;
        while (it.hasNext()) {
            a = it.next();
            desc = desc + a.getTypeOfAnimal() + "\t" + a.getName() + "\n";
        }

        return desc;
    }

}
