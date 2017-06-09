package zooPackage;

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

}
