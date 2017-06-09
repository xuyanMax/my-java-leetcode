package zooPackage;

// author Chris.Trathen
public abstract class Animal {

    protected String name;
    protected String typeOfAnimal;
    protected Location loc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfAnimal() {
        return typeOfAnimal;
    }

    public void setTypeOfAnimal(String typeOfAnimal) {
        this.typeOfAnimal = typeOfAnimal;
    }

    public void speak() {
        System.out.println("nothing");
    }
    
    public void walk(Location lc) {
        this.loc = lc;
    }

    public boolean canFly() {
        return false;
    }

}
